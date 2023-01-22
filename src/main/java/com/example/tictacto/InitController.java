package com.example.tictacto;

import com.example.tictacto.model.Game;
import com.example.tictacto.model.GameMode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
        game.setMode(GameMode.HVH);
        try {
            loadPlayerSetup(GameMode.HVH.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void onHVSCButtonClick() {
        game.setMode(GameMode.HVC);
        try {
            loadPlayerSetup(GameMode.HVC.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPlayerSetup(String mode) throws IOException {
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
