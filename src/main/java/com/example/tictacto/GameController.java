package com.example.tictacto;

import com.example.tictacto.model.Game;
import com.example.tictacto.model.GameMode;
import com.example.tictacto.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label chosenModeText;
    @FXML
    private TextField name;
    @FXML
    private Button hVSh, hVSc;

    private int counter = 0;
    private Game game = new Game();

    @FXML
    protected void onHVSHButtonClick(ActionEvent event) {
        try {
            loadPlayerSetup(GameMode.HVH.getGameMode(), event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        game.setMode(GameMode.HVH);
        System.out.println("Started new game which is ready? " + game.isReady());
    }

    @FXML
    protected void onHVSCButtonClick(ActionEvent event) {
        try {
            loadPlayerSetup(GameMode.HVC.getGameMode(), event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        game.setMode(GameMode.HVC);
    }
    @FXML
    protected void onNextClick(ActionEvent event) throws Exception {
        if (chosenModeText.getText().equals(GameMode.HVC.getGameMode())) {
            System.out.println("computer player setup");
        } else if (chosenModeText.getText().equals(GameMode.HVH.getGameMode())) {
                try {
                    loadPlayerSetup(GameMode.HVH.getGameMode(), event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                game.addPlayer(new Player(name.getText(), true));
                System.out.println("Added player " + name.getText());
                if (game.isReady()) {
                    try {
                        loadGameView(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

        } else {
            System.out.println("Got: " + chosenModeText.getText());
        }

    }

    private void loadGameView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadPlayerSetup(String mode, ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("setup-player.fxml"));
        /*Parent root = FXMLLoader.load(getClass().getResource("setup-player.fxml"));
        Scene scene = hVSh.getScene();
        scene.setRoot(root);
        */
        /*try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }*/
        /*fxmlLoader.setController(this);

        Stage window = (Stage) hVSh.getScene().getWindow();
        //chosenModeText.setText(mode);
        window.setScene(new Scene(fxmlLoader.load(), 600, 400)); */

        root = FXMLLoader.load(getClass().getResource("setup-player.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }



    private void startGame() {
        // load game fxml
        // new Game() with previously created players
    }
}
