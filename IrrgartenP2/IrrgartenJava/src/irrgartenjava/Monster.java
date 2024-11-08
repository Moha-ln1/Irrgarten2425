/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgartenjava;

/**
 *
 * @author moham
 */
// Monster.java
public class Monster {
    private static final int INITIAL_HEALTH = 3;
    private int health;
    private float strength;
    private float intelligence;
    private int row; 
    private int col; 

    public Monster(float strength, float intelligence) {
        this.health = INITIAL_HEALTH;
        this.strength = strength;
        this.intelligence = intelligence;
        this.row = -1; // Initialize to an invalid position
        this.col = -1; // Initialize to an invalid position
    }

    public boolean dead() {
        return health <= 0;
    }

    public float attack() {
        return Dice.intensity(strength);
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Monster[Health: " + health + ", Strength: " + strength + ", Intelligence: " + intelligence + ", Position: (" + row + ", " + col + ")]";
    }

    public void gotWounded() {
        health--;
    }

    public boolean defend(float receivedAttack) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }
}
