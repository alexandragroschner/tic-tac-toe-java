package com.example.tictacto.model;

import java.util.ArrayList;

public class Game {
    private GameMode mode;
    private String[][] gameField;

    private ArrayList<Player> players;
    private Player currentPlayer;
    private Player nextPlayer;
    private Boolean isOver;
    private Boolean isReady;

    public Game() {
        players = new ArrayList<Player>();
        System.out.println("A new game was created");
        System.out.println("Game mode is: " + mode);
    }
    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public void addPlayer(Player player) throws Exception {
        if (players.size() < 2) {
            players.add(player);
            if (players.size() == 1) {

            }
        } else {
            throw new Exception("Too many players");
        }
    }

    public String[][] getGameField() {
        return gameField;
    }

    public void setGameField(String[][] gameField) {
        this.gameField = gameField;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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

    public void switchPlayerTurn(Player currentPlayer) {
        if (currentPlayer.equals(players.get(0))) {
            setCurrentPlayer(players.get(1));
        } else {
            setCurrentPlayer(players.get(0));
        }
    }

    public Boolean isOver() {
        return isOver;
    }

    public Boolean isReady() {
        System.out.println("Game has " + players.size() + " players");
        if (players == null || mode == null) {
            return false;
        } else return players.size() == 2;

        /*
        if (players == null) {
            return false;
        } else if (players.size() == 2) {
            return true;
        }
        return false;
         */
    }
}
