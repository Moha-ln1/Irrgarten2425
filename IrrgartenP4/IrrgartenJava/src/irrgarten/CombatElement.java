/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author Moha
 */
public abstract class CombatElement {
    float effect;
    int uses;

    public CombatElement(float effect, int used) {
        this.effect = effect;
        this.uses = uses;
    }
    
    // Método para producir el efecto (abstracto para que cada clase lo implemente)
    protected abstract float produceEffect();
    
    // Método para verificar si el elemento debe descartarse
    public boolean discard() {
        return Dice.discardElement(uses); // Suponiendo que este método decide si el elemento debe descartarse
    }

    @Override
    public String toString() {
        return "[" + effect + ", " + uses + "]";
    }
}
