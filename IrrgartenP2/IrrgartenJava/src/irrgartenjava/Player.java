/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgartenjava;

/**
 *
 * @author moham
 */
import java.util.ArrayList;

public class Player {
    private static final int MAX_HEALTH = 10;
    private static final int INIT_HEALTH = 5;
    private static final int MAX_SHIELDS = 3;
    private static final int HITS2LOSE = 3;

    private String name;
    private char number;
    private int health;
    private int consecutiveHits;
    private float intelligence;
    private float strength;
    private int pos; 
    private int col; 
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

    public Player(int number, float intelligence, float strength) {
        this.name = "Player #" + number;
        this.number = (char) ('0' + number); // Convertir el número a un carácter
        this.health = INIT_HEALTH;
        this.intelligence = intelligence;
        this.strength = strength;
        this.pos = -1; // Inicializa a una posición inválida
        this.col = -1; // Inicializa a una posición inválida
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        this.consecutiveHits = 0;
    }

    public void resurrect() {
        this.weapons.clear();
        this.shields.clear();
        this.health = INIT_HEALTH;
        this.consecutiveHits = 0;
    }

    public void setPos(int row, int col) {
        this.pos = row;
        this.col = col;
    }

    public boolean dead() {
        return health <= 0;
    }

    public float attack() {
        return strength + sumWeapons();
    }

    public Weapon newWeapon() {
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }

    public Shield newShield() {
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }

    public float defensiveEnergy() {
        return intelligence + sumShields();
    }

    public void resetHits() {
        consecutiveHits = 0;
    }

    public void gotWounded() {
        health--;
    }

    public void incConsecutiveHits() {
        consecutiveHits++;
    }

    public float sumWeapons() {
        float total = 0;
        for (Weapon weapon : weapons) {
            total += weapon.attack();
        }
        return total;
    }

    public float sumShields() {
        float total = 0;
        for (Shield shield : shields) {
            total += shield.protect();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Player[" + name + ", Health: " + health + ", Position: (" + pos + ", " + col + ")]";
    }

    public boolean defend(float receivedAttack) {
        return this.manageHit(receivedAttack);
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public void receiveReward() {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public void receiveWeapon(Weapon w) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public void receiveShield(Shield s) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public boolean manageHit(float receivedAttack) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }
}
