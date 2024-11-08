/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean dead() {
        return health == 0;
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
        if (health < 0) {
            health = 0;  // Asegurarse de que la salud no sea negativa
        }
    }

    public boolean defend(float receivedAttack) {
        // 1.1: Verificar si el monstruo está muerto
        boolean isDead = dead();

        // 2: Si el monstruo no está muerto
        if (!isDead) {
            // 1.2: Calcular la energía defensiva del monstruo
            float defensiveEnergy = Dice.intensity(intelligence);

            // 3: Comparar la energía defensiva con el ataque recibido
            if (defensiveEnergy < receivedAttack) {
                // 1.3: Si la energía defensiva es menor, el monstruo recibe una herida
                gotWounded();

                // 1.4: Verificar si el monstruo ha muerto después de la herida
                isDead = dead();
            }
        }

        // 1.5: Devolver el estado del monstruo (si está muerto o no)
        return isDead;
        
    }

    
}
