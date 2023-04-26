package com.example.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.client.Main.connect;

public class GameController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button surrender;

    public static Pane pawnPane;

    public static Piece[][] pieceTab = new Piece[8][8];

    static String playerColor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connect.sendMessage("disconnect");
            connect.stopConnection();

            connect.startConnection(2001);
            Thread listener = new Thread(new Listener());
            listener.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        AnchorPane anchor;
        anchor = (AnchorPane) surrender.getParent();

        pawnPane = new Pane();
        pawnPane.setMaxSize(480, 480);
        pawnPane.setMinSize(480, 480);
        pawnPane.relocate(0, 0);
        anchor.getChildren().add(pawnPane);

        String color = null;
        for(int row=0; row<8; row++)
        {
            for(int col=0; col<8; col++)
            {
                if(((row%2==1 && col%2==0) || (row%2==0 && col%2==1))  && (row<3 || row>4))
                {
                    if (row<3)
                    {
                        color = "red";
                    }
                    else if(row>4)
                    {
                        color = "white";
                    }

                    Piece piece = null;
                    try {
                        piece = new Piece(color, row, col);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    pieceTab[row][col] = piece;
                    pawnPane.getChildren().add(piece);
                }
            }
        }
    }
}