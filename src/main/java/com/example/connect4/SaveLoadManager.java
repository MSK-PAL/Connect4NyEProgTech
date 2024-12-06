package com.example.connect4;

import java.io.*;
import java.util.List;

public class SaveLoadManager {

    private static final String SAVE_FILE = "game_save.dat";

    // Játék állapot mentése fájlba
    public void saveGame(Board board, List<String> players, int turn) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            out.writeObject(board);
            out.writeObject(players);
            out.writeInt(turn);
            System.out.println("Játék mentve!");
        } catch (IOException e) {
            System.out.println("Hiba történt a mentés során: " + e.getMessage());
        }
    }

    // Játék állapot betöltése fájlból
    public GameState loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            Board board = (Board) in.readObject();
            List<String> players = (List<String>) in.readObject();
            int turn = in.readInt();
            return new GameState(board, players, turn);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hiba történt a betöltés során: " + e.getMessage());
            return null;
        }
    }

    // Belső osztály a játék állapot tárolásához
    public static class GameState {
        private final Board board;
        private final List<String> players;
        private final int turn;

        public GameState(Board board, List<String> players, int turn) {
            this.board = board;
            this.players = players;
            this.turn = turn;
        }

        public Board getBoard() {
            return board;
        }

        public List<String> getPlayers() {
            return players;
        }

        public int getTurn() {
            return turn;
        }
    }
}

