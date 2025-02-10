/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;


/**
 *
 * @author moham
 */// TestP2.java

public class TestP2 {
    public static void main(String[] args) {
        // Prueba de la clase Monster
        System.out.println("===== Monster Test =====");
        Monster monster = new Monster("i",5.0f, 7.0f);
        monster.setPos(2, 3);
        System.out.println("Monster Initial: " + monster);
        System.out.println("Monster Attack: " + monster.attack());
        monster.gotWounded();
        System.out.println("Monster after Wound: " + monster);
        System.out.println("Monster is Dead: " + monster.dead());

        // Prueba de la clase Player
        System.out.println("\n===== Player Test =====");
        Player player = new Player(1, 6.0f, 8.0f);
        player.setPos(1, 2);
        System.out.println("Player Initial: " + player);
        System.out.println("Player Attack: " + player.attack());
        player.gotWounded();
        System.out.println("Player after Wound: " + player);
        player.resetHits();
        player.incConsecutiveHits();
        System.out.println("Player Defensive Energy: " + player.defensiveEnergy());
        System.out.println("Player is Dead: " + player.dead());

        // Prueba de la clase Labyrinth
        System.out.println("\n===== Labyrinth Test =====");
        Labyrinth labyrinth = new Labyrinth(5, 5);
        System.out.println("Initial Labyrinth:\n" + labyrinth);
        int[] exitPos = {4, 4};
        labyrinth.setExit(exitPos[0], exitPos[1]);
        System.out.println("Labyrinth after setting exit:\n" + labyrinth);

        int[] monsterPos = {2, 2};
        labyrinth.addMonster(monsterPos[0], monsterPos[1], monster);
        System.out.println("Labyrinth after adding Monster:\n" + labyrinth);

        System.out.println("Is Exit Position (4,4): " + labyrinth.exitPos(4, 4));
        System.out.println("Is Monster Position (2,2): " + labyrinth.monsterPos(2, 2));
        System.out.println("Can Step on (3,3): " + labyrinth.canStepOn(3, 3));
        System.out.println("Random Empty Position: " + java.util.Arrays.toString(labyrinth.randomEmptyPos()));

        // Prueba de la clase Game
        System.out.println("\n===== Game Test =====");
        Game game = new Game(2, 5, 5);
        System.out.println("Game Initial State: " + game.getGameState());

        // Probar cambio de jugador
        game.nextPlayer();
        System.out.println("Game State after changing player: " + game.getGameState());

        // Prueba de logging
        game.logPlayerWon();
        System.out.println("Game Log after player wins:\n" + game.getGameState().getLog());

        game.logMonsterWon();
        System.out.println("Game Log after monster wins:\n" + game.getGameState().getLog());
    }
}
