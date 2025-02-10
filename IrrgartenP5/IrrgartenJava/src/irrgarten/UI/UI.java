package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;

public interface UI {
    Directions nextMove();
    void showGame(GameState gameState);

    /**
     *
     */
    public void disableInteraction();
}
