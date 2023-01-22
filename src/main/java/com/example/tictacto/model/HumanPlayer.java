package com.example.tictacto.model;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that extends Player super class.
 * Adds player statistics to Player
 */
public class HumanPlayer extends Player{

    private ArrayList<PlayerStat> stats;
    public HumanPlayer(String name, String profilePicUrl) {
        super(name, profilePicUrl);
        stats = new ArrayList<>();
    }

    @Override
    public String getGamesWon() {
        return String.valueOf(stats.size());
    }

    public int makeTurn(int x, int y, Game game) {
        if (game.getGameField()[x][y] == null) {
            game.getGameField()[x][y] = game.getCurrentPlayer().getSign().toString();

            game.switchPlayerTurn(game.getCurrentPlayer());

            // successful turn
            return 1;
        }
        return 0;
    }

    public void wonGame(Game game) throws IOException {
        for (PlayerStat stat: stats) {
            if (stat.getGameID() == game.getId()) return;
        }

        PlayerStat stat = new PlayerStat(game.getId(), game.getDate(), game.getCurrentPlayer().getName());
        stats.add(new PlayerStat(game.getId(), game.getDate(), game.getCurrentPlayer().getName()));

        stat.writeToFile(this.getName());
    }
}
