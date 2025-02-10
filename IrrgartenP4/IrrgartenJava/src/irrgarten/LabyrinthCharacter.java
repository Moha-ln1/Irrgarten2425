/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author Moha
 */

public abstract class LabyrinthCharacter {
    protected String name;
    protected float intelligence;
    protected float strength;
    protected float health;
    protected int row;
    protected int col;

    // Constructor principal
    public LabyrinthCharacter(String name, float intelligence, float strength, float health) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        this.row = -1; // Posición inicial inválida
        this.col = -1; // Posición inicial inválida
    }

    // Constructor copia
    public LabyrinthCharacter(LabyrinthCharacter other) {
        this.name = other.name;
        this.intelligence = other.intelligence;
        this.strength = other.strength;
        this.health = other.health;
        this.row = other.row;
        this.col = other.col;
    }

    // Métodos abstractos
    public abstract float attack();
    public abstract boolean defend(float attack);

    // Métodos generales
    public boolean dead() {
        return health <= 0;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    protected float getIntelligence() {
        return intelligence;
    }

    protected float getStrength() {
        return strength;
    }

    protected float getHealth() {
        return health;
    }

    protected void setHealth(float health) {
        this.health = health;
        if (this.health < 0) {
            this.health = 0; // Evitar valores negativos
        }
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return name + "[Health: " + health + ", Strength: " + strength + ", Intelligence: " + intelligence + ", Position: (" + row + ", " + col + ")]";
    }

    protected void gotWounded() {
        health--;
        if (health < 0) {
            health = 0; // Evitar valores negativos
        }
    }
}
