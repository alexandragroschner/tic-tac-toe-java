package com.example.tictacto.model;

/**
 * Enum for existing game signs
 */
public enum GameSign {

    SIGN_X("X"),
    SIGN_O("O");

    private final String sign;

    GameSign(final String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
}
