package com.example.tictacto;

import com.example.tictacto.model.Game;
import com.example.tictacto.model.GameSign;
import com.example.tictacto.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class GameController {

    private Stage thisStage;
    private final PlayerSetupController playerSetupController;
    private Game game;

    @FXML
    private Label currentplayername;

    @FXML
    private GridPane gameField;
    @FXML
    private Button lt;
    @FXML
    private Button mt;

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

        lt.setOnAction(event -> clickLT());
        mt.setOnAction(event -> clickLM());

        currentplayername.setText(game.getPlayers().get(0).getName());
        game.setCurrentPlayer(game.getPlayers().get(0));

    }

    private void clickLT() {
        // change button label to sign of current player
        // set sign in game.gameField array
        // change currentplayer
        lt.setText(game.getCurrentPlayer().getSign().toString());
        updateGameAfterTurn(0,0, game.getCurrentPlayer().getSign(), game.getCurrentPlayer());
    }

    private void clickLM() {
        mt.setText(game.getCurrentPlayer().getSign().toString());
        updateGameAfterTurn(1,0, game.getCurrentPlayer().getSign(), game.getCurrentPlayer());
    }

    private void updateGameAfterTurn(int x, int y, GameSign sign, Player currentPlayer) {
        game.getGameField()[x][y] = sign.toString();
        game.switchPlayerTurn(currentPlayer);
        System.out.println("Switched turns: current player is " + game.getCurrentPlayer());
        System.out.println("                next player will be " + game.getNextPlayer());
    }
}
