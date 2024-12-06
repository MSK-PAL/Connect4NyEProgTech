package com.example.connect4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:connect4.db";

    // Adatbázis inicializálása
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String createTableSQL = """
                CREATE TABLE IF NOT EXISTS players (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    score INTEGER NOT NULL
                );
                """;
            conn.createStatement().execute(createTableSQL);
            System.out.println("Adatbázis inicializálva.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Játékos hozzáadása
    public static void addPlayer(String name, int score) {
        String insertSQL = "INSERT INTO players (name, score) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // High Score lista lekérdezése
    public static void printHighScores() {
        String querySQL = "SELECT name, score FROM players ORDER BY score DESC LIMIT 10";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(querySQL);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("High Scores:");
            while (rs.next()) {
                System.out.printf("%s: %d%n", rs.getString("name"), rs.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

