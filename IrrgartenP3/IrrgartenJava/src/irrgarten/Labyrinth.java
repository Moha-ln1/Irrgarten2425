/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author moham
 */// Labyrinth.java
import java.util.ArrayList;

public class Labyrinth {
    private static final char BLOCK_CHAR_OBSTACLE = 'X';
    private static final char BLOCK_CHAR_EMPTY = '-';
    private static final char BLOCK_CHAR_MONSTER = 'M';
    private static final char BLOCK_CHAR_EXIT = 'E';
    private static final char BLOCK_CHAR_COMBAT = 'C';

    private static final int ROW = 0;
    private static final int COL = 1;

    private int nRows, nCols;
    private int exitRow, exitCol; // Exit coordinates
    private Monster[][] monsters;
    private Player[][] players;
    private char[][] cells;

    public Labyrinth(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
        this.cells = new char[nRows][nCols];

        // Initialize all cells to empty
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                cells[i][j] = BLOCK_CHAR_EMPTY;
            }
        }

        // Set initial exit position to an invalid location
        this.exitRow = -1;
        this.exitCol = -1;
    }

    public void setExit(int row, int col){
        this.exitRow = row;
        this.exitCol = col;
        cells[exitRow][exitCol] = BLOCK_CHAR_EXIT;

    }
    
    public boolean haveAWinner() {
        // Check if any player is at the exit position
        return players[exitRow][exitCol] != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                sb.append(cells[i][j]).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void addMonster(int row, int col, Monster monster) {
        if (posOK(row, col) && cells[row][col] == BLOCK_CHAR_EMPTY) {
            monsters[row][col] = monster;
            monster.setPos(row, col);
            cells[row][col] = BLOCK_CHAR_MONSTER;
        }
    }

    public boolean posOK(int row, int col) {
        return row >= 0 && row < nRows && col >= 0 && col < nCols;
    }

    public boolean emptyPos(int row, int col) {
        return cells[row][col] == BLOCK_CHAR_EMPTY;
    }

    public boolean monsterPos(int row, int col) {
        return cells[row][col] == BLOCK_CHAR_MONSTER;
    }

    public boolean exitPos(int row, int col) {
        return row == exitRow && col == exitCol;
    }

    public boolean combatPos(int row, int col) {
        return cells[row][col] == BLOCK_CHAR_COMBAT;
    }

    public boolean canStepOn(int row, int col) {
        return posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col));
    }

    public void updateOldPos(int row, int col) {
        if (posOK(row, col)) {
            if (combatPos(row, col)) {
                cells[row][col] = BLOCK_CHAR_MONSTER;
            } else {
                cells[row][col] = BLOCK_CHAR_EMPTY;
            }
        }
    }

    public int[] dir2Pos(int row, int col, Directions direction) {
        int[] newPos = {row, col};
        switch (direction) {
            case UP: newPos[ROW]--; break;
            case DOWN: newPos[ROW]++; break;
            case LEFT: newPos[COL]--; break;
            case RIGHT: newPos[COL]++; break;
        }
        return newPos;
    }

    public int[] randomEmptyPos() {
        int[] position;
        do {
            int row = Dice.randomPos(nRows);
            int col = Dice.randomPos(nCols);
            position = new int[]{row, col};
        } while (!emptyPos(position[ROW], position[COL]));
        return position;
    }

    // Dentro de la clase Labyrinth
    public void spreadPlayers(ArrayList<Player> players) {
        for (Player p : players) {
            // Obtener una posición vacía aleatoria
            int[] pos = randomEmptyPos();
            // Colocar al jugador en la nueva posición
            putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }


    public Monster putPlayer(Directions direction, Player player) {
        // 1.1: Obtener la posición actual del jugador
        int oldRow = player.getRow();
        int oldCol = player.getCol();

        // 1.3: Obtener la nueva posición en función de la dirección
        int[] newPos = dir2Pos(oldRow, oldCol, direction);

        // 1.4: Mover al jugador a la nueva posición y devolver el monstruo en la nueva posición si lo hay
        return putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
    }


    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        int incRow = 0, incCol = 0;

        // Definir incrementos según la orientación
        if (orientation == Orientation.VERTICAL) {
            incRow = 1;
        } else {
            incCol = 1;
        }

        int row = startRow, col = startCol;

        // Colocar los bloques en las posiciones correspondientes
        while (posOK(row, col) && emptyPos(row, col) && length > 0) {
            cells[row][col] = BLOCK_CHAR_OBSTACLE;
            length--;
            row += incRow;
            col += incCol;
        }
    }


    public ArrayList<Directions> validMoves(int row, int col) {
        ArrayList<Directions> output = new ArrayList<>();

        // Agregar direcciones válidas en función de las posiciones libres
        if (canStepOn(row + 1, col)) output.add(Directions.DOWN);
        if (canStepOn(row - 1, col)) output.add(Directions.UP);
        if (canStepOn(row, col + 1)) output.add(Directions.RIGHT);
        if (canStepOn(row, col - 1)) output.add(Directions.LEFT);

        return output;
    }


    public Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        Monster output = null;

        // Verificar si la nueva posición es válida para moverse
        if (canStepOn(row, col)) {
            // Si la posición antigua es válida, actualizarla a vacío
            if (posOK(oldRow, oldCol)) {
                Player p = this.players[oldRow][oldCol];
                if(p == player){
                    updateOldPos(oldRow, oldCol);
                    this.players[oldRow][oldCol] = null;
                }
            }

            boolean monsterPos = this.monsterPos(row, col);
            
            // Verificar si la nueva posición tiene un monstruo
            if (monsterPos) {
                cells[row][col] = BLOCK_CHAR_COMBAT;
                output = monsters[row][col];
            } else {
                cells[row][col] = player.getNumber();
            }

            // Actualizar la nueva posición del jugador en el tablero
            this.players[row][col] = player;
            player.setPos(row, col);
        }

        return output;
    }
    
    public void removeMonster(Monster monster, Player winnerPlayer) {
        int row = monster.getRow();
        int col = monster.getCol();

        // Verificar si la posición es válida y contiene el monstruo
        if (posOK(row, col) && monsters[row][col] == monster) {
            monsters[row][col] = null;       // Eliminar el monstruo de la matriz
            cells[row][col] = winnerPlayer.getNumber();  // Actualizar la celda para reflejar que está vacía
        }
    }

}
