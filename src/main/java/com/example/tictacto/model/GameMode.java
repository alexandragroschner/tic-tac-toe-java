package com.example.tictacto.model;

public enum GameMode {
    HVH("Human VS Human"),
    HVC("Human VS Computer");

    private String mode;

    GameMode(String mode) {
        this.mode = mode;
    }

    public String getGameMode() {
        return mode;
    }
}
