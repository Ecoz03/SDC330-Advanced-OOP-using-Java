/*
Name: Jessie Sosniak
Date: 26 NOV 2025
Assignment: SDC330 Course Project
Details: Stub for database operations. Safe to compile and run now. 
         Next week, methods will be implemented with SQLite logic.
*/

public class DatabaseHandler {

    //Connection

    public void connect() {
        System.out.println("[DB] connect() called - database not yet implemented.");
    }

    public void createTables() {
        System.out.println("[DB] createTables() called - database not yet implemented.");
    }

    //CREATE

    public void addGame(String title, String platform, Integer releaseYear, String maxPlayers, String genre) {
        System.out.println("[DB] addGame() called - database not yet implemented.");
    }

    //READ

    public String infoGame(String title) {
        System.out.println("[DB] infoGame() called - database not yet implemented.");
        return null;
    }

    public String infoAllGames() {
        System.out.println("[DB] infoAllGames() called - database not yet implemented.");
        return "";
    }

    //UPDATE

    public void updatePlatform(String title, String newPlatform) {
        System.out.println("[DB] updatePlatform() called - database not yet implemented.");
    }

    public void updateReleaseYear(String title, Integer newYear) {
        System.out.println("[DB] updateReleaseYear() called - database not yet implemented.");
    }

    public void updateMaxPlayers(String title, String newMaxPlayers) {
        System.out.println("[DB] updateMaxPlayers() called - database not yet implemented.");
    }

    public void updateGenre(String title, String newGenre) {
        System.out.println("[DB] updateGenre() called - database not yet implemented.");
    }

    //DELETE

    public void deleteGame(String title) {
        System.out.println("[DB] deleteGame() called - database not yet implemented.");
    }
}