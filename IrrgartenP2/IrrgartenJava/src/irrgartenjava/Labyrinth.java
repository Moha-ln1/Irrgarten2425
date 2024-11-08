/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgartenjava;

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

    public void spreadPlayers(ArrayList<Player> players) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public Monster putPlayer(Directions direction, Player player) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }

    public ArrayList<Directions> validMoves(int row, int col) {
        throw new UnsupportedOperationException("Method not implemented yet.");
    }
}
