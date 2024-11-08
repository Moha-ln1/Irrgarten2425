/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;
/**
 *
 * @author moham
 */
import irrgarten.*;

import irrgarten.UI.TextUI;
import irrgarten.controller.Controller;

public class TestP3 {
    public static void main(String[] args) {
        // Configuración del juego: número de jugadores, filas y columnas del laberinto
        int nPlayers = 2;  // Número de jugadores (puedes cambiarlo)
        int nRows = 5;     // Número de filas del laberinto
        int nCols = 5;     // Número de columnas del laberinto

        // Crear la instancia del juego
        Game game = new Game(nPlayers, nRows, nCols);

        // Crear la vista
        TextUI textUI = new TextUI();

        // Crear el controlador
        Controller controller = new Controller(game, textUI);

        // Iniciar el juego
        if(!game.finished()) controller.play();
    }
}
