/*
Name: Jessie Sosniak
Date: 26 NOV 2025
Assignment: SDC330 Course Project
Details: Acts as the in-memory controller for the project, handling all CRUD
operations (create, read, update, delete) and search functions on the game
inventory so that App.java can focus only on user interaction. 
*/

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private final List<AbstractGame> games = new ArrayList<>();

    //CREATE

    public boolean addGame(String title, String platform, Integer releaseYear, String maxPlayers, String genre) {
        if (exists(title)) {
            return false; //prevent duplicates
        }
        AbstractGame game = new SimpleGame(title, platform, releaseYear, maxPlayers, genre);
        games.add(game);
        return true;
    }

    //READ

    public String viewGameDetails(String title) {
        for (AbstractGame g : games) {
            if (g.getTitle().equalsIgnoreCase(title)) {
                return g.displayDetails();
            }
        }
        return null;
    }

    public String listAllGames() {
        if (games.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (AbstractGame g : games) {
            sb.append(g.displayDetails()).append("\n");
        }
        return sb.toString();
    }

    public String searchByFirstLetter(char firstLetter) {
        StringBuilder sb = new StringBuilder();
        for (AbstractGame g : games) {
            if (!g.getTitle().isEmpty() &&
                Character.toLowerCase(g.getTitle().charAt(0)) == firstLetter) {
                sb.append(g.displayDetails()).append("\n");
            }
        }
        return sb.toString();
    }

    //UPDATE

    public boolean updatePlatform(String title, String newPlatform) {
        AbstractGame g = findGame(title);
        if (g == null) return false;
        g.setPlatform(newPlatform);
        return true;
    }

    public boolean updateReleaseYear(String title, Integer newYear) {
        AbstractGame g = findGame(title);
        if (g == null) return false;
        g.setReleaseYear(newYear);
        return true;
    }

    public boolean updateMaxPlayers(String title, String newMaxPlayers) {
        AbstractGame g = findGame(title);
        if (g == null) return false;
        g.setMaxPlayers(newMaxPlayers);
        return true;
    }

    public boolean updateGenre(String title, String newGenre) {
        AbstractGame g = findGame(title);
        if (g == null) return false;
        g.setGenre(newGenre);
        return true;
    }

    //DELETE

    public boolean deleteGame(String title) {
        AbstractGame g = findGame(title);
        if (g == null) return false;
        games.remove(g);
        return true;
    }

    //HELPERS

    public boolean exists(String title) {
        return findGame(title) != null;
    }

    private AbstractGame findGame(String title) {
        for (AbstractGame g : games) {
            if (g.getTitle().equalsIgnoreCase(title)) {
                return g;
            }
        }
        return null;
    }
}
