/*
Name: Jessie Sosniak
Date: 26 NOV 2025
Assignment: SDC330 Course Project
Details: Represents a PlayStation game. User must choose between 
PS1, PS2, PS3, PS4, or PS5. Extends AbstractGame and implements GameInterface.
*/

public class PSGame extends AbstractGame {

    //Constructor

    public PSGame(String title, String platformChoice, Integer releaseYear, String maxPlayers, String genre) {
        //Normalize platform choice to canonical values
        super(title, normalizePlatform(platformChoice), releaseYear, maxPlayers, genre);
    }

    //Platform Normalization

    private static String normalizePlatform(String choice) {
        if (choice == null) return "PlayStation";
        String norm = choice.trim().toLowerCase();
        switch (norm) {
            case "ps1": return "PS1";
            case "ps2": return "PS2";
            case "ps3": return "PS3";
            case "ps4": return "PS4";
            case "ps5": return "PS5";
            default:    return "PlayStation"; //fallback
        }
    }

    //Display

    @Override
    public String displayDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(getTitle()).append("\n");
        sb.append("Platform: ").append(getPlatform()).append("\n");
        sb.append("Genre: ").append(getGenre()).append("\n");
        sb.append("Release Year: ").append(getReleaseYear() != null ? getReleaseYear() : "N/A").append("\n");
        sb.append("Max Players: ").append(getMaxPlayers() != null ? getMaxPlayers() : "N/A").append("\n");
        return sb.toString();
    }
}
