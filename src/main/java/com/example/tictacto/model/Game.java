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
        gameField = new String[3][3];
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
            // add a second computer player is mode is H vs C
            /*if (players.size() == 1 && mode == GameMode.HVC) {
                players.add(new Player( "Computer 1", GameSign.SIGN_O));
            }*/
        } else {
            throw new Exception("Too many players");
        }
    }

    public int makeTurn(int x, int y, Player currentPlayer) {
        if (gameField[x][y] == null) {
            gameField[x][y] = currentPlayer.getSign().toString();

            switchPlayerTurn(currentPlayer);

            /*if (getWinner() == null) {
                System.out.println("no winner yet");
            } else {
                System.out.println("winner is: " + getWinner().toString());
            }*/

            // successful turn
            return 1;
        }
        return 0;
    }

    public ArrayList<Integer> makeComputerPlayerTurn() {
        System.out.println("turn by computer player");
        ArrayList<Integer> position = new ArrayList<>();

        // just a test
        position.add(0);
        position.add(0);

        // make this configurable
        String sign = GameSign.SIGN_O.toString();



        return position;
    }

    public GameSign getWinner() {
        for (int i = 0; i < 3; i++) {
            if (getRowWinner(i) != null) {
                return getRowWinner(i);
            }
            if (getColWinner(i) != null) {
                return getColWinner(i);
            }
        }
        // either returns winner or null if no winner yet
        return getDiagonalWinner();
    }

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
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            if (gameField[row][i] != null) {
                if (gameField[row][i].equals(GameSign.SIGN_X.toString())) countX++;
                if (gameField[row][i].equals(GameSign.SIGN_O.toString())) countO++;
            }
        }
        if (countX == 3) return GameSign.SIGN_X;
        if (countO == 3) return GameSign.SIGN_O;
        return null;
    }

    private GameSign getColWinner (int col) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            if (gameField[i][col] != null) {
                if (gameField[i][col].equals(GameSign.SIGN_X.toString())) countX++;
                if (gameField[i][col].equals(GameSign.SIGN_O.toString())) countO++;
            }
        }
        if (countX == 3) return GameSign.SIGN_X;
        if (countO == 3) return GameSign.SIGN_O;
        return null;
    }

    private GameSign getDiagonalWinner () {
        int countX = 0;
        int countO = 0;

        for (int i = 0; i < 3; i++) {
            if (gameField[i][i] != null) {
                if (gameField[i][i].equals(GameSign.SIGN_X.toString())) countX++;
                if (gameField[i][i].equals(GameSign.SIGN_O.toString())) countO++;
            }
        }
        if (countX == 3) return GameSign.SIGN_X;
        if (countO == 3) return GameSign.SIGN_O;

        // reset counters for second diagonal
        countX = 0;
        countO = 0;

        // modulo gets [0][2], [1][1], [2][0]
        for (int i = 0; i < 3; i++) {
            int y = (2 * i -1) % 3;
            // turns negative remainder into modulo result
            if (y<0) y += 2;

            if (gameField[i][y] != null) {
                if (gameField[i][y].equals(GameSign.SIGN_X.toString())) countX++;
                if (gameField[i][y].equals(GameSign.SIGN_O.toString())) countO++;
            }
        }

        if (countX == 3) return GameSign.SIGN_X;
        if (countO == 3) return GameSign.SIGN_O;
        return null;
    }

    private void getResult() {

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
