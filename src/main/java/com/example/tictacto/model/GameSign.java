package com.example.tictacto.model;

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
