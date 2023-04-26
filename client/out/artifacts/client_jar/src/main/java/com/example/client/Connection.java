package com.example.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Socket startConnection(int port) throws Exception
    {
        try
        {
            socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Połączono connection "+port);
            return socket;
        }
        catch (Exception e)
        {
            System.out.println("Nie połączono z serwerem");
        }
        return socket;
    }

    public void sendMessage(String message) throws Exception
    {
        out.println(message);
    }

    public String readMessage() throws Exception
    {
        return in.readLine();
    }

    public void stopConnection() throws Exception
    {
        in.close();
        out.close();
        socket.close();
    }
}
