package com.example.tictacto.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class PlayerStat {

    private UUID gameID;
    private LocalDateTime date;
    private String opponentName;

    public PlayerStat(UUID gameID, LocalDateTime date, String opponentName) {
        this.gameID = gameID;
        this.date = date;
        this.opponentName = opponentName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setGameID(UUID gameID) {
        this.gameID = gameID;
    }

    public UUID getGameID() {
        return gameID;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String writeToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper.writeValueAsString(this);
    }

    public void writeToFile(String filename) throws IOException {
        FileWriter myWriter = new FileWriter( "src/main/resources/playerstats/" + filename + ".json", true);
        myWriter.write(writeToJson() + System.lineSeparator());
        myWriter.close();
    }
}
