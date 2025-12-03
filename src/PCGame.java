/*
Name: Jessie Sosniak
Date: 03 DEC 2025
Assignment: SDC330 Course Project
Details: Represents a PC game. Platform is always auto-filled as "PC". 
Extends AbstractGame and implements GameInterface.
*/


public class PCGame extends AbstractGame {

    //Constructor

    public PCGame(String title, Integer releaseYear, String maxPlayers, String genre) {
        //Platform is always "PC"
        super(title, "PC", releaseYear, maxPlayers, genre);
    }

    //Display

    @Override
    public String displayDetails() {
        //Optionally override to emphasize PC platform
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(getTitle()).append("\n");
        sb.append("Platform: PC\n"); // explicitly show PC
        sb.append("Genre: ").append(getGenre()).append("\n");
        sb.append("Release Year: ").append(getReleaseYear() != null ? getReleaseYear() : "N/A").append("\n");
        sb.append("Max Players: ").append(getMaxPlayers() != null ? getMaxPlayers() : "N/A").append("\n");
        return sb.toString();
    }
}
