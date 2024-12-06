package com.example.connect4;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Connect4!");
        System.out.println("The game will start soon...");

        // Játékosok inicializálása
        Game game = new Game(Arrays.asList("Player 1", "Player 2"));
        game.start(); // Meghívjuk a start() metódust a játék indításához.
    }
}

