package com.example.tictacto;

import com.example.tictacto.model.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;


public class GameController {

    private Stage thisStage;
    private final PlayerSetupController playerSetupController;
    private Game game;

    @FXML
    private Label player1name;

    public GameController(PlayerSetupController playerSetupController) {
        this.playerSetupController = playerSetupController;
        System.out.println("new game controller");

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
            // Set this class as the controller
            loader.setController(this);
            // Load the scene
            thisStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showStage() {
        thisStage.showAndWait();
    }

    @FXML
    private void initialize() {
        this.game = playerSetupController.getGame();
        player1name.setText(game.getPlayers().get(0).getName());
        System.out.println("name: " + game.getPlayers().get(0).getName());

    }
}
