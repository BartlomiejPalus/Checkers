package com.server;

import java.net.ServerSocket;

/**
 * poczatek wykonywania programu
 */
public class Main {
    /**
     * tworzy gniazdo serwera na porcie 2000, akceptuje polaczenia od klientow,
     * tworzy watki ktore odbieraja wiadomosci od klientow
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket;
        serverSocket = new ServerSocket(2000);
        Thread searchGame = new Thread(new SearchGame());
        searchGame.start();

        while (true)
        {
            Thread listener = new Thread(new Listener(serverSocket.accept()));
            listener.start();
        }
    }
}
