/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgartenjava;

/**
 *
 * @author moham
 */
// Weapon.java
public class Weapon {
    private float power;
    private int uses;

    public Weapon(float power, int uses) {
        this.power = power;
        this.uses = uses;
    }

    public float attack() {
        if (uses > 0) {
            uses--;
            return power;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "W[" + power + ", " + uses + "]";
    }

    public boolean discard() {
        return Dice.discardElement(uses); // Este método se implementará en la clase Dice.
    }
}
