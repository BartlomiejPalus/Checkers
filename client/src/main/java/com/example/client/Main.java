package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * glowna klasa programu
 */
public class Main extends Application {

    public static Connection connect = new Connection();

    /**
     * uruchamia okno programu i pierwsza scene
     * @param stage glowny stage
     */
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene scene = new Scene(root, 1280, 720);
            stage.setTitle("Warcaby");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

            connect.startConnection(2000);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}