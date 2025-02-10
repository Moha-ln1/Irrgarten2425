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

public class Monster extends LabyrinthCharacter {
    private static final float INITIAL_HEALTH = 5;

    public Monster(String name, float intelligence, float strength) {
        super(name, intelligence, strength, INITIAL_HEALTH);
    }

    @Override
    public float attack() {
        return Dice.intensity(strength);
    }

    @Override
    public boolean defend(float receivedAttack) {
        if (!dead()) {
            float defensiveEnergy = Dice.intensity(intelligence);
            if (defensiveEnergy < receivedAttack) {
                gotWounded();
            }
        }
        return dead();
    }
}
