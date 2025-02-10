/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author moham
 */
// Shield.java
public class Shield extends CombatElement{

    public Shield(float effect, int used) {
        super(effect, used);
    }
    
    
    @Override
    protected float produceEffect() {
        if (uses > 0) {
            uses--;
            return effect;
        } else {
            return 0;
        }
    }
     
    // Método para protegerse
    public float protect() {
        return produceEffect(); // Usa el método general para calcular el efecto
    }

    @Override
    public String toString() {
        return "S" + super.toString(); // Prefijo "S" para identificar escudos
    }
}

