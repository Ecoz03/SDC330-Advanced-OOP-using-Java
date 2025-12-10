/*
Name: Jessie Sosniak
Date: 03 DEC 2025
Assignment: SDC330 Course Project
Details: Handles all database operations for the Game Inventory System using SQLite via JDBC.
Provides methods to create tables, add new games, retrieve game details, list all games,
search by title or first letter, update game attributes, and delete records. This class
serves as the persistence layer, ensuring that game data is stored and managed consistently.
*/

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:games.db";

    public DatabaseHandler() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Connected to database: " + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS Games (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "title TEXT UNIQUE NOT NULL," +
                     "platform TEXT NOT NULL," +
                     "genre TEXT NOT NULL," +
                     "releaseYear INTEGER," +
                     "maxPlayers TEXT" +
                     ");";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public boolean addGame(String title, String platform, Integer releaseYear, String maxPlayers, String genre) {
        String sql = "INSERT INTO Games(title, platform, genre, releaseYear, maxPlayers) VALUES(?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, platform);
            pstmt.setString(3, genre);
            if (releaseYear != null) pstmt.setInt(4, releaseYear);
            else pstmt.setNull(4, Types.INTEGER);
            if (maxPlayers != null && !maxPlayers.isEmpty()) pstmt.setString(5, maxPlayers);
            else pstmt.setNull(5, Types.VARCHAR);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding game: " + e.getMessage());
            return false;
        }
    }

    public String infoGame(String title) {
        String sql = "SELECT * FROM Games WHERE title = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return "Title: " + rs.getString("title") + "\n" +
                       "Platform: " + rs.getString("platform") + "\n" +
                       "Genre: " + rs.getString("genre") + "\n" +
                       "Release Year: " + rs.getInt("releaseYear") + "\n" +
                       "Max Players: " + rs.getString("maxPlayers") + "\n\n";
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving game info: " + e.getMessage());
        }
        return null;
    }

    public String infoAllGames() {
        String sql = "SELECT * FROM Games ORDER BY title ASC";
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sb.append("Title: ").append(rs.getString("title")).append("\n")
                  .append("Platform: ").append(rs.getString("platform")).append("\n")
                  .append("Genre: ").append(rs.getString("genre")).append("\n")
                  .append("Release Year: ").append(rs.getInt("releaseYear")).append("\n")
                  .append("Max Players: ").append(rs.getString("maxPlayers")).append("\n\n");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all games: " + e.getMessage());
        }
        return sb.toString();
    }

    public String searchByFirstLetter(char firstLetter) {
        String sql = "SELECT * FROM Games WHERE title LIKE ? ORDER BY title ASC";
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstLetter + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                sb.append("Title: ").append(rs.getString("title")).append("\n")
                  .append("Platform: ").append(rs.getString("platform")).append("\n")
                  .append("Genre: ").append(rs.getString("genre")).append("\n")
                  .append("Release Year: ").append(rs.getInt("releaseYear")).append("\n")
                  .append("Max Players: ").append(rs.getString("maxPlayers")).append("\n\n");
            }
        } catch (SQLException e) {
            System.out.println("Error searching games: " + e.getMessage());
        }
        return sb.toString();
    }

    public boolean updatePlatform(String title, String newPlatform) {
        String sql = "UPDATE Games SET platform = ? WHERE title = ?";
        return executeUpdate(sql, newPlatform, title);
    }

    public boolean updateReleaseYear(String title, Integer newYear) {
        String sql = "UPDATE Games SET releaseYear = ? WHERE title = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newYear);
            pstmt.setString(2, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating release year: " + e.getMessage());
            return false;
        }
    }

    public boolean updateMaxPlayers(String title, String newMaxPlayers) {
        String sql = "UPDATE Games SET maxPlayers = ? WHERE title = ?";
        return executeUpdate(sql, newMaxPlayers, title);
    }

    public boolean updateGenre(String title, String newGenre) {
        String sql = "UPDATE Games SET genre = ? WHERE title = ?";
        return executeUpdate(sql, newGenre, title);
    }

    public boolean deleteGame(String title) {
        String sql = "DELETE FROM Games WHERE title = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting game: " + e.getMessage());
            return false;
        }
    }

    private boolean executeUpdate(String sql, String value, String title) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, value);
            pstmt.setString(2, title);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error executing update: " + e.getMessage());
            return false;
        }
    }

}
