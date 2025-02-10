package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon> {

    public WeaponCardDeck() {
        addCards();
    }

    
    @Override
    protected void addCards() {
        for (int i = 0; i < 10; i++) { // Genera un conjunto inicial de armas
            addCard(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
        }
    }
}
