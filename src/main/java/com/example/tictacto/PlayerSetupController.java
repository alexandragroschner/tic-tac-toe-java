package com.example.tictacto;

import com.example.tictacto.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A fxml controller class connected to setup-player.fxml
 * Class to handle setting up Human and/or Computer players
 * Setup includes choosing a name for Human players and loading profile pictures
 */

public class PlayerSetupController {

    private final Stage thisStage;
    private final InitController initController;
    private Game game;
    @FXML
    private Label chosenModeText;
    @FXML
    private TextField name;
    @FXML
    private Button next;
    @FXML
    private ImageView profilePic;

    public PlayerSetupController(InitController initController) {
        // gets the Initcontroller instance so it has access to the previously created game
        this.initController = initController;

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("setup-player.fxml"));
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
        next.setOnAction(event -> {
            try {
                onNextClick();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        chosenModeText.setText(initController.getChosenGameMode());
        this.game = initController.getGame();
        // loads new profile picture
        new ProfilePicClient(profilePic).start();

    }

    public Game getGame() {
        return this.game;
    }

    /**
     * Depending on chosen game mode creates either 2 Human Players or 1 Human and 1 Computer Player automically
     * @throws Exception
     */
    @FXML
    protected void onNextClick() throws Exception {
        // Human vs Computer mode
        if (chosenModeText.getText().equals(GameMode.HVC.toString())) {
            System.out.println("H VS C");

            game.addPlayer(new HumanPlayer(name.getText(), profilePic.getImage().getUrl()));
            // automatically add computer player with a new profile pic
            game.addPlayer(new ComputerPlayer(new ProfilePicClient(profilePic).getProfilePic().getUrl()));

        } else if (chosenModeText.getText().equals(GameMode.HVH.toString())) {
            System.out.println("H VS H");

            game.addPlayer(new HumanPlayer(name.getText(), profilePic.getImage().getUrl()));
            // reset name label and load new profile pic for second player
            name.setText("");
            profilePic.setImage(new Image("loading.png"));
            new ProfilePicClient(profilePic).start();

        } else {
            throw new Exception("Something wrong with the game mode");
        }
        // loads the game view as soon as the game is ready to be played
        if (game.isReady()) {
            try {
                loadGameView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadGameView() throws IOException {
        GameController gameController = new GameController((this));
        gameController.showStage();
    }
}
