/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author moham
 */// Game.java
import java.util.ArrayList;

public class Game {
    
    private static final int MAX_ROUNDS = 10;

    private Labyrinth labyrinth;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Player currentPlayer;
    private int currentPlayerIdx;
    private String log;

    public Game(int nPlayers, int nRows, int nCols) {
        this.labyrinth = new Labyrinth(nRows, nCols);
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.currentPlayerIdx = Dice.whoStarts(nPlayers);
        this.log = "";

        // Create players
        for (int i = 0; i < nPlayers; i++) {
            players.add(new Player(i, Dice.randomIntelligence(), Dice.randomStrength()));
        }
        this.currentPlayer = players.get(currentPlayerIdx);

        configureLabyrinth();
    }

    public boolean finished() {
        return labyrinth.haveAWinner();
    }

    public GameState getGameState() {
        return new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIdx, finished(), log);
    }

    public void configureLabyrinth() {
        // Añadir obstáculos aleatorios
    //    for (int i = 0; i < 2; i++) {  // Añadir 10 obstáculos
    //        int[] obstaclePos = labyrinth.randomEmptyPos();
    //        labyrinth.addBlock(Orientation.HORIZONTAL, obstaclePos[0], obstaclePos[1], 3);  // Añadir una fila de 3 obstáculos
    //    }

        // Añadir la salida a una posición vacía aleatoria
        int[] exitPos = labyrinth.randomEmptyPos();
        labyrinth.setExit(exitPos[0], exitPos[1]);

        // Añadir monstruos en posiciones aleatorias
        for (int i = 0; i < 5; i++) {
            //Monster monster = new Monster(Dice.randomStrength(), Dice.randomIntelligence());
            Monster monster = new Monster(100, 100);
            int[] pos = labyrinth.randomEmptyPos();
            labyrinth.addMonster(pos[0], pos[1], monster);
            monsters.add(monster);
        }

        // Distribuir jugadores en posiciones aleatorias
        labyrinth.spreadPlayers(players);
    }


    public void nextPlayer() {
        do {
            currentPlayerIdx = (currentPlayerIdx + 1) % players.size();
            currentPlayer = players.get(currentPlayerIdx);
        } while (currentPlayer.dead());  // Saltar a jugadores muertos hasta encontrar uno vivo
    }


    public void logPlayerWon() {
        log += "Player " + currentPlayer + " won the combat\n";
    }

    public void logMonsterWon() {
        log += "A monster has defeated player " + currentPlayer + ".\n";
    }

    public void logResurrected() {
        log += "Player " + currentPlayer + " has been resurrected.\n";
    }

    public void logPlayerSkipTurn() {
        log += "Player " + currentPlayer + " skips their turn.\n";
    }

    public void logPlayerNoOrders() {
        log += "Player " + currentPlayer + " received no valid move orders.\n";
    }

    public void logNoMonster() {
        log += "No monster present for combat.\n";
    }

    public void logRounds(int rounds, int max) {
        log += "Combat ended after " + rounds + " of " + max + " rounds.\n";
    }

    public boolean nextStep(Directions preferredDirection) {
        log = "";
        
        boolean dead = currentPlayer.dead();
        
        // Verificar si el jugador está muerto
        if (!dead) {
            Directions direction = this.actualDirection(preferredDirection);
            
            if(direction != preferredDirection){
                this.logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster == null){
                this.logNoMonster();
            }
            else{
                GameCharacter winner = this.combat(monster);
                this.manageReward(winner);
            }
        }
        else{
            this.manageResurrection();
        }
        
        boolean endGame = this.finished();
        
        if(!endGame){
            this.nextPlayer();
        }
        
        return endGame;
    }

    // Dentro de la clase Game
    public Directions actualDirection(Directions preferredDirection) {
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();

        // Obtener movimientos válidos para la posición actual del jugador
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);

        // Llamar al método move del jugador para obtener la dirección final
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        return output;
    }
    
    public GameCharacter combat(Monster monster) {
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;

        // 1.1: El jugador ataca primero
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);

        // 1.2: Verificamos si el monstruo perdió después del primer ataque
        while (rounds < MAX_ROUNDS && !lose) {
            // 1.3: Verificamos si el monstruo ha muerto después del primer ataque
            if (monster.dead()) {
                winner = GameCharacter.PLAYER;
                break;
            }

            winner = GameCharacter.MONSTER;
            rounds++;

            // 1.4: El monstruo ataca
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);

            // 1.5: Verificamos si el jugador ha muerto
            if (currentPlayer.dead()) {
                break;
            }

            if (!lose) {
                // 1.6: El jugador ataca nuevamente
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);

                // 1.7: Verificamos si el monstruo ha muerto después del segundo ataque
                if (monster.dead()) {
                    break;
                }
            }
        }

        // 1.8: Registrar las rondas de combate
        logRounds(rounds, MAX_ROUNDS);

        // 1.9: Si el jugador ganó, eliminar al monstruo del laberinto
        if (winner == GameCharacter.PLAYER && monster.dead()) {
            labyrinth.removeMonster(monster, currentPlayer);
        }
        

        return winner;
    }


    
    public void manageResurrection() {
        // 1.1: Intentar resucitar al jugador utilizando Dice.resurrectPlayer()
        boolean resurrect = Dice.resurrectPlayer();

        if (resurrect) {
            // 1.2: Si resucita, invocar resurrect() en el jugador
            currentPlayer.resurrect();
            // 1.3: Registrar la resurrección
            logResurrected();
        } else {
            // 1.4: Si no, registrar que el jugador ha saltado el turno
            logPlayerSkipTurn();
        }
    }

    
   // Dentro de la clase Game
    public void manageReward(GameCharacter winner) {
        if (winner == GameCharacter.PLAYER) {
            // 1.1: Si el jugador es el ganador, recibir la recompensa
            currentPlayer.receiveReward();
            // 1.2: Registrar la victoria del jugador
            logPlayerWon();
        } else {
            // 1.3: Si no, registrar que el monstruo ganó
            logMonsterWon();
            
        }
    }


    
}
