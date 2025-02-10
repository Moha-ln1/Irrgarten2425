package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield> {

    public ShieldCardDeck() {
        addCards();
    }

    
    @Override
    protected void addCards() {
        for (int i = 0; i < 10; i++) { // Genera un conjunto inicial de escudos
            addCard(new Shield(Dice.shieldPower(), Dice.usesLeft()));
        }
    }
}
