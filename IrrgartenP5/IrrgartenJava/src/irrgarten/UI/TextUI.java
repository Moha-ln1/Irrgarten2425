
package irrgarten.UI;

import irrgarten.*;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI implements UI{
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    @Override
    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    @Override
    public void showGame(GameState gameState) {
        // Mostrar el laberinto
        System.out.println("===== Labyrinth =====");
        System.out.println(gameState.getLabyrinth());

        // Mostrar información de los jugadores
        System.out.println("\n===== Players =====");
        System.out.println(gameState.getPlayers());

        // Mostrar información de los monstruos
        System.out.println("\n===== Monsters =====");
        System.out.println(gameState.getMonsters());

        // Mostrar el jugador actual
        System.out.println("\nCurrent Player: Player #" + gameState.getCurrentPlayer());

        // Mostrar si hay un ganador
        if (gameState.isWinner()) {
            System.out.println("\nThere is a winner!");
        } else {
            System.out.println("\nThe game is still ongoing.");
        }

        // Mostrar el registro del juego
        System.out.println("\n===== Game Log =====");
        System.out.println(gameState.getLog());
    }

    @Override
    public void disableInteraction() {
        throw new UnsupportedOperationException("FUncion para gui"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



}
