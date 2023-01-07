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
    private Scene scene;
    private Parent root;

    @FXML
    private Button hVSh, hVSc;

    private int counter = 0;
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

    public void setGameInstance(Game game, Stage stage) {
        this.game = game;
        //this.thisStage = stage;
    }

    protected void onHVSHButtonClick() {
        game.setMode(GameMode.HVH);
        //chosenModeText.setText(GameMode.HVH.toString());
        System.out.println("Started new game which is ready? " + game.isReady());
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

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("setup-player.fxml"));
        //root = loader.load();
        //GameController gameController = loader.getController();
        //gameController.setGameInstance(game, this.stage);
        //root = FXMLLoader.load(getClass().getResource("setup-player.fxml"));

        PlayerSetupController playerSetupController = new PlayerSetupController(this);
        playerSetupController.showStage();

        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        /*Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show(); */

    }

    public Game getGame() {
        return game;
    }

    public String getChosenGameMode() {
        return game.getMode().toString();
    }



    private void startGame() {
        // load game fxml
        // new Game() with previously created players
    }
}
