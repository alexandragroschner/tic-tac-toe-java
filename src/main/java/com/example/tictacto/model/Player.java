package com.example.tictacto.model;

public class Player {
    private String name;
    private GameSign sign;

    public Player(String name, GameSign sign) {
        this.name = name;
        this.sign = sign;
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
}
