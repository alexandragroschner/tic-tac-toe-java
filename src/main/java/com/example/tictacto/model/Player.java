package com.example.tictacto.model;

/**
 * Abstract class that serves can be used as super class by specific player subclasses
 */
public abstract class Player {
    private String name;
    private GameSign sign;
    private String profilePicUrl;

    public Player(String name, GameSign sign, String profilePicUrl) {
        this.name = name;
        this.sign = sign;
        this.profilePicUrl = profilePicUrl;
    }

    public void setSign(GameSign sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public abstract String getGamesWon();

    public GameSign getSign() {
        return sign;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }
}
