/*
Name: Jessie Sosniak
Date: 26 NOV 2025
Assignment: SDC330 Course Project
Details: Represents a Nintendo handheld game (GameBoy, DS, SwitchLite). 
Extends NintendoGame and implements choosePlatform().
*/


public class NintendoHandheld extends NintendoGame {

    //Constructor

    public NintendoHandheld(String title, String platformChoice, Integer releaseYear, String maxPlayers, String genre) {
        super(title, releaseYear, maxPlayers, genre);
        choosePlatform(platformChoice);
    }

    //Platform Selection

    @Override
    protected void choosePlatform(String choice) {
        if (choice == null) {
            setPlatform("Nintendo Handheld");
            return;
        }
        String norm = choice.trim().toLowerCase();
        switch (norm) {
            case "gameboy":
                setPlatform("GameBoy");
                break;
            case "ds":
                setPlatform("DS");
                break;
            case "switchlite":
            case "switch lite":
                setPlatform("SwitchLite");
                break;
            default:
                setPlatform("Nintendo Handheld"); //fallback
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
