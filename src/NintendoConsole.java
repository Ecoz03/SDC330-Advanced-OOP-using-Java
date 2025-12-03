/*
Name: Jessie Sosniak
Date: 03 DEC 2025
Assignment: SDC330 Course Project
Details: Represents a Nintendo console game (GameCube, Wii, Switch/Switch2). 
Extends NintendoGame and implements choosePlatform().
*/



public class NintendoConsole extends NintendoGame {

    //Constructor

    public NintendoConsole(String title, String platformChoice, Integer releaseYear, String maxPlayers, String genre) {
        super(title, releaseYear, maxPlayers, genre);
        choosePlatform(platformChoice);
    }

    //Platform Selection

    @Override
    protected void choosePlatform(String choice) {
        if (choice == null) {
            setPlatform("Nintendo Console");
            return;
        }
        String norm = choice.trim().toLowerCase();
        switch (norm) {
            case "gamecube":
                setPlatform("GameCube");
                break;
            case "wii":
                setPlatform("Wii");
                break;
            case "switch":
            case "switch2":
            case "switch/switch2":
                setPlatform("Switch/Switch2");
                break;
            default:
                setPlatform("Nintendo Console"); //fallback
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
