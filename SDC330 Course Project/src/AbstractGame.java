/*
Name: Jessie Sosniak
Date: 26 NOV 2025
Assignment: SDC330 Course Project
Details: Abstract base class for all game types. 
*/

public abstract class AbstractGame implements GameInterface {

    //Fields

    private String title;
    private String platform;
    private String genre;
    private Integer releaseYear; //nullable
    private String maxPlayers;   //nullable

    //Constructor

    public AbstractGame(String title, String platform, Integer releaseYear, String maxPlayers, String genre) {
        this.title = title;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.maxPlayers = maxPlayers;
        this.genre = genre;
    }


    //Getters

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPlatform() {
        return platform;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public Integer getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String getMaxPlayers() {
        return maxPlayers;
    }


    //Setters
 
    @Override
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public void setReleaseYear(Integer year) {
        this.releaseYear = year;
    }

    @Override
    public void setMaxPlayers(String maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    //Display

    @Override
    public String displayDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Platform: ").append(platform).append("\n");
        sb.append("Genre: ").append(genre).append("\n");
        sb.append("Release Year: ").append(releaseYear != null ? releaseYear : "N/A").append("\n");
        sb.append("Max Players: ").append(maxPlayers != null ? maxPlayers : "N/A").append("\n");
        return sb.toString();
    }
}