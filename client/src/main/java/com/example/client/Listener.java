package com.example.client;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static com.example.client.GameController.*;
import static com.example.client.LoginController.login;
import static com.example.client.Main.connect;
import static com.example.client.Piece.beatFromServer;

/**
 * obsluga wiadomosci z serwera
 */
public class Listener implements Runnable{

    private String message;
    private Text matchResult;
    private Text redPlayer;
    private Text whitePlayer;
    private Button backToMenu;
    private Button draw;
    private Button yes;
    private Button no;
    private Text drawOffer;

    /**
     * przypisuje parametry do zmiennych
     * @param matchResult   tekst z wynikiem meczu
     * @param redPlayer     tekst z nazwa gracza czerwonego
     * @param whitePlayer   tekst z nazwa gracza bialego
     * @param backToMenu    przycisk powrotu do menu
     * @param draw          przycisk propozycji remisu
     * @param yes           przycisk zaakceptowania remisu
     * @param no            przycisk odrzucenia remisu
     * @param drawOffer     tekst z zapytaniem o remis
     */
    Listener(Text matchResult, Text redPlayer, Text whitePlayer, Button backToMenu, Button draw, Button yes, Button no, Text drawOffer)
    {
        this.matchResult=matchResult;
        this.redPlayer=redPlayer;
        this.whitePlayer=whitePlayer;
        this.backToMenu=backToMenu;
        this.draw=draw;
        this.yes=yes;
        this.no=no;
        this.drawOffer=drawOffer;
    }

    /**
     * osbiera wiadomosci z serwera i wywoluje odpowiednie metody
     */
    @Override
    public void run() {
        String enemyNick = null;
        boolean exit = false;
        try {
            connect.sendMessage("ready,"+login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(!exit)
        {
            try {
                message = connect.readMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(message == null)
                continue;

            String msg[] = message.split(",");

            for (int i = 1; i < msg.length; i++) {
                msg[i].replaceFirst(",", "");
            }
            switch (msg[0]) {
                case "start" -> {
                    playerColor = msg[1];
                    enemyNick=msg[2];
                    if(playerColor.equals("white")) {
                        isMyTurn = true;
                        whitePlayer.setText(login);
                        redPlayer.setText(msg[2]);
                    }
                    else {
                        whitePlayer.setText(msg[2]);
                        redPlayer.setText(login);
                    }
                    whitePlayer.setFill(Color.GREEN);
                }
                case "move" -> {
                    board[Integer.parseInt(msg[1])][Integer.parseInt(msg[2])].move(Integer.parseInt(msg[3]), Integer.parseInt(msg[4]));
                    board[Integer.parseInt(msg[3])][Integer.parseInt(msg[4])] = board[Integer.parseInt(msg[1])][Integer.parseInt(msg[2])];
                    board[Integer.parseInt(msg[1])][Integer.parseInt(msg[2])] = null;
                    board[Integer.parseInt(msg[3])][Integer.parseInt(msg[4])].promote(Integer.parseInt(msg[6]));
                    isMyTurn = Boolean.parseBoolean(msg[7]);
                    NickService.changeColor(whitePlayer, redPlayer);
                }
                case "beat" -> {
                    beatFromServer(Integer.parseInt(msg[1]), Integer.parseInt(msg[2]), msg[3]);
                }
                case "promote" -> {
                    board[Integer.parseInt(msg[1])][Integer.parseInt(msg[2])].promote(Integer.parseInt(msg[3]));
                }
                case "drawOffer" -> {
                    draw.setVisible(false);
                    yes.setVisible(true);
                    no.setVisible(true);
                    drawOffer.setText("Czy zgadzasz się na remis?");
                }
                case "drawResponse" -> {
                    if(msg[1].equals("draw")){
                        matchResult.setText("Remis");
                        isMyTurn = false;
                        isGameEnded = true;
                        draw.setVisible(true);
                        backToMenu.setVisible(true);
                    }
                    else {
                        draw.setVisible(true);
                        yes.setVisible(false);
                        no.setVisible(false);
                    }
                }
                case "result" -> {
                    if (msg[1].equals(playerColor))
                        matchResult.setText("Wygrał gracz "+login);
                    else
                        matchResult.setText("Wygrał gracz "+enemyNick);
                    isGameEnded = true;
                    isMyTurn = false;
                    backToMenu.setVisible(true);
                }
                default -> {
                    System.out.println("Błędna wartość operacji");
                }
            }
        }
    }
}