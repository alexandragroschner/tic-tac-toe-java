package com.example.tictacto.model;

public class Player {
    private String name;
    private Boolean isCircle;

    public Player(String name, Boolean isCircle) {
        this.name = name;
        this.isCircle = isCircle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCircle() {
        return isCircle;
    }

    public void setCircle(Boolean circle) {
        isCircle = circle;
    }
}
