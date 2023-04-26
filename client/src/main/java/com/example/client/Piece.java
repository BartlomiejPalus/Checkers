package com.example.client;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.example.client.CheckKingMove.kingMove;
import static com.example.client.CheckPawnMove.pieceMove;
import static com.example.client.GameController.*;
import static com.example.client.Main.connect;

/**
 * tworzenie i usuwanie pionkow
 */
public class Piece extends Pane {

    private ImageView view;
    private Image image, kingImage;

    private Text whitePlayer;
    private Text redPlayer;
    private double mouseX, mouseY;
    private double startX, startY;
    int row, col;
    int type;
    String color;

    /**
     * przypisuje parametry do zmiennych i tworzy nowego pionka
     * @param color         kolor pionka
     * @param row           pozycja pionka(wiersz)
     * @param col           pozycja pionka(kolumna)
     * @param whitePlayer   pole tekstowe z nickiem gracza bialego
     * @param redPlayer     pole tekstowe z nickiem gracza czerwonego
     * @throws FileNotFoundException
     */
    public Piece(String color, int row, int col, Text whitePlayer, Text redPlayer) throws FileNotFoundException {
        this.row = row;
        this.col = col;
        this.color = color;
        move(row, col);

        this.whitePlayer = whitePlayer;
        this.redPlayer = redPlayer;
        if(color.equals("red")) {
            image = new Image(new FileInputStream("src\\main\\resources\\com\\example\\client\\images\\redPawn.png"));
            kingImage = new Image(new FileInputStream("src\\main\\resources\\com\\example\\client\\images\\redKing.png"));
            type = -1;
        }
        else {
            image = new Image(new FileInputStream("src\\main\\resources\\com\\example\\client\\images\\whitePawn.png"));
            kingImage = new Image(new FileInputStream("src\\main\\resources\\com\\example\\client\\images\\whiteKing.png"));
            type = 1;
        }

        view = new ImageView(image);
        view.setImage(image);

        getChildren().addAll(view);

        setOnMousePressed(e -> {
            if(color.equals(playerColor) && isMyTurn) {
                mouseX = e.getSceneX();
                mouseY = e.getSceneY();
            }
        });

        setOnMouseDragged(e -> {
            if(color.equals(playerColor) && isMyTurn) {
                relocate(e.getSceneX() - mouseX + startX, e.getSceneY() - mouseY + startY);
            }
        });

        setOnMouseReleased(e -> {
            if(color.equals(playerColor) && isMyTurn) {
                int startRow = row;
                int startCol = col;
                putInRightPlace(e.getSceneX() - mouseX + startX, e.getSceneY() - mouseY + startY);
            }
        });
    }

    /**
     * oblicza nowe wspolrzedne pionka
     * @param row   pozycja pionka(wiersz)
     * @param col   pozycja pionka(kolumna)
     */
    public void move(int row, int col)
    {
        this.row = row;
        this.col = col;
        startX = col * 60 + 360;
        startY = row * 60 + 120;
        relocate(startX, startY);
    }

    /**
     * oblicza nowy wiersz i kolumne pionka z pikseli
     * @param x wiersz
     * @param y kolumna
     */
    public void putInRightPlace(double x, double y) {
        int newCol = Math.floorDiv((int)(x - 320), 60);
        int newRow = Math.floorDiv((int)(y - 120 + 30), 60);
        //pawn
        if(type == 1 || type == -1) {
            if (pieceMove(row, col, newRow, newCol, color)) {
                board[newRow][newCol] = board[row][col];
                board[row][col] = null;

                try {
                    connect.sendMessage("move," + row + "," + col + "," + newRow + "," + newCol + "," + color + "," + type + ","+ !isMyTurn);
                    NickService.changeColor(whitePlayer, redPlayer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                row = newRow;
                col = newCol;
                if (row == 0 || row == 7)
                    promote(type);
                move(row, col);
            } else {
                move(row, col);
            }
        }
        else
        {
            //King
            if (kingMove(row, col, newRow, newCol, color)) {
                board[newRow][newCol] = board[row][col];
                board[row][col] = null;

                try {
                    connect.sendMessage("move," + row + "," + col + "," + newRow + "," + newCol + "," + color + "," + type + ","+ !isMyTurn);
                    NickService.changeColor(whitePlayer, redPlayer);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                row = newRow;
                col = newCol;
                move(row, col);
            }
            else {
                move(row, col);
            }
        }
    }

    /**
     * promuje pionka w damke
     * @param type  typ pionka
     */
    public void promote(int type) {

        if(type == 1 && row == 0) {
            this.type = 2;
            view.setImage(kingImage);
        }
        else if(type == -1 && row == 7){
            this.type = -2;
            view.setImage(kingImage);
        }
    }

    /**
     * uwuwa pionka z gry
     * @param row   pozycja pionka(wiersz)
     * @param col   pozycja pionka(kolumna)
     * @param color kolor pionka
     */
    public static void beat(int row, int col, String color)
    {
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    connect.sendMessage("beating,"+row+","+col+","+color);//player color
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pawnPane.getChildren().remove(board[row][col]);
                board[row][col] = null;
            }
        });
    }

    /**
     * usuwa pionka bitego przez przeciwnika
     * @param row   pozycja pionka(wiersz)
     * @param col   pozycja pionka(kolumna)
     * @param color kolor pionka
     */
    public static void beatFromServer(int row, int col, String color)
    {
        Platform.runLater(new Runnable() {
            public void run() {
                pawnPane.getChildren().remove(board[row][col]);
                board[row][col] = null;
            }
        });
    }
}
