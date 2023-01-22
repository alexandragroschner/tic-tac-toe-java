package com.example.tictacto.model;

/**
 * Abstract class that serves can be used as super class by specific player subclasses
 */
public abstract class Player {
    private String name;
    private GameSign sign;
    private String profilePicUrl;

    public Player(String name, String profilePicUrl) {
        this.name = name;
        this.profilePicUrl = profilePicUrl;
    }

    public void setSign(GameSign sign) {
        this.sign = sign;
    }

    public java.lang.String getName() {
        return name;
    }

    public abstract String getGamesWon();

    public GameSign getSign() {
        return sign;
    }

    public java.lang.String getProfilePicUrl() {
        return profilePicUrl;
    }
}
