package com.example.client;

import static com.example.client.GameController.pawnPane;
import static com.example.client.GameController.pieceTab;

public class CheckMove {

    public static boolean pieceMove(int oldRow, int oldCol, int newRow, int newCol, int type, String color)
    {
        System.out.println("oldRow: "+oldRow );
        System.out.println("oldCol: "+oldCol );
        System.out.println("newRow: "+newRow );
        System.out.println("newCol: "+newCol );

        if(newRow>7 || newRow<0 || newCol>7 || newCol<0)
            return false;

        if(pieceTab[newRow][newCol] != null)
            return false;

        if((newRow%2==0 && newCol%2==0 || newRow%2==1 && newCol%2==1))
            return false;

        //lewy gorny
        if(newRow==oldRow-2 && newCol==oldCol-2 && pieceTab[newRow+1][newCol+1]!=null) {
            if (pieceTab[newRow + 1][newCol + 1].color != color) {
                pawnPane.getChildren().remove(pieceTab[newRow + 1][newCol + 1]);
                pieceTab[newRow + 1][newCol + 1] = null;
                return true;
            }
        }
        //prawy gorny
        if(newRow==oldRow-2 && newCol==oldCol+2 && pieceTab[newRow+1][newCol-1]!=null) {
            if (pieceTab[newRow + 1][newCol - 1].color != color) {
                pawnPane.getChildren().remove(pieceTab[newRow + 1][newCol - 1]);
                pieceTab[newRow + 1][newCol - 1] = null;
                return true;
            }
        }
        //prawy dolny
        if(newRow==oldRow+2 && newCol==oldCol+2 && pieceTab[newRow-1][newCol-1]!=null) {
            if (pieceTab[newRow - 1][newCol - 1].color != color) {
                pawnPane.getChildren().remove(pieceTab[newRow - 1][newCol - 1]);
                pieceTab[newRow - 1][newCol - 1] = null;
                return true;
            }
        }
        //lewy dolny
        if(newRow==oldRow+2 && newCol==oldCol-2 && pieceTab[newRow-1][newCol+1]!=null) {
            if (pieceTab[newRow - 1][newCol + 1].color != color) {
                pawnPane.getChildren().remove(pieceTab[newRow - 1][newCol + 1]);
                pieceTab[newRow - 1][newCol + 1] = null;
                return true;
            }
        }

        if(color.equals("red") && ((newRow==oldRow+1 && newCol==oldCol+1) || (newRow==oldRow+1 && newCol==oldCol-1))) {
            return true;
        }
        else if(color.equals("white") && ((newRow==oldRow-1 && newCol==oldCol+1) || (newRow==oldRow-1 && newCol==oldCol-1))) {
            return true;
        }

        return false;
    }






    private static void beating(int Row, int Col, int type)
    {
        /*if(pieceTab[Row-1][Col-1].type < 0)
        {
            if((Row-2>7 || Row-2<0 || Col-2>7 || Col-2<0) && pieceTab[Row-2][Col-2].type == 0) {
                counter++;
                longestBeating(Row - 2, Col - 2, type);
            }
            if((Row-2>7 || Row-2<0 || Col>7 || Col<0) && pieceTab[Row-2][Col-2].type == 0) {
                counter++;
                longestBeating(Row - 2, Col, type);
            }
            if((Row>7 || Row-2<0 || Col>7 || Col<0) && pieceTab[Row-2][Col-2].type == 0) {
                counter++;
                longestBeating(Row - 2, Col, type);
            }
            if((Row+2>7 || Row+2<0 || Col+2>7 || Col+2<0) && pieceTab[Row-2][Col-2].type == 0) {
                counter++;
                longestBeating(Row - 2, Col, type);
            }*/
    }
}
