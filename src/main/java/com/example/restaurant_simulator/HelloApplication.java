package com.example.restaurant_simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("simulator-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 900);
        stage.setTitle("Simulator_Restaurant");
        stage.setScene(scene);
        stage.setResizable(Boolean.FALSE);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}