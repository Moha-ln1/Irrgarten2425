package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

public abstract class CardDeck<T extends CombatElement> {
    protected ArrayList<T> cardDeck;

    // Constructor
    public CardDeck() {
        this.cardDeck = new ArrayList<>();
        addCards(); // Llama al método para inicializar la baraja
    }

    // Método abstracto para añadir todas las cartas específicas al mazo
    protected abstract void addCards();

    // Método para añadir una carta específica al mazo
    protected void addCard(T card) {
        cardDeck.add(card);
    }

    // Proporciona la siguiente carta
    public T nextCard() {
        if (cardDeck.isEmpty()) {
            addCards(); // Si no hay cartas, las añade
            Collections.shuffle(cardDeck); // Baraja las cartas
        }
        return cardDeck.remove(0); // Devuelve y elimina la primera carta
    }
}
