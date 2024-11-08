/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author moham
 */
import java.util.ArrayList;

public class Player {
    //private static final int MAX_HEALTH = 10;
    private static final int INITIAL_HEALTH = 10;
    private static final int MAX_SHIELDS = 3;
    private static final int MAX_WEAPONS = 2;
    private static final int HITS2LOSE = 3;

    private String name;
    private char number;
    private int health;
    private int consecutiveHits;
    private float intelligence;
    private float strength;
    private int row; 
    private int col; 
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

    public Player(int number, float intelligence, float strength) {
        this.name = "Player #" + number;
        this.number = (char) ('0' + number); // Convertir el número a un carácter
        this.health = INITIAL_HEALTH;
        this.intelligence = intelligence;
        this.strength = strength;
        this.row = -1; // Inicializa a una posición inválida
        this.col = -1; // Inicializa a una posición inválida
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        this.consecutiveHits = 0;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public char getNumber() {
        return number;
    }
    
    

    public void resurrect() {
        this.weapons.clear();
        this.shields.clear();
        this.health = INITIAL_HEALTH;
        this.consecutiveHits = 0;
    }

    public void setPos(int row, int col) {
        this.row = row;
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
        if (health < 0) {
            health = 0;  // Asegurarse de que la salud no sea negativa
        }
        
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
        return "Player[" + name + ", Health: " + health + ", Position: (" + row + ", " + col + ")]";
    }

    public boolean defend(float receivedAttack) {
        return this.manageHit(receivedAttack);
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        int size = validMoves.size(); // 1.1: Obtener el número de movimientos válidos

        if (size > 0 && !validMoves.contains(direction)) { // Si hay movimientos válidos y la dirección preferida no es una de ellos
            return validMoves.get(0); // 1.3: Obtener el primer movimiento válido
        }

        return direction; // 1.5: Retornar la dirección preferida
    }


    public void receiveReward() {
        // 1.1: Obtener la recompensa de armas
        int wReward = Dice.weaponsReward();

        // 1.2: Obtener la recompensa de escudos
        int sReward = Dice.shieldsReward();

        // Bucle para crear y recibir nuevas armas
        for (int i = 0; i < wReward; i++) {
            Weapon wnew = newWeapon(); // 1.3: Crear una nueva arma
            receiveWeapon(wnew); // 1.4: Recibir la nueva arma
        }

        // Bucle para crear y recibir nuevos escudos
        for (int i = 0; i < sReward; i++) {
            Shield snew = newShield(); // 1.5: Crear un nuevo escudo
            receiveShield(snew); // 1.6: Recibir el nuevo escudo
        }

        // 1.7: Agregar salud adicional
        int extraHealth = Dice.healthReward();
        health += extraHealth;
    }


    public void receiveWeapon(Weapon w) {
        for (int i = 0; i < weapons.size(); i++) { // Bucle para verificar las armas existentes
            Weapon wi = weapons.get(i); // 1.1: Obtener la siguiente arma

            if (wi.discard()) { // 1.2: Verificar si el arma debe ser descartada
                weapons.remove(wi); // 1.3: Remover el arma si debe ser descartada
            }
        }

        if (weapons.size() < MAX_WEAPONS) { // 1.4: Verificar si se puede agregar una nueva arma
            weapons.add(w); // 1.5: Agregar la nueva arma
        }
    }


    public void receiveShield(Shield s) {
        for (int i = 0; i < shields.size(); i++) { // Bucle para verificar los escudos existentes
            Shield si = shields.get(i); // 1.1: Obtener el siguiente escudo

            if (si.discard()) { // 1.2: Verificar si el escudo debe ser descartado
                shields.remove(si); // 1.3: Remover el escudo si debe ser descartado
            }
        }

        if (shields.size() < MAX_SHIELDS) { // 1.4: Verificar si se puede agregar un nuevo escudo
            shields.add(s); // 1.5: Agregar el nuevo escudo
        }
    }


    public boolean manageHit(float receivedAttack) {
        
        float defense = defensiveEnergy(); // 1.1: Calcular la energía defensiva del jugador

        if (defense < receivedAttack) { // Verificar si el ataque supera la defensa
            gotWounded(); // 1.2: El jugador recibe una herida
            incConsecutiveHits(); // 1.3: Incrementar los golpes consecutivos
        } else {
            resetHits(); // 1.4: Reiniciar los golpes consecutivos si el ataque no supera la defensa
        }

        // Verificar si el jugador está muerto o ha alcanzado el límite de golpes consecutivos
        if (consecutiveHits == HITS2LOSE || dead()) { 
            resetHits(); // 1.5: Reiniciar los golpes
            return true; // El jugador pierde
        }
        else
            return false; // El jugador no pierde
    }
    
}
