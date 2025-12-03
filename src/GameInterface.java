/*
Name: Jessie Sosniak
Date: 03 DEC 2025
Assignment: SDC330 Course Project
Details: Defines the contract that every game class must follow, requiring
consistent getters, setters, and a displayDetails() method so that all game 
types (PC, Playstation, Nintendo) can be managed uniformly through polymorphism.
*/

public interface GameInterface {

    //Required Getters

    String getTitle();
    String getPlatform();
    String getGenre();
    Integer getReleaseYear(); //nullable
    String getMaxPlayers();   //nullable, can be numeric or "Single Player"/"MMO"

    //Required Setters

    void setPlatform(String platform);
    void setGenre(String genre);
    void setReleaseYear(Integer year);
    void setMaxPlayers(String maxPlayers);

    //Display

    String displayDetails(); //formatted string for printing game info
}
