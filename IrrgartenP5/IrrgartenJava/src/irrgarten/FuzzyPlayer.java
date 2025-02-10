package irrgarten;

import java.util.ArrayList;

public class FuzzyPlayer extends Player {

    // Constructor que crea un FuzzyPlayer a partir de otro Player
    public FuzzyPlayer(Player other) {
        super(other); // Utiliza el constructor de copia de Player
    }

    // Sobrescritura del método move
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        if (validMoves.isEmpty()) {
            return null; // Si no hay movimientos válidos, no se mueve
        }

        // Convierte la lista de direcciones válidas a un arreglo
        Directions[] validMovesArray = validMoves.toArray(new Directions[0]);

        // Utiliza el método del dado para seleccionar la dirección
        return Dice.preferredDirection(direction, validMovesArray, Dice.intensity(this.getIntelligence()));
    }

    // Sobrescritura del método attack
    @Override
    public float attack() {
        // La energía de ataque suma las armas y una intensidad basada en la fuerza
        return super.sumWeapons() + Dice.intensity(this.getStrength());
    }

    // Sobrescritura del método defensiveEnergy
    @Override
    public float defensiveEnergy() {
        // La energía defensiva suma los escudos y una intensidad basada en la inteligencia
        return super.sumShields() + Dice.intensity(this.getIntelligence());
    }

    // Sobrescritura del método toString
    @Override
    public String toString() {
        return "Fuzzy" + super.toString() + "]"; // Anteponer "Fuzzy" a la representación del jugador
    }
}
