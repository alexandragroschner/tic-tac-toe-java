package com.example.tictacto.model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Player {
    private String name;
    private GameSign sign;
    private String profilePicUrl;


    public Player(String name, GameSign sign, String profilePicUrl) {
        this.name = name;
        this.sign = sign;
        this.profilePicUrl = profilePicUrl;
        System.out.println("Created player with url: " + profilePicUrl);
    }

    public java.lang.String getName() {
        return name;
    }

    public abstract String getGamesWon();

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public GameSign getSign() {
        return sign;
    }

    public void setSign(GameSign sign) {
        this.sign = sign;
    }

    public java.lang.String getProfilePicUrl() {
        return profilePicUrl;
    }
}
