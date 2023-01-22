package com.example.tictacto.model;

public abstract class Player {
    private String name;
    private GameSign sign;
    private String profilePicUrl;

    public Player(String name, GameSign sign, String profilePicUrl) {
        this.name = name;
        this.sign = sign;
        this.profilePicUrl = profilePicUrl;
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
