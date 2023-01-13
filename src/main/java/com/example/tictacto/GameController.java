package com.example.tictacto;

import com.example.tictacto.model.Game;
import com.example.tictacto.model.GameMode;
import com.example.tictacto.model.GameSign;
import com.example.tictacto.model.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class GameController {

    private Stage thisStage;
    private final PlayerSetupController playerSetupController;
    private Game game;

    @FXML
    private Label currentplayername;
    @FXML
    private Label errorLabel;
    @FXML
    private GridPane gameField;
    @FXML
    private Button lt;
    @FXML
    private Button lm;
    @FXML
    private Button lb;
    @FXML
    private Button mt;
    @FXML
    private Button mm;
    @FXML
    private Button mb;
    @FXML
    private Button rm;
    @FXML
    private Button rt;
    @FXML
    private Button rb;
    public GameController(PlayerSetupController playerSetupController) {
        this.playerSetupController = playerSetupController;

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
        lm.setOnAction(event -> clickLM());
        lb.setOnAction(event -> clickLB());
        mt.setOnAction(event -> clickMT());
        mm.setOnAction(event -> clickMM());
        mb.setOnAction(event -> clickMB());
        rt.setOnAction(event -> clickRT());
        rm.setOnAction(event -> clickRM());
        rb.setOnAction(event -> clickRB());

        currentplayername.setText(game.getPlayers().get(0).getName());
        game.setCurrentPlayer(game.getPlayers().get(0));

        //getNodeByRowColumnIndex(0,0,gameField).setText("bla");

    }

    private void clickLT() {
        if (game.makeTurn(0,0, game.getCurrentPlayer()) == 1) {
            // change button label to sign of current player
            lt.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());

            if (game.getMode() == GameMode.HVC) {
                getNodeByRowColumnIndex(game.makeComputerPlayerTurn(), gameField).setText("bla");
            }
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }

    private void clickLM() {
        if (game.makeTurn(0,1, game.getCurrentPlayer()) == 1) {
            // change button label to sign of current player
            lm.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }
    private void clickLB() {
        if (game.makeTurn(0,2, game.getCurrentPlayer()) == 1) {
            // change button label to sign of current player
            lb.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }
    private void clickMT() {
        if (game.makeTurn(1,0, game.getCurrentPlayer()) == 1) {
            // change button label to sign of current player
            mt.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }
    private void clickMM() {
        if (game.makeTurn(1,1, game.getCurrentPlayer()) == 1) {
            // change button label to sign of current player
            mm.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }
    private void clickMB() {
        if (game.makeTurn(1,2, game.getCurrentPlayer()) == 1) {
            // change button label to sign of current player
            mb.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }
    private void clickRT() {
        if (game.makeTurn(2,0, game.getCurrentPlayer()) == 1) {
            // change button label to sign of player i.e. "nextPlayer" because "makeTurn" already switched the players
            rt.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }
    private void clickRM() {
        if (game.makeTurn(2,1, game.getCurrentPlayer()) == 1) {
            // change button label to sign of current player
            rm.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }
    private void clickRB() {
        if (game.makeTurn(2,2, game.getCurrentPlayer()) == 1) {
            // change button label to sign of current player
            rb.setText(game.getNextPlayer().getSign().toString());
            currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());
        } else {
            errorLabel.setText("Field already set. Try another one");
        }
    }

    public Button getNodeByRowColumnIndex (ArrayList<Integer> position, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if(GridPane.getRowIndex(node) == position.get(0) && GridPane.getColumnIndex(node) == position.get(1)) {
                result = node;
                break;
            }
        }

        return (Button) result;
    }

    private void updateGameAfterTurn(int x, int y, GameSign sign, Player currentPlayer) {
        System.out.println("prev player was " + game.getCurrentPlayer().getName());
        //game.getGameField()[x][y] = sign.toString();
        game.switchPlayerTurn(currentPlayer);
        // set the label to the new current player name
        currentplayername.setText(game.getCurrentPlayer().getName() + "with sign: " + game.getCurrentPlayer().getSign());

        System.out.println("now the turn is with: " + game.getCurrentPlayer().getName());
    }
}
