package Tests;

import irrgarten.*;
import irrgarten.UI.GameUI;
import irrgarten.UI.UI;
import irrgarten.controller.Controller;

import javax.swing.UIManager;

public class TestP3 {
    public static void main(String[] args) {
        // Configurar Look and Feel para mejorar la estética de la interfaz gráfica
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configuración del juego: número de jugadores, filas y columnas del laberinto
        int nPlayers = 2;  // Número de jugadores (puedes cambiarlo)
        int nRows = 5;     // Número de filas del laberinto
        int nCols = 5;     // Número de columnas del laberinto

        // Crear la instancia del juego
        Game game = new Game(nPlayers, nRows, nCols);

        // Crear la vista
        UI view = new GameUI();

        // Crear el controlador
        Controller controller = new Controller(game, view);

        // Iniciar el juego
        if (!game.finished()) controller.play();
    }
}
