package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * odbieranie i obsluguje wiadomosci od gracza podczas gry
 */
public class GameListener implements Runnable{

    Socket clientSocket;
    BufferedReader in;
    PrintWriter out, enemyOut;

    int result;
    String color;
    String message;
    Board board;

    /**
     * zapisuje wartosci parametrow do zmiennych i tworzy stumien ejscia i wyjscia do gracza
     * @param clientSocket  identyfikator gniazda gracza
     * @param color         kolor gracza
     * @param board         tablica z ukladem pionkow
     * @param enemyOut      strumien wyjscia do przeciwnika
     * @throws IOException
     */
    GameListener(Socket clientSocket, String color, Board board, PrintWriter enemyOut) throws IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.enemyOut = enemyOut;
        this.color = color;
        this.board = board;
    }

    /**
     * obsluguje widomosci odbierane przez serwer podczas gry
     */
    @Override
    public void run() {
        boolean exit = false;
        while (!exit)
        {
            try {
                message = in.readLine();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(message == null)
                continue;
            System.out.println("GameListener odebrano wiadomosc: "+message);
            String msg[] = message.split(",");

            for (int i = 1; i < msg.length; i++) {
                msg[i].replaceFirst(",", "");
            }

            switch (msg[0])
            {
                case "ready" -> {
                    enemyOut.println("start," + color +","+ msg[1]);
                }
                case "move" -> {
                    if(msg[5] != color) {
                        board.movePiece(Integer.parseInt(msg[1]), Integer.parseInt(msg[2]), Integer.parseInt(msg[3]), Integer.parseInt(msg[4]));
                        enemyOut.println("move," + msg[1] + "," + msg[2] + "," + msg[3] + "," + msg[4] + "," + color + "," +msg[6] + "," + msg[7]);
                    }
                }
                case "beating" -> {
                    if(msg[3] != color) {
                        enemyOut.println("beat," + msg[1] + "," + msg[2] + "," + msg[3]);
                    }
                    board.beat(Integer.parseInt(msg[1]), Integer.parseInt(msg[2]));
                    board.wypiszTablice();
                    result = board.endGame();
                    if(result > 0) {
                        out.println("result,white");
                        enemyOut.println("result,white");
                    }
                    else if(result < 0) {
                        out.println("result,red");
                        enemyOut.println("result,red");
                    }
                }
                case "promote" -> {
                    board.promote(Integer.parseInt(msg[1]), Integer.parseInt(msg[2]), Integer.parseInt(msg[3]));
                    enemyOut.println("promote," + msg[1] + "," + msg[2] + "," + msg[3]);
                }
                case "surrender" -> {
                    if(msg[1].equals("red")) {
                        out.println("result,white");
                        enemyOut.println("result,white");
                    }
                    else {
                        out.println("result,red");
                        enemyOut.println("result,red");
                    }
                }
                case "drawOffer" -> {
                    enemyOut.println("drawOffer");
                }
                case "drawResponse" -> {
                    if(msg[1].equals("yes")) {
                        out.println("drawResponse,draw");
                        enemyOut.println("drawResponse,draw");
                    } else {
                        enemyOut.println("drawResponse,no");
                    }
                }
                case "disconnect" -> {
                    exit = true;
                }
                default -> {
                    System.out.println("Błedna wartosc operacji");
                }
            }
        }
    }
}
