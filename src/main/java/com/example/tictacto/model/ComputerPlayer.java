package com.example.tictacto.model;

import java.util.ArrayList;

import static com.example.tictacto.model.SignCounter.*;

public class ComputerPlayer extends Player{
    public ComputerPlayer(String name, GameSign sign, String profilePicUrl) {
        super(name, sign, profilePicUrl);
    }

    @Override
    public String getGamesWon() {
        return "no game stats for computer players";
    }

    public ArrayList<Integer> makeTurn(Game game) {

        System.out.println("turn by computer player");
        ArrayList<Integer> position;

        GameSign opponentSign;
        if (this.getSign().equals(GameSign.SIGN_X)) {
            opponentSign = GameSign.SIGN_O;
        } else {
            opponentSign = GameSign.SIGN_X;
        }

        // check if myself is almost winning
        position = preventOrMakeWin(this.getSign(), game);
        if (position != null) {
            game.switchPlayerTurn(game.getCurrentPlayer());
            System.out.println("making a win with: " + position.get(0) + position.get(1));
            return position;
        }

        // check if opponent is almost winning
        position = preventOrMakeWin(opponentSign, game);
        if (position != null) {
            game.switchPlayerTurn(game.getCurrentPlayer());
            System.out.println("preventing a win with: " + position.get(0) + position.get(1));
            return position;
        }

        position = makeNextMove(opponentSign, game);
        game.switchPlayerTurn(game.getCurrentPlayer());
        if (position != null ) System.out.println("making next move with: " + position.get(0) + position.get(1));

        return position;
    }

    private ArrayList<Integer> preventOrMakeWin(GameSign sign, Game game) {
        ArrayList<Integer> position = new ArrayList<>();
        // if this player is almost winning 1. Diagonal, make/prevent the win
        if (countSignInDownDiagonal(sign, game.getGameField()) == 2) {
            for (int i = 0; i < 3; i++) {
                if (game.getGameField()[i][i] == null) {
                    game.getGameField()[i][i] = this.getSign().toString();  //before sign
                    position.add(i);
                    position.add(i);
                    return position;
                }
            }
        }

        // if this player is almost winning 2. Diagonal, make the win
        if (countSignInUpDiagonal(sign, game.getGameField()) == 2) {
            for (int i = 0; i < 3; i++) {
                int y = (2 * i -1) % 3;
                // turns negative remainder into modulo result
                if (y<0) y = 2;
                //System.out.println("checking: " + i + ", " + y);
                if (game.getGameField()[i][y] == null) {
                    game.getGameField()[i][y] = this.getSign().toString();
                    position.add(i);
                    position.add(y);
                    return position;
                }
            }
        }

        // if this player almost won one of the columns, make the win
        for (int c = 0; c < 3; c++) {
            if (countSignsInCol(sign, c, game.getGameField()) == 2) {
                for (int i = 0; i < 3; i++) {
                    if (game.getGameField()[i][c] == null) {
                        game.getGameField()[i][c] = this.getSign().toString();
                        position.add(i);
                        position.add(c);
                        return position;
                    }
                }
            }
        }

        // if this player almost won one of the rows, make the win
        for (int r = 0; r < 3; r++) {
            if (countSignsInRow(sign, r, game.getGameField()) == 2) {
                for (int i = 0; i < 3; i++) {
                    if (game.getGameField()[r][i] == null) {
                        game.getGameField()[r][i] = this.getSign().toString();
                        position.add(r);
                        position.add(i);
                        return position;
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Integer> makeNextMove(GameSign opponent, Game game) {
        ArrayList<Integer> position = new ArrayList<>();

        if (countSignInDownDiagonal(opponent, game.getGameField()) == 0) {
            for (int i = 0; i < 3; i++) {
                if (game.getGameField()[i][i] == null) {
                    game.getGameField()[i][i] = this.getSign().toString();
                    position.add(i);
                    position.add(i);
                    return position;
                }
            }
        }

        if (countSignInUpDiagonal(opponent, game.getGameField()) == 0) {
            for (int i = 0; i < 3; i++) {
                int y = (2 * i -1) % 3;
                // turns negative remainder into modulo result
                if (y<0) y = 2;
                System.out.println("checking: " + i + ", " + y);
                if (game.getGameField()[i][y] == null) {
                    game.getGameField()[i][y] = this.getSign().toString();
                    position.add(i);
                    position.add(y);
                    return position;
                }
            }
        }

        for (int c = 0; c < 3; c++) {
            if (countSignsInCol(opponent, c, game.getGameField()) == 0) {
                for (int i = 0; i < 3; i++) {
                    if (game.getGameField()[i][c] == null) {
                        game.getGameField()[i][c] = this.getSign().toString();
                        position.add(i);
                        position.add(c);
                        return position;
                    }
                }
            }
        }

        // if this player almost won one of the rows, make the win
        for (int r = 0; r < 3; r++) {
            if (countSignsInRow(opponent, r, game.getGameField()) == 0) {
                for (int i = 0; i < 3; i++) {
                    if (game.getGameField()[r][i] == null) {
                        game.getGameField()[r][i] = this.getSign().toString();
                        position.add(r);
                        position.add(i);
                        return position;
                    }
                }
            }
        }
        return null;
    }
}
