package com.example.client;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.example.client.GameController.isMyTurn;
import static com.example.client.GameController.playerColor;

/**
 * obsluga pol nickow podczas rozgrywki
 */
public class NickService {
    /**
     * zmiana koloru nicku gracza ktory aktualnie wykonuje ruch
     * @param whitePlayer   tekst z nickiem gracza bialego
     * @param redPlayer     tekst z nickiem gracza czerwonego
     */
    public static void changeColor(Text whitePlayer, Text redPlayer)
    {
        if(isMyTurn) {
            if (playerColor.equals("red")) {
                redPlayer.setFill(Color.GREEN);
                whitePlayer.setFill(Color.WHITE);
            } else {
                whitePlayer.setFill(Color.GREEN);
                redPlayer.setFill(Color.WHITE);
            }
        } else {
            if (playerColor.equals("red")) {
                redPlayer.setFill(Color.WHITE);
                whitePlayer.setFill(Color.GREEN);
            } else {
                whitePlayer.setFill(Color.WHITE);
                redPlayer.setFill(Color.GREEN);
            }
        }
    }
}
