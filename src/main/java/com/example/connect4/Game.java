package com.example.connect4;

import java.util.List;
import java.util.Scanner;

public class Game {
    private final Board board;
    private final List<String> players;
    private int currentPlayerIndex;
    private int turn;
    private final SaveLoadManager saveLoadManager;

    // Konstruktor, amely inicializálja a táblát és a játékosokat
    public Game(List<String> players) {
        this.board = new Board(6, 7);  // 6 sor, 7 oszlopos tábla
        this.players = players;
        this.currentPlayerIndex = 0;
        this.turn = 0;
        this.saveLoadManager = new SaveLoadManager();
    }

    // Játék indítása
    public void start() {
        System.out.println("Connect4 játék elindult!");
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            board.printBoard();
            System.out.println("Játékos " + players.get(currentPlayerIndex) + ", válassz egy oszlopot (0-6): ");
            int col = scanner.nextInt();

            if (col < 0 || col >= 7 || board.isColumnFull(col)) {
                System.out.println("Érvénytelen lépés, próbálj újra.");
                continue;
            }

            char symbol = currentPlayerIndex == 0 ? 'X' : 'O';
            board.dropPiece(col, symbol);

            // Nyertes ellenőrzése
            if (board.checkWinner(symbol)) {
                board.printBoard();
                System.out.println("Gratulálunk! " + players.get(currentPlayerIndex) + " nyert!");
                gameOver = true;
            }

            // Játékos váltás
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            turn++;

            // Opció a játék mentésére
            if (turn % 5 == 0) {  // Példa: minden 5. lépés után menteni
                saveLoadManager.saveGame(board, players, turn);
                System.out.println("A játék mentésre került.");
            }
        }
        scanner.close();
    }
}

