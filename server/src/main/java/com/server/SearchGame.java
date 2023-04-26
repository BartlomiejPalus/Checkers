package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * laczenie graczy ktorzy chca zagrac w pary
 */
public class SearchGame implements Runnable{

    private ServerSocket serverSocket;
    private Socket clientSocket1 = null;
    private Socket clientSocket2 = null;

    /**
     * laczy w pary graczy ktorzy chca rozegrac partie
     */
    @Override
    public void run() {
        try
        {
            serverSocket = new ServerSocket(2001);

            while(true)
            {
                if(clientSocket1 == null)
                {
                    clientSocket1 = serverSocket.accept();
                }
                else if(clientSocket2 == null)
                {
                    clientSocket2 = serverSocket.accept();
                }
                if(clientSocket1 != null && clientSocket2 != null)
                {
                    Game game = new Game(clientSocket1, clientSocket2);
                    clientSocket1 = null;
                    clientSocket2 = null;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try {
                clientSocket1.close();
                clientSocket2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}