/*
Name: Jessie Sosniak
Date: 03 DEC 2025
Assignment: SDC330 Course Project
Details: Acts as the in-memory controller for the project, handling all CRUD
operations (create, read, update, delete) and search functions on the game
inventory so that App.java can focus only on user interaction. 
*/

public class InventoryManager { 

  

    private final DatabaseHandler dbHandler = new DatabaseHandler(); 

  

    public InventoryManager() { 

        //Ensure database and tables are ready 

        dbHandler.createTables(); 

    } 

  

    //CREATE 

    public boolean addGame(String title, String platform, Integer releaseYear, String maxPlayers, String genre) { 

        return dbHandler.addGame(title, platform, releaseYear, maxPlayers, genre); 

    } 

  

    //READ 

    public String viewGameDetails(String title) { 

        return dbHandler.infoGame(title); 

    } 

  

    public String listAllGames() { 

        return dbHandler.infoAllGames(); 

    } 

  

    public String searchByFirstLetter(char firstLetter) { 

        String allGames = dbHandler.infoAllGames(); 

        if (allGames == null || allGames.isEmpty()) return ""; 

        StringBuilder sb = new StringBuilder(); 

        for (String block : allGames.split("\n\n")) { 

            if (!block.isEmpty() && Character.toLowerCase(block.charAt(7)) == firstLetter) { 

                sb.append(block).append("\n\n"); 

            } 

        } 

        return sb.toString(); 

    } 

  

    //UPDATE 

    public boolean updatePlatform(String title, String newPlatform) { 

        return dbHandler.updatePlatform(title, newPlatform); 

    } 

  

    public boolean updateReleaseYear(String title, Integer newYear) { 

        return dbHandler.updateReleaseYear(title, newYear); 

    } 

  

    public boolean updateMaxPlayers(String title, String newMaxPlayers) { 

        return dbHandler.updateMaxPlayers(title, newMaxPlayers); 

    } 

  

    public boolean updateGenre(String title, String newGenre) { 

        return dbHandler.updateGenre(title, newGenre); 

    } 

  

    //DELETE 

    public boolean deleteGame(String title) { 

        return dbHandler.deleteGame(title); 

    } 

  

    //HELPERS 

    public boolean exists(String title) { 

        return dbHandler.infoGame(title) != null; 

    } 

} 