package com.example.tictacto.model;

import java.util.ArrayList;

import static com.example.tictacto.model.SignCounter.*;

/**
 * Class that extends Player super class.
 * Implements algorithm to make automatic turns.
 */
public class ComputerPlayer extends Player{
    public ComputerPlayer(String profilePicUrl) {
        super("Computer Player", profilePicUrl);
    }

    @Override
    public String getGamesWon() {
        return "no game stats for computer players";
    }

    /**
     *
     * @param game
     * @return position to place computer player sign on or null of no win can be made anymore
     */
    public ArrayList<Integer> makeTurn(Game game) {

        System.out.println("turn by computer player");
        ArrayList<Integer> position;

        // get the GameSign of the opponent
        GameSign opponentSign;
        if (this.getSign().equals(GameSign.SIGN_X)) {
            opponentSign = GameSign.SIGN_O;
        } else {
            opponentSign = GameSign.SIGN_X;
        }

        // check if this player is almost winning
        position = preventOrMakeWin(this.getSign(), game);
        if (position != null) {
            game.switchPlayerTurn(game.getCurrentPlayer());
            return position;
        }

        // check if opponent is almost winning and prevent that
        position = preventOrMakeWin(opponentSign, game);
        if (position != null) {
            game.switchPlayerTurn(game.getCurrentPlayer());
            return position;
        }

        // make a move that could lead to a win
        position = makeNextMove(opponentSign, game);
        game.switchPlayerTurn(game.getCurrentPlayer());
        return position;
    }

    /**
     * Can be used to check if a given sign is almost winning i.e. has 2 sign in either a row, column or diagonal
     * If the sign this player is passed, it will check if current player is almost winning and if yes, make the win
     * If the sign of the opponents player is passed, it will check if this player is almost loosing and will prevent that by placing this players sign
     * @param sign
     * @param game
     * @return position to make/prevent win or null if none could be found
     */
    private ArrayList<Integer> preventOrMakeWin(GameSign sign, Game game) {
        ArrayList<Integer> position = new ArrayList<>();
        // if player is almost winning 1. Diagonal, make/prevent the win
        if (countSignInDownDiagonal(sign, game.getGameField()) == 2) {
            for (int i = 0; i < 3; i++) {
                if (game.getGameField()[i][i] == null) {
                    game.getGameField()[i][i] = this.getSign().toString();
                    position.add(i);
                    position.add(i);
                    return position;
                }
            }
        }

        // if player is almost winning 2. Diagonal, make/prevent the win
        if (countSignInUpDiagonal(sign, game.getGameField()) == 2) {
            for (int i = 0; i < 3; i++) {
                int y = (2 * i -1) % 3;
                // turns negative remainder into modulo result
                if (y<0) y = 2;
                if (game.getGameField()[i][y] == null) {
                    game.getGameField()[i][y] = this.getSign().toString();
                    position.add(i);
                    position.add(y);
                    return position;
                }
            }
        }

        // if player almost won one of the columns, make/prevent the win
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

        // if player almost won one of the rows, make/prevent the win
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

    /**
     * checks all possibilities (rows, columns, diagonals) for potential wins i.e. no sign of the opponent and sets its sign
     * @param opponent GameSign of opponent
     * @param game
     * @return position to put sign on or null of no win is possible anymore
     */
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
