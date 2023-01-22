package com.example.tictacto.model;

public class SignCounter {
    static public int countSignsInCol(GameSign sign, int col, String[][] gameField) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (gameField[i][col] != null) {
                if (gameField[i][col].equals(sign.toString())) count++;
            }
        }
        return count;
    }

    static public int countSignsInRow(GameSign sign, int row, String[][] gameField) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (gameField[row][i] != null) {
                if (gameField[row][i].equals(sign.toString())) count++;
            }
        }
        return count;
    }

    static public int countSignInDownDiagonal(GameSign sign, String[][] gameField) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (gameField[i][i] != null) {
                if (gameField[i][i].equals(sign.toString())) count++;
            }
        }
        return count;
    }

    static public int countSignInUpDiagonal(GameSign sign, String[][] gameField) {
        int count = 0;
        // modulo gets [0][2], [1][1], [2][0]
        for (int i = 0; i < 3; i++) {
            int y = (2 * i -1) % 3;
            // turns negative remainder into modulo result
            if (y<0) y = 2;
            if (gameField[i][y] != null) {
                if (gameField[i][y].equals(sign.toString())) count++;
            }
        }
        return count;
    }
}
