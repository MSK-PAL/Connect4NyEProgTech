package com.example.connect4;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Connect4!");
        System.out.println("The game will start soon...");

        // Játékosok neveinek bekérése
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player 1's name: ");
        String player1 = scanner.nextLine();
        System.out.print("Enter player 2's name: ");
        String player2 = scanner.nextLine();

        // Játékosok inicializálása
        Game game = new Game(List.of(player1, player2));
        game.start(); // Meghívjuk a start() metódust a játék indításához.
    }
}

