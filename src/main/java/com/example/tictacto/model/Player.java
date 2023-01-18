package com.example.tictacto.model;

public class Player {
    private String name;
    private GameSign sign;
    private String profilePicUrl;

    public Player(String name, GameSign sign, String profilePicUrl) {
        this.name = name;
        this.sign = sign;
        this.profilePicUrl = profilePicUrl;
        System.out.println("Created player with url: " + profilePicUrl);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameSign getSign() {
        return sign;
    }

    public void setSign(GameSign sign) {
        this.sign = sign;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }
}
