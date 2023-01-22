package com.example.tictacto.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public UUID getGameID() {
        return gameID;
    }

    public void writeToFile(String filename) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/" + filename + ".json");

        InputStream input = PlayerStat.class.getClassLoader()
                .getResourceAsStream(filename + ".json");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode statObject = mapper.createObjectNode();

        statObject.put("date", this.date.toString());
        statObject.put("opponent", this.opponentName);

        // if file exists read json and append to it
        if (input != null) {
            // read existing root node form file
            JsonNode rootNode = mapper.readTree(input);

            ((ObjectNode) rootNode).set(gameID.toString(), statObject);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
        } else {
            // create a new root node
            ObjectNode rootNode = mapper.createObjectNode();

            rootNode.set(gameID.toString(), statObject);

            // create non-existing file
            Path newFilePath = Paths.get("src/main/resources/" + filename + ".json");
            Files.createFile(newFilePath);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, rootNode);
        }
    }
}
