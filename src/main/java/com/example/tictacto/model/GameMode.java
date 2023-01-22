package com.example.tictacto.model;

/**
 * Enum for existing game modes
 */
public enum GameMode {
    HVH("Human VS Human"),
    HVC("Human VS Computer");

    private final String mode;

    GameMode(final String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return mode;
    }
}
