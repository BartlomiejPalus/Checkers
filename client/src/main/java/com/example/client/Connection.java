package com.example.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * komunikacja z serwerem
 */
public class Connection {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * utworzenie polaczenia z serwerem
     * @param port numer portu
     * @return socket
     * @throws Exception brak polaczenia z serwerem
     */
    public Socket startConnection(int port) throws Exception
    {
        try
        {
            socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            return socket;
        }
        catch (Exception e)
        {
            System.out.println("Nie połączono z serwerem");
        }
        return socket;
    }

    /**
     * wysylanie wiadomosci do serwera
     * @param message tresc wiadomosci
     * @throws Exception
     */
    public void sendMessage(String message) throws Exception
    {
        out.println(message);
    }

    /**
     * odebranie wiadomosci z serwera
     * @return wiadmosc
     * @throws Exception
     */
    public String readMessage() throws Exception
    {
        return in.readLine();
    }

    /**
     * zakonczenia polaczenia z serwerem
     * @throws Exception
     */
    public void stopConnection() throws Exception
    {
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
