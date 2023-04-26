package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.client.LoginController.login;
import static com.example.client.Main.connect;
import static com.example.client.SettingsController.boardTextureSRC;

/**
 * obsluga okna rozgrywki
 */
public class GameController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button surrender;
    @FXML
    private Text matchResult;
    @FXML
    private Text redPlayer;
    @FXML
    private Text whitePlayer;
    @FXML
    private Button backToMenu;
    @FXML
    private Button draw;
    @FXML
    private Button yes;
    @FXML
    private Button no;
    @FXML
    private Text drawOffer;
    @FXML
    private ImageView boardTexture;

    public static Pane pawnPane;

    public static Piece[][] board;

    public static String playerColor;
    public static boolean isMyTurn = false, isGameEnded = false;

    /**
     * inicjowanie zmiennych wartosciami poczatkowymi
     * @param url nieuzywany
     * @param resourceBundle nieuzywany
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            boardTexture.setImage(new Image(new FileInputStream(boardTextureSRC)));
            board = new Piece[8][8];
            isMyTurn = false;
            isGameEnded = false;
            playerColor = null;
            backToMenu.setVisible(false);
            yes.setVisible(false);
            no.setVisible(false);
            connect.sendMessage("disconnect");
            connect.stopConnection();

            connect.startConnection(2001);
            Thread listener = new Thread(new Listener(matchResult, redPlayer, whitePlayer, backToMenu, draw, yes, no, drawOffer));
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
                    else
                    {
                        color = "white";
                    }

                    Piece piece = null;
                    try {
                        piece = new Piece(color, row, col, whitePlayer, redPlayer);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    board[row][col] = piece;
                    pawnPane.getChildren().add(piece);
                }
            }
        }
    }

    /**
     * wyslanie wiadomosci o poddaniu sie do serwera po wcisnieciu przycisku
     * @param event zdarzenie
     * @throws Exception
     */
    public void onSurrenderButtonClick(ActionEvent event) throws Exception {
        if(!isGameEnded && playerColor != null) {
            connect.sendMessage("surrender,"+playerColor);
        }
    }

    /**
     * powrot do menu po wcisnieciu przycisku
     * @param event
     * @throws Exception
     */
    public void onBackToMenuClick(ActionEvent event) throws Exception {

        connect.sendMessage("disconnect");
        connect.startConnection(2000);
        connect.sendMessage("reconnect,"+login);

        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * wyslanie wiadomosci o propozycji remisu do serwera po wcisnieciu przycisku
     * @param event
     * @throws Exception
     */
    public void onDrawButtonClick(ActionEvent event) throws Exception {
        if(!isGameEnded && playerColor != null) {
            connect.sendMessage("drawOffer");
            draw.setVisible(false);
        }
    }

    /**
     * wyslanie wiadomosci o zaakceptowaniu remisu do serwera po wcisnieciu przycisku
     * @param event
     * @throws Exception
     */
    public void onYesButtonClick(ActionEvent event) throws Exception {
        connect.sendMessage("drawResponse,yes");
        draw.setVisible(true);
        yes.setVisible(false);
        no.setVisible(false);
        drawOffer.setText("");
    }

    /**
     * wyslanie wiadomosci o nie zaakceptowaniu remisu do serwera po wcisnieciu przycisku
     * @param event
     * @throws Exception
     */
    public void onNoButtonClick(ActionEvent event) throws Exception {
        connect.sendMessage("drawResponse,no");
        draw.setVisible(true);
        yes.setVisible(false);
        no.setVisible(false);
        drawOffer.setText("");
    }
}