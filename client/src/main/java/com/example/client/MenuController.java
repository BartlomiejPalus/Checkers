package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.client.Main.connect;

/**
 * obsluga ekranu menu
 */
public class MenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * zmienia secene na game po wcisnieciu przycisku
     * @param event
     * @throws IOException
     */
    public void onGrajButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * zmienia secene na settings po wcisnieciu przycisku
     * @param event
     * @throws IOException
     */
    public void onUstawieniaButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * konczy polaczenie z serwerem i zamyka okno po wcisnieciu przycisku
     * @param event
     * @throws IOException
     */
    public void onWyjscieButtonClick(ActionEvent event) throws Exception {
        connect.stopConnection();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}