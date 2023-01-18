package com.example.tictacto.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class PlayerStats {

    private UUID gameID;
    private LocalDate date;
    private Player opponent;

    public PlayerStats(UUID gameID, LocalDate date, Player opponent) {
        this.gameID = gameID;
        this.date = date;
        this.opponent = opponent;
    }

    public LocalDate getDate() {
        return date;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setGameID(UUID gameID) {
        this.gameID = gameID;
    }

    public UUID getGameID() {
        return gameID;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
