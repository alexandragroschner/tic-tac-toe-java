package com.example.tictacto;

import com.example.tictacto.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerSetupController {

    private final Stage thisStage;
    private final InitController initController;
    private Game game;
    @FXML
    Label chosenModeText;
    @FXML
    private TextField name;
    @FXML
    private Button next;
    @FXML
    private ImageView profilePic;

    public PlayerSetupController(InitController initController) {
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
        new ProfilePicClient(profilePic).start();

    }

    public Game getGame() {
        return this.game;
    }
    public void setChosenGameMode(String mode) {
        chosenModeText.setText(mode);
    }

    @FXML
    protected void onNextClick() throws Exception {
        if (chosenModeText.getText().equals(GameMode.HVC.toString())) {
            System.out.println("H VS C");

            game.addPlayer(new HumanPlayer(name.getText(), GameSign.SIGN_X, profilePic.getImage().getUrl()));
            game.addPlayer(new ComputerPlayer("Computer 1", GameSign.SIGN_O, new ProfilePicClient(profilePic).getProfilePic().getUrl()));

        } else if (chosenModeText.getText().equals(GameMode.HVH.toString())) {
            System.out.println("H VS H");

            // TBD: do this prettier
            if (game.getPlayers().size() == 0) {
                game.addPlayer(new HumanPlayer(name.getText(), GameSign.SIGN_X, profilePic.getImage().getUrl()));
            } else {
                game.addPlayer(new HumanPlayer(name.getText(), GameSign.SIGN_O, profilePic.getImage().getUrl()));
            }

            System.out.println("Added player " + name.getText());
            name.setText("");
            profilePic.setImage(new Image("loading.png"));
            new ProfilePicClient(profilePic).start();

        } else {
            System.out.println("Got: " + chosenModeText.getText());
        }

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
