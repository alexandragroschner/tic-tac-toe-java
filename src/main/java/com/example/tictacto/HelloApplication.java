package com.example.tictacto;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        /*Game game = new Game();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("init.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 600, 400);
        GameController gameController = fxmlLoader.getController();
        gameController.setGameInstance(game,stage);
        stage.setTitle("TIC TAC TOE!");
        stage.setScene(scene);
        stage.show(); */

        InitController initController = new InitController();
        initController.showStage();
    }

    public static void main(String[] args) {
        launch();
    }

    public static class PlayerSetupController {
    }
}