package com.example.tictacto.model;

import java.util.ArrayList;
import java.util.UUID;

public class Player {
    private String name;
    private GameSign sign;
    private String profilePicUrl;
    private ArrayList<PlayerStats> stats;

    public Player(String name, GameSign sign, String profilePicUrl) {
        this.name = name;
        this.sign = sign;
        this.profilePicUrl = profilePicUrl;
        stats = new ArrayList<>();
        System.out.println("Created player with url: " + profilePicUrl);
    }

    public String getName() {
        return name;
    }

    public int getGamesWon() {
        return stats.size();
    }

    public void wonGame(Game game) {
        for (PlayerStats stat: stats) {
            if (stat.getGameID() == game.getId()) return;
        }
        stats.add(new PlayerStats(game.getId(), game.getDate(), game.getCurrentPlayer()));

        for (PlayerStats stat: stats) {
            System.out.println(stat.getGameID() + ", " + stat.getDate() + ", " + stat.getOpponent().getName());
        }
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
