package com.example.connect4;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {
    private final int rows; // Sorok száma
    private final int cols; // Oszlopok száma
    private final char[][] grid; // A tábla mátrixa

    // Konstruktor: Inicializálja a táblát az adott méretekkel
    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        initialize();
    }

    // Tábla inicializálása üres állapotra
    private void initialize() {
        for (int i = 0; i < rows; i++) {
            Arrays.fill(grid[i], '.'); // Üres mezők: '.'
        }
    }

    // Ellenőrzi, hogy egy oszlop tele van-e
    public boolean isColumnFull(int col) {
        return grid[0][col] != '.';
    }

    // Korong behelyezése a táblába
    public boolean dropPiece(int col, char symbol) {
        for (int i = rows - 1; i >= 0; i--) { // Alulról felfelé keressük az első üres helyet
            if (grid[i][col] == '.') {
                grid[i][col] = symbol;
                return true; // Sikeres lépés
            }
        }
        return false; // Ha az oszlop tele van
    }

    // Ellenőrzi, hogy van-e nyertes
    public boolean checkWinner(char symbol) {
        return checkHorizontal(symbol) || checkVertical(symbol) || checkDiagonal(symbol);
    }

    // Vízszintes ellenőrzés
    private boolean checkHorizontal(char symbol) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - 4; c++) { // Csak olyan helyen vizsgálunk, ahol még lehet négyes
                if (grid[r][c] == symbol && grid[r][c + 1] == symbol &&
                    grid[r][c + 2] == symbol && grid[r][c + 3] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    // Függőleges ellenőrzés
    private boolean checkVertical(char symbol) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r <= rows - 4; r++) { // Csak olyan helyen vizsgálunk, ahol még lehet négyes
                if (grid[r][c] == symbol && grid[r + 1][c] == symbol &&
                    grid[r + 2][c] == symbol && grid[r + 3][c] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    // Átlós ellenőrzés
    private boolean checkDiagonal(char symbol) {
        // Bal felsőtől jobb alsóig tartó átlók
        for (int r = 0; r <= rows - 4; r++) {
            for (int c = 0; c <= cols - 4; c++) {
                if (grid[r][c] == symbol && grid[r + 1][c + 1] == symbol &&
                    grid[r + 2][c + 2] == symbol && grid[r + 3][c + 3] == symbol) {
                    return true;
                }
            }
        }

        // Jobb felsőtől bal alsóig tartó átlók
        for (int r = 0; r <= rows - 4; r++) {
            for (int c = 3; c < cols; c++) {
                if (grid[r][c] == symbol && grid[r + 1][c - 1] == symbol &&
                    grid[r + 2][c - 2] == symbol && grid[r + 3][c - 3] == symbol) {
                    return true;
                }
            }
        }
        return false;
    }

    // Tábla megjelenítése
    public void printBoard() {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Sorok száma lekérdezése
    public int getRows() {
        return rows;
    }

    // Oszlopok száma lekérdezése
    public int getCols() {
        return cols;
    }
}

