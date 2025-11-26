/*
Name: Jessie Sosniak
Date: 26 NOV 2025
Assignment: SDC330 Course Project
Details: Abstract parent class for all Nintendo games. Extends AbstractGame 
and requires subclasses to implement choosePlatform(). Subclasses: NintendoHandheld, 
NintendoConsole.
*/

public abstract class NintendoGame extends AbstractGame {

    //Constructor

    public NintendoGame(String title, Integer releaseYear, String maxPlayers, String genre) {
        //Platform will be set by subclasses via choosePlatform()
        super(title, null, releaseYear, maxPlayers, genre);
    }

    //Abstract Method
    //Each subclass must implement logic to set the platform

    protected abstract void choosePlatform(String choice);

    //Display

    @Override
    public String displayDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(getTitle()).append("\n");
        sb.append("Platform: ").append(getPlatform() != null ? getPlatform() : "Nintendo (unspecified)").append("\n");
        sb.append("Genre: ").append(getGenre()).append("\n");
        sb.append("Release Year: ").append(getReleaseYear() != null ? getReleaseYear() : "N/A").append("\n");
        sb.append("Max Players: ").append(getMaxPlayers() != null ? getMaxPlayers() : "N/A").append("\n");
        return sb.toString();
    }
}
