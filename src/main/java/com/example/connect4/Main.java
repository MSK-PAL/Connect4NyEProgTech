package com.example.connect4;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Connect4!");
        System.out.println("The game will start soon...");

        // Létrehozunk egy példányt a Game osztályból, ahol a játék logikája lesz.
        Game game = new Game();
        game.start(); // Meghívjuk a start() metódust a játék indításához.
    }
}
