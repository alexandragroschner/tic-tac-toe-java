package com.example.tictacto;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        InitController initController = new InitController();
        initController.showStage();
    }

    public static void main(String[] args) {
        launch();
    }
}