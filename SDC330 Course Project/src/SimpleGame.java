/*
Name: Jessie Sosniak
Date: 26 NOV 2025
Assignment: SDC330 Course Project
Details: Temporary generic game class for testing CRUD operations.
Extends AbstractGame and implements GameInterface. Later replaced 
by platform-specific subclasses (PCGame, PSGame, NintendoHandheld, 
NintendoConsole).
*/


public class SimpleGame extends AbstractGame {

    //Constructor

    public SimpleGame(String title, String platform, Integer releaseYear, String maxPlayers, String genre) {
        super(title, platform, releaseYear, maxPlayers, genre);
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
