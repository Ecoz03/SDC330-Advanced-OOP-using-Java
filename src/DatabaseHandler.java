/*
Name: Jessie Sosniak
Date: 03 DEC 2025
Assignment: SDC330 Course Project
Details: Stub for database operations. Safe to compile and run now. 
         Next week, methods will be implemented with SQLite logic.
*/

import java.sql.*; 

  

public class DatabaseHandler { 

  

    private static final String DB_URL = "jdbc:sqlite:games.db"; 

  

    //Connection 

    public Connection connect() { 

        try { 

            return DriverManager.getConnection(DB_URL); 

        } catch (SQLException e) { 

            System.out.println("[DB] Connection failed: " + e.getMessage()); 

            return null; 

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

        try (Connection conn = connect(); 

             Statement stmt = conn.createStatement()) { 

            stmt.execute(sql); 

            System.out.println("[DB] Tables created or verified."); 

        } catch (SQLException e) { 

            System.out.println("[DB] createTables() error: " + e.getMessage()); 

        } 

    } 

  

    //CREATE 

    public boolean addGame(String title, String platform, Integer releaseYear, String maxPlayers, String genre) { 

        String sql = "INSERT INTO Games(title, platform, genre, releaseYear, maxPlayers) VALUES(?,?,?,?,?)"; 

        try (Connection conn = connect(); 

             PreparedStatement pstmt = conn.prepareStatement(sql)) { 

            pstmt.setString(1, title); 

            pstmt.setString(2, platform); 

            pstmt.setString(3, genre); 

            if (releaseYear != null) { 

                pstmt.setInt(4, releaseYear); 

            } else { 

                pstmt.setNull(4, Types.INTEGER); 

            } 

            if (maxPlayers != null) { 

                pstmt.setString(5, maxPlayers); 

            } else { 

                pstmt.setNull(5, Types.VARCHAR); 

            } 

            pstmt.executeUpdate(); 

            return true; 

        } catch (SQLException e) { 

            System.out.println("[DB] addGame() error: " + e.getMessage()); 

            return false; 

        } 

    } 

  

    //READ 

    public String infoGame(String title) { 

        String sql = "SELECT * FROM Games WHERE LOWER(title) = LOWER(?)"; 

        try (Connection conn = connect(); 

             PreparedStatement pstmt = conn.prepareStatement(sql)) { 

            pstmt.setString(1, title); 

            ResultSet rs = pstmt.executeQuery(); 

            if (rs.next()) { 

                return formatGame(rs); 

            } 

        } catch (SQLException e) { 

            System.out.println("[DB] infoGame() error: " + e.getMessage()); 

        } 

        return null; 

    } 

  

    public String infoAllGames() { 

        StringBuilder sb = new StringBuilder(); 

        String sql = "SELECT * FROM Games"; 

        try (Connection conn = connect(); 

             Statement stmt = conn.createStatement(); 

             ResultSet rs = stmt.executeQuery(sql)) { 

            while (rs.next()) { 

                sb.append(formatGame(rs)).append("\n"); 

            } 

        } catch (SQLException e) { 

            System.out.println("[DB] infoAllGames() error: " + e.getMessage()); 

        } 

        return sb.toString(); 

    } 

  

    //UPDATE 

    public boolean updatePlatform(String title, String newPlatform) { 

        return updateField(title, "platform", newPlatform); 

    } 

  

    public boolean updateReleaseYear(String title, Integer newYear) { 

        return updateField(title, "releaseYear", newYear); 

    } 

  

    public boolean updateMaxPlayers(String title, String newMaxPlayers) { 

        return updateField(title, "maxPlayers", newMaxPlayers); 

    } 

  

    public boolean updateGenre(String title, String newGenre) { 

        return updateField(title, "genre", newGenre); 

    } 

  

    private boolean updateField(String title, String field, Object value) { 

        String sql = "UPDATE Games SET " + field + " = ? WHERE LOWER(title) = LOWER(?)"; 

        try (Connection conn = connect(); 

             PreparedStatement pstmt = conn.prepareStatement(sql)) { 

            if (value == null) { 

                pstmt.setNull(1, Types.NULL); 

            } else if (value instanceof Integer) { 

                pstmt.setInt(1, (Integer) value); 

            } else { 

                pstmt.setString(1, value.toString()); 

            } 

            pstmt.setString(2, title); 

            int rows = pstmt.executeUpdate(); 

            return rows > 0; 

        } catch (SQLException e) { 

            System.out.println("[DB] updateField() error: " + e.getMessage()); 

            return false; 

        } 

    } 

  

    //DELETE 

    public boolean deleteGame(String title) { 

        String sql = "DELETE FROM Games WHERE LOWER(title) = LOWER(?)"; 

        try (Connection conn = connect(); 

             PreparedStatement pstmt = conn.prepareStatement(sql)) { 

            pstmt.setString(1, title); 

            int rows = pstmt.executeUpdate(); 

            return rows > 0; 

        } catch (SQLException e) { 

            System.out.println("[DB] deleteGame() error: " + e.getMessage()); 

            return false; 

        } 

    } 

  

    //Helper to format game details 

    private String formatGame(ResultSet rs) throws SQLException { 

        return "Title: " + rs.getString("title") + "\n" + 

               "Platform: " + rs.getString("platform") + "\n" + 

               "Genre: " + rs.getString("genre") + "\n" + 

               "Release Year: " + (rs.getObject("releaseYear") != null ? rs.getInt("releaseYear") : "N/A") + "\n" + 

               "Max Players: " + (rs.getString("maxPlayers") != null ? rs.getString("maxPlayers") : "N/A"); 

    } 

} 