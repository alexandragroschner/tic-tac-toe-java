package com.example.tictacto.model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.ArrayList;

public class Player {
    private String name;
    private GameSign sign;
    private String profilePicUrl;
    private ArrayList<PlayerStat> stats;

    public Player(String name, GameSign sign, String profilePicUrl) {
        this.name = name;
        this.sign = sign;
        this.profilePicUrl = profilePicUrl;
        stats = new ArrayList<>();
        System.out.println("Created player with url: " + profilePicUrl);
    }

    public java.lang.String getName() {
        return name;
    }

    public int getGamesWon() {
        return stats.size();
    }

    public void wonGame(Game game) throws IOException {
        for (PlayerStat stat: stats) {
            if (stat.getGameID() == game.getId()) return;
        }

        PlayerStat stat = new PlayerStat(game.getId(), game.getDate(), game.getCurrentPlayer().getName());
        stats.add(new PlayerStat(game.getId(), game.getDate(), game.getCurrentPlayer().getName()));

        System.out.println("JSON: " + stat.writeToJson());
        stat.writeToFile(this.getName());

        /*for (PlayerStat s: stats) {
            System.out.println(s.getGameID() + ", " + s.getDate() + ", " + s.getOpponent().getName());
        }*/
    }

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
