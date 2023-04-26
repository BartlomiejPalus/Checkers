package com.example.client;

import static com.example.client.GameController.pieceTab;
import static com.example.client.GameController.playerColor;
import static com.example.client.Main.connect;

public class Listener implements Runnable{

    private String message;

    @Override
    public void run() {
        boolean exit = false;

        while(!exit)
        {
            try {
                message = connect.readMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(message == null)
                continue;

            System.out.println(message);

            String msg[] = message.split(",");

            for (int i = 1; i < msg.length; i++) {
                msg[i].replaceFirst(",", "");
            }

            switch (msg[0]) {
                case "start" -> {
                    //to do nick przeciwnika i ranking
                    playerColor = msg[1];
                    System.out.println(playerColor);
                }
                case "move" -> {
                        pieceTab[Integer.parseInt(msg[1])][Integer.parseInt(msg[2])].move(Integer.parseInt(msg[3]), Integer.parseInt(msg[4]));
                        pieceTab[Integer.parseInt(msg[3])][Integer.parseInt(msg[4])] = pieceTab[Integer.parseInt(msg[1])][Integer.parseInt(msg[2])];
                        pieceTab[Integer.parseInt(msg[1])][Integer.parseInt(msg[2])] = null;
                }
                default -> {
                    System.out.println("Błędna wartość operacji");
                }
            }
        }
    }
}