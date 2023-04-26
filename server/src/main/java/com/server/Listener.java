package com.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * odbieranie i obsluguje wiadomosci od gracza poza trwaniem partii
 */
public class Listener implements Runnable {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String message;
    private String nick;

    /**
     * tworzy strumien wyjscia i wejscia z graczem
     * @param clientSocket identyfikator gniazda gracza
     */
    public Listener(Socket clientSocket)
    {
        try
        {
            this.clientSocket = clientSocket;
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        }
        catch (Exception e)
        {
            System.out.println("Blad w listener konstruktor");
        }
    }

    /**
     * obsluguje widomosci odbierane przez serwer poza trwaniem rozgrywki
     */
    @Override
    public void run() {
        boolean exit = false;
        while(!exit)
        {
            try {
                message = in.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(message == null)
                continue;

            System.out.println("listener "+message);
            String msg[] = message.split(",");

            for (int i = 1; i < msg.length; i++) {
                msg[i].replaceFirst(",", "");
            }

            switch (msg[0]) {
                case "login" -> {
                    if(Login.checkData(msg[1], msg[2])) {
                        nick = msg[1];
                        out.println(true);
                    }
                    out.println(false);
                }
                case "register" -> {
                    out.println(Register.register(msg[1], msg[2]));
                }
                case "disconnect" -> {
                    exit = true;
                }
                case "reconnect" -> {
                    nick = msg[1];
                }
                default -> {
                    System.out.println("Błędna wartość operacji");
                }
            }
        }
    }
}
