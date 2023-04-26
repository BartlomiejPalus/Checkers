package com.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * tworzy watki do komunikacji z graczami
 */
public class Game {
    Thread whiteT;
    Thread redT;

    PrintWriter whiteOut;
    PrintWriter redOut;

    /**
     * tworzy watki do komunikacji z graczami i losuje kolory
     * @param socket1   identyfikator gniazada gracza1
     * @param socket2   identyfikator gniazada gracza2
     * @throws Exception
     */
    public Game(Socket socket1, Socket socket2) throws Exception
    {
        Board board = new Board();

        Random rand = new Random();
        boolean color = rand.nextBoolean();

        if (color)
        {
            whiteOut = new PrintWriter(socket1.getOutputStream(), true);
            redOut = new PrintWriter(socket2.getOutputStream(), true);
            whiteT = new Thread(new GameListener(socket1, "white", board, redOut));
            redT = new Thread(new GameListener(socket2, "red", board, whiteOut));
        }
        else
        {
            whiteOut = new PrintWriter(socket2.getOutputStream(), true);
            redOut = new PrintWriter(socket1.getOutputStream(), true);
            whiteT = new Thread(new GameListener(socket2, "white", board, redOut));
            redT = new Thread(new GameListener(socket1, "red", board, whiteOut));
        }
        whiteT.start();
        redT.start();
    }
}
