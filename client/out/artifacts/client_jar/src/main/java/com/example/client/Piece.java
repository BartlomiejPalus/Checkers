package com.example.client;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.example.client.GameController.pieceTab;
import static com.example.client.Main.connect;

public class Piece extends Pane {

    private ImageView view;
    private Image image;

    private double mouseX, mouseY;
    private double startX, startY;
    int row, col;
    int type;
    String color;

    public Piece(String color, int row, int col) throws FileNotFoundException {

        this.row = row;
        this.col = col;
        this.color = color;
        move(row, col);

        if(color.equals("red")) {
            image = new Image(new FileInputStream("src\\main\\resources\\com\\example\\client\\images\\redPawn.png"));
            type = -1;
        }
        else {
            image = new Image(new FileInputStream("src\\main\\resources\\com\\example\\client\\images\\whitePawn.png"));
            type = 1;
        }

        view = new ImageView(image);
        view.setImage(image);

        getChildren().addAll(view);

        setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + startX, e.getSceneY() - mouseY + startY);
        });

        setOnMouseReleased(e -> {
            int startRow = row;
            int startCol = col;
            putInRightPlace(e.getSceneX() - mouseX + startX, e.getSceneY() - mouseY + startY);
        });
    }

    public void move(int row, int col)
    {
        this.row = row;
        this.col = col;
        startX = col * 60 + 360;
        startY = row * 60 + 120;
        relocate(startX, startY);
    }

    public void putInRightPlace(double x, double y)
    {
        int newCol = Math.floorDiv((int)(x - 320), 60);
        int newRow = Math.floorDiv((int)(y - 120 + 30), 60);

        if(CheckMove.pieceMove(row, col, newRow, newCol, type, color)) {
            pieceTab[newRow][newCol] = pieceTab[row][col];
            pieceTab[row][col] = null;
            try {
                connect.sendMessage("move,"+row+","+col+","+newRow+","+newCol+","+type);
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

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
