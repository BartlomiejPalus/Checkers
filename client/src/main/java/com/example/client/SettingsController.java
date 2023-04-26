package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * obsluga ekranu ustawien
 */
public class SettingsController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static String boardTextureSRC = "src\\main\\resources\\com\\example\\client\\images\\board1.png";

    /**
     * wraca do menu po wcisnieciu przycisku
     * @param event
     * @throws IOException
     */
    public void onWrocButtonClick(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * wybiera wyglad planszy po wcisnieciu przycisku
     * @param event
     * @throws IOException
     */
    public void onButton1Click(ActionEvent event) throws IOException {
        boardTextureSRC="src\\main\\resources\\com\\example\\client\\images\\board1.png";
    }

    /**
     * wybiera wyglad planszy po wcisnieciu przycisku
     * @param event
     * @throws IOException
     */
    public void onButton2Click(ActionEvent event) throws IOException {
        boardTextureSRC="src\\main\\resources\\com\\example\\client\\images\\board2.png";
    }

    /**
     * wybiera wyglad planszy po wcisnieciu przycisku
     * @param event
     * @throws IOException
     */
    public void onButton3Click(ActionEvent event) throws IOException {
        boardTextureSRC="src\\main\\resources\\com\\example\\client\\images\\board3.png";
    }

    /**
     * wybiera wyglad planszy po wcisnieciu przycisku
     * @param event
     * @throws IOException
     */
    public void onButton4Click(ActionEvent event) throws IOException {
        boardTextureSRC="src\\main\\resources\\com\\example\\client\\images\\board4.png";
    }
}