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

    private Stage thisStage;
    private final InitController initController;
    private Game game;

    private Stage stage;
    private Scene scene;
    private Parent root;

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
            /*try {
                loadPlayerSetup(GameMode.HVC.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
            game.addPlayer(new HumanPlayer(name.getText(), GameSign.SIGN_X, profilePic.getImage().getUrl()));
            game.addPlayer(new ComputerPlayer("Computer 1", GameSign.SIGN_O, new ProfilePicClient(profilePic).getProfilePic().getUrl()));

        } else if (chosenModeText.getText().equals(GameMode.HVH.toString())) {
            System.out.println("H VS H");
            /*try {
                loadPlayerSetup(GameMode.HVH.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/

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

    private void loadPlayerSetup(String mode) throws IOException {
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("setup-player.fxml"));
        root = loader.load();
        //GameController gameController = loader.getController();
        //gameController.setGameInstance(game, this.stage);
        //root = FXMLLoader.load(getClass().getResource("setup-player.fxml"));

        PlayerSetupController playerSetupController = loader.getController();
        playerSetupController.setChosenGameMode(mode);

        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    private void loadGameView() throws IOException {
        //root = FXMLLoader.load(getClass().getResource("game-view.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        //root = loader.load();
        //GameController gameController = loader.getController();
        //PlayerSetupController playerSetupController = loader.getController();

        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        /*Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();*/

        GameController gameController = new GameController((this));
        gameController.showStage();
    }

}
