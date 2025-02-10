/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author moham
 */
// Weapon.java
public class Weapon extends CombatElement{

    public Weapon(float effect, int used) {
        super(effect, used);
    }
    

    @Override
    protected float produceEffect() {
        if (uses > 0) {
            uses--; // Reduce los usos disponibles
            return effect; // Devuelve el poder de ataque
        } else {
            return 0; // Sin usos restantes, no hay efecto
        }
    }
    
    public float attack() {
        return produceEffect();
    }

    @Override
    public String toString() {
        return "W" + super.toString(); // Prefijo "W" para identificar armas
    }

}
