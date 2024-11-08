/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgartenjava;

/**
 *
 * @author moham
 */
// TestP1.java
// TestP1.java
public class TestP1 {
    public static void main(String[] args) {
        // Prueba de Weapon
        Weapon weapon = new Weapon(2.0f, 5);
        System.out.println("Weapon initial: " + weapon);
        System.out.println("Weapon Attack: " + weapon.attack());
        System.out.println("Weapon after attack: " + weapon);
        for (int i = 0; i < 6; i++) {
            System.out.println("Weapon Attack Attempt " + (i + 1) + ": " + weapon.attack());
        }
        System.out.println("Weapon final: " + weapon);
        System.out.println("Weapon Discard: " + weapon.discard());

        // Prueba de Shield
        Shield shield = new Shield(3.0f, 4);
        System.out.println("\nShield initial: " + shield);
        System.out.println("Shield Protect: " + shield.protect());
        System.out.println("Shield after protect: " + shield);
        for (int i = 0; i < 5; i++) {
            System.out.println("Shield Protect Attempt " + (i + 1) + ": " + shield.protect());
        }
        System.out.println("Shield final: " + shield);
        System.out.println("Shield Discard: " + shield.discard());

        // Prueba de Dice
        System.out.println("\nDice Tests:");
        for (int i = 0; i < 100; i++) {
            System.out.println("Resurrect Player Attempt " + (i + 1) + ": " + Dice.resurrectPlayer());
        }

        System.out.println("Random Position: " + Dice.randomPos(10));
        System.out.println("Who Starts: " + Dice.whoStarts(4));
        System.out.println("Random Intelligence: " + Dice.randomIntelligence());
        System.out.println("Random Strength: " + Dice.randomStrength());
        System.out.println("Weapons Reward: " + Dice.weaponsReward());
        System.out.println("Shields Reward: " + Dice.shieldsReward());
        System.out.println("Health Reward: " + Dice.healthReward());
        System.out.println("Weapon Power: " + Dice.weaponPower());
        System.out.println("Shield Power: " + Dice.shieldPower());
        System.out.println("Uses Left: " + Dice.usesLeft());
        System.out.println("Intensity: " + Dice.intensity(5.0f));
        System.out.println("Discard Element (uses = 3): " + Dice.discardElement(3));
        System.out.println("Discard Element (uses = 0): " + Dice.discardElement(0));
        System.out.println("Discard Element (uses = 5): " + Dice.discardElement(5));

        // Prueba de GameState
        GameState gameState = new GameState("Labyrinth", "Player1", "Monster1", 0, false, "Game started");
        System.out.println("\nGameState:");
        System.out.println("Labyrinth: " + gameState.getLabyrinth());
        System.out.println("Players: " + gameState.getPlayers());
        System.out.println("Monsters: " + gameState.getMonsters());
        System.out.println("Current Player: " + gameState.getCurrentPlayer());
        System.out.println("Is Winner: " + gameState.isWinner());
        System.out.println("Log: " + gameState.getLog());
    }
}
