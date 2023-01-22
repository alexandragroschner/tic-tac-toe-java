package com.example.tictacto.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.tictacto.model.SignCounter.*;

/**
 * class that represents tic tac toe game
 */
public class Game {

    private final UUID id;
    private final LocalDateTime date;
    private GameMode mode;
    private String[][] gameField;
    private ArrayList<Player> players;
    private Player currentPlayer;

    /**
     * Default constructor that initialized game
     */
    public Game() {
        id = UUID.randomUUID();
        date = LocalDateTime.now();
        players = new ArrayList<>();
        System.out.println("A new game was created");
        gameField = new String[3][3];
    }

    /**
     * constructor that can be used to start a new game with given players and game mode (for restarts)
     * @param players
     * @param mode
     */
    public Game(ArrayList<Player> players, GameMode mode) {
        this.players = players;
        this.mode = mode;
        id = UUID.randomUUID();
        date = LocalDateTime.now();
        gameField = new String[3][3];

        System.out.println("A new game was created");
    }
    public GameMode getMode() {
        return mode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public UUID getId() {
        return id;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    /**
     * Adds players to game.
     * The first added player always gets GameSign X, the second one GameSign O
     * @param player
     * @throws Exception
     */
    public void addPlayer(Player player) throws Exception {
        if (players.size() < 2) {
            players.add(player);
        } else {
            throw new Exception("Too many players");
        }
    }

    /**
     * Gets GameSign of Winner
     * @return GameSign of Winner or null if there is no winner yet
     */
    public GameSign getWinnerSign() {
        for (int i = 0; i < 3; i++) {
            if (getRowWinner(i) != null) {
                return getRowWinner(i);
            }
            if (getColWinner(i) != null) {
                return getColWinner(i);
            }
        }
        return getDiagonalWinner();
    }

    /**
     * Gets player with given GameSign
     * @param sign
     * @return player
     * @throws Exception
     */
    public Player getPlayerWithSign(GameSign sign) throws Exception {
        if (players.get(0).getSign() == sign) {
            return players.get(0);
        } else if (players.get(1).getSign() == sign){
            return players.get(1);
        } else {
            throw new Exception("No player found with that sign");
        }
    }

    private GameSign getRowWinner (int row) {
        if (countSignsInRow(GameSign.SIGN_X, row, this.gameField) == 3) return GameSign.SIGN_X;
        if (countSignsInRow(GameSign.SIGN_O, row, this.gameField) == 3) return GameSign.SIGN_O;
        return null;
    }

    private GameSign getColWinner (int col) {
        if (countSignsInCol(GameSign.SIGN_X, col, this.gameField) == 3) return GameSign.SIGN_X;
        if (countSignsInCol(GameSign.SIGN_O, col, this.gameField) == 3) return GameSign.SIGN_O;
        return null;
    }

    private GameSign getDiagonalWinner () {
        if (countSignInDownDiagonal(GameSign.SIGN_X, this.gameField) == 3) return GameSign.SIGN_X;
        if (countSignInDownDiagonal(GameSign.SIGN_O, this.gameField) == 3) return GameSign.SIGN_O;

        if (countSignInUpDiagonal(GameSign.SIGN_X, this.gameField) == 3) return GameSign.SIGN_X;
        if (countSignInUpDiagonal(GameSign.SIGN_O, this.gameField) == 3) return GameSign.SIGN_O;
        return null;
    }

    public String[][] getGameField() {
        return gameField;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getNextPlayer() {
        if (currentPlayer.equals(players.get(0))) {
            return players.get(1);
        } else {
            return players.get(0);
        }
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Can be used to switch the current player after a turn
     * @param currentPlayer
     */
    public void switchPlayerTurn(Player currentPlayer) {
        if (currentPlayer.equals(players.get(0))) {
            setCurrentPlayer(players.get(1));
        } else {
            setCurrentPlayer(players.get(0));
        }
    }

    /**
     * Game is ready if it has 2 players and a mode
     * If it is ready a random player is chosen for the first turn
     * @return
     */
    public Boolean isReady() {
        if (players == null || mode == null) {
            return false;
        } else if (players.size() == 2) {
            setCurrentPlayer(getRandomPlayer());
            return true;
        }
        return false;
    }

    /**
     * Returns the GameSign that wasn't chosen yet for remaining player.
     * @return sign that is still free or null if all or no sign is free.
     */
    public GameSign getRemainingFreeSign() {
        if (players.size() == 1) {
            System.out.println("player 1 has sign: " + players.get(0).getSign().toString());
            if (players.get(0).getSign() == GameSign.SIGN_X) return GameSign.SIGN_O;
            if (players.get(0).getSign() == GameSign.SIGN_O) return GameSign.SIGN_X;
        }
        return null;
    }

    private Player getRandomPlayer() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        return players.get(randomNum);
    }

    public boolean isTie() {
        int signCounter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] != null) signCounter++;
            }
        }
        return signCounter == 9;
    }
}
