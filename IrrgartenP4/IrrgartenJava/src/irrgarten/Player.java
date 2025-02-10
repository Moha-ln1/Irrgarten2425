package irrgarten;

import java.util.ArrayList;

public class Player extends LabyrinthCharacter {
    private static final int INITIAL_HEALTH = 10;
    private static final int MAX_SHIELDS = 3;
    private static final int MAX_WEAPONS = 2;
    private static final int HITS2LOSE = 3;

    private char number;
    private int consecutiveHits;
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

    private WeaponCardDeck weaponDeck;
    private ShieldCardDeck shieldDeck;
    
    public Player(int number, float intelligence, float strength) {
        super("Player #" + number, intelligence, strength, INITIAL_HEALTH);
        this.number = (char) ('0' + number);
        this.consecutiveHits = 0;
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        this.weaponDeck = new WeaponCardDeck();
        this.shieldDeck = new ShieldCardDeck();
    }
    
    public Player(Player other) {
        super(other); // Llama al constructor de copia de LabyrinthCharacter
        this.number = other.number;
        this.consecutiveHits = other.consecutiveHits;

        // Copia profunda de las listas de armas y escudos
        this.weapons = new ArrayList<>();
        for (Weapon weapon : other.weapons) {
            this.weapons.add(weapon); // Suponiendo que Weapon tiene un constructor de copia
        }

        this.shields = new ArrayList<>();
        for (Shield shield : other.shields) {
            this.shields.add(shield); // Suponiendo que Shield tiene un constructor de copia
        }
    }


    public char getNumber() {
        return number;
    }

    public void resurrect() {
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        consecutiveHits = 0;
    }

    @Override
    public float attack() {
        return strength + sumWeapons();
    }

    @Override
    public boolean defend(float receivedAttack) {
        float defense = defensiveEnergy();
        if (defense < receivedAttack) {
            gotWounded();
            incConsecutiveHits();
        } else {
            resetHits();
        }
        return consecutiveHits == HITS2LOSE || dead();
    }

    float defensiveEnergy() {
        return intelligence + sumShields();
    }

    float sumWeapons() {
        return weapons.stream().map(Weapon::attack).reduce(0f, Float::sum);
    }

    float sumShields() {
        return shields.stream().map(Shield::protect).reduce(0f, Float::sum);
    }

    void resetHits() {
        consecutiveHits = 0;
    }

    void incConsecutiveHits() {
        consecutiveHits++;
    }

    
     /**
     * Mueve al jugador en una dirección calculada usando el dado.
     *
     * @param direction  La dirección preferida por el jugador.
     * @param validMoves Lista de direcciones válidas.
     * @return La dirección seleccionada para el movimiento.
     */
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        if (validMoves.isEmpty()) {
            return null; // Si no hay movimientos válidos, no se mueve
        }

        // Convierte la lista de direcciones válidas a un arreglo
        Directions[] validMovesArray = validMoves.toArray(new Directions[0]);

        // Utiliza el método del dado para seleccionar la dirección
        return Dice.preferredDirection(direction, validMovesArray, intelligence);
    }
    
    public void receiveReward() {
        // Recibe armas
        for (int i = 0; i < Dice.weaponsReward(); i++) {
            weapons.add(weaponDeck.nextCard());
        }

        // Recibe escudos
        for (int i = 0; i < Dice.shieldsReward(); i++) {
            shields.add(shieldDeck.nextCard());
        }

        // Aumenta salud
        this.health += Dice.healthReward();
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
    
    @Override
    public String toString() {
        return super.toString() + " [Weapons: " + weapons.size() + ", Shields: " + shields.size() + "]";
    }
}
