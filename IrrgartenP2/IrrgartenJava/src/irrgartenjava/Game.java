/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgartenjava;

/**
 *
 * @author moham
 */// Game.java
import java.util.ArrayList;

public class Game {
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
        // Add exit to a random empty position
        int[] exitPos = labyrinth.randomEmptyPos();
        labyrinth.setExit(exitPos[0], exitPos[1]);

        // Add monsters to random positions
        for (int i = 0; i < players.size(); i++) {
            Monster monster = new Monster(Dice.randomStrength(), Dice.randomIntelligence());
            int[] pos = labyrinth.randomEmptyPos();
            labyrinth.addMonster(pos[0], pos[1], monster);
            monsters.add(monster);
        }

        // Spread players in random positions
        labyrinth.spreadPlayers(players);
    }

    public void nextPlayer() {
        currentPlayerIdx = (currentPlayerIdx + 1) % players.size();
        currentPlayer = players.get(currentPlayerIdx);
    }

    public void logPlayerWon() {
        log += "Player " + currentPlayer + " has reached the exit and won the game!\n";
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
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public Directions actualDirection(Directions preferredDirection) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public GameCharacter combat(Monster monster) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public void manageReward(GameCharacter winner) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public void manageResurrection() {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }
}
