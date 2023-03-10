package com.example.tictacto;

import com.example.tictacto.model.Game;
import com.example.tictacto.model.GameMode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A fxml controller class connected to init.fxml
 * Class for initial choice of game mode
 * Creates first instance of game
 */
public class InitController {

    private final Stage thisStage;
    @FXML
    private Button hVSh, hVSc;
    private Game game = new Game();

    public InitController() {
        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("init.fxml"));
            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            thisStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void initialize() {
        hVSc.setOnAction(event -> onHVSCButtonClick());
        hVSh.setOnAction(event -> onHVSHButtonClick());
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    protected void onHVSHButtonClick() {
        try {
            loadPlayerSetup(GameMode.HVH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void onHVSCButtonClick() {
        try {
            loadPlayerSetup(GameMode.HVC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the mode on the previously created game and loads the player setup by creating the PlayerSetupController
     * @param mode
     * @throws IOException
     */
    private void loadPlayerSetup(GameMode mode) throws IOException {
        game.setMode(mode);
        PlayerSetupController playerSetupController = new PlayerSetupController(this);
        playerSetupController.showStage();
    }

    public Game getGame() {
        return game;
    }

    public String getChosenGameMode() {
        return game.getMode().toString();
    }

}
