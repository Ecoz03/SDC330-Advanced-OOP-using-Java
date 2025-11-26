/*
Name: Jessie Sosniak
Date: 26 NOV 2025
Assignment: SDC330 Course Project
Details: Abstract representation of maximum player count for a game. 
Subclasses: SinglePlayer, MMO, NumericPlayerCount.
*/

public abstract class PlayerCount {

    //Abstract Method

    public abstract String getValue();

    //Factory Helpers

    public static PlayerCount fromInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null; //nullable
        }
        String norm = input.trim().toLowerCase();
        if (norm.equals("single player") || norm.equals("singleplayer")) {
            return new SinglePlayer();
        }
        if (norm.equals("mmo")) {
            return new MMO();
        }
        try {
            int val = Integer.parseInt(norm);
            if (val > 0) {
                return new NumericPlayerCount(val);
            }
        } catch (NumberFormatException e) {
            //ignore, will fall through
        }
        return null; //invalid input
    }
}

//Single Player

class SinglePlayer extends PlayerCount {
    @Override
    public String getValue() {
        return "Single Player";
    }
}

//MMO

class MMO extends PlayerCount {
    @Override
    public String getValue() {
        return "MMO";
    }
}

//Numeric Player Count

class NumericPlayerCount extends PlayerCount {
    private final int count;

    public NumericPlayerCount(int count) {
        this.count = count;
    }

    @Override
    public String getValue() {
        return String.valueOf(count);
    }
}
