package com.example.client;


import static com.example.client.ForcedBeating.isBeatingPossibe;
import static com.example.client.ForcedBeating.isBeatingPossibeAfterMove;
import static com.example.client.GameController.board;
import static com.example.client.GameController.isMyTurn;
import static com.example.client.Piece.beat;

/**
 * sprawdzanie ruchow pionkow
 */
public class CheckPawnMove {
    /**
     * sprawdzanie poprawnosci ruchu pionka
     * @param oldRow    poczatkowa pozycja pionka(wiersz)
     * @param oldCol    poczatkowa pozycja pionka(kolumna)
     * @param newRow    koncowa pozycja pionka(wiersz)
     * @param newCol    koncowa pozycja pionka(kolumna)
     * @param color     kolor pionka
     * @return          zwraca true jezeli ruch jest mozliwy lub false w przeciwnym wypdaku
     */
    public static boolean pieceMove(int oldRow, int oldCol, int newRow, int newCol, String color) {
        if(board[newRow][newCol] != null)
            return false;
        if(board[oldRow][oldCol].color != color)
            return false;
        if(newRow>7 || newRow<0 || newCol>7 || newCol<0)
            return false;

        if((newRow%2==0 && newCol%2==0 || newRow%2==1 && newCol%2==1))
            return false;

        //lewy gorny
        if(newRow==oldRow-2 && newCol==oldCol-2 && board[newRow+1][newCol+1]!=null) {
            if (board[newRow + 1][newCol + 1].color != color) {
                beat(newRow+1, newCol+1, color);
                if(isBeatingPossibeAfterMove(newRow, newCol, board[oldRow][oldCol].type))
                    isMyTurn=true;
                else
                    isMyTurn=false;
                return true;
            }
            isMyTurn=false;
        }
        //prawy gorny
        if(newRow==oldRow-2 && newCol==oldCol+2 && board[newRow+1][newCol-1]!=null) {
            if (board[newRow + 1][newCol - 1].color != color) {
                beat(newRow+1, newCol-1, color);
                if(isBeatingPossibeAfterMove(newRow, newCol, board[oldRow][oldCol].type))
                    isMyTurn=true;
                else
                    isMyTurn=false;
                return true;
            }
            isMyTurn=false;
        }
        //prawy dolny
        if(newRow==oldRow+2 && newCol==oldCol+2 && board[newRow-1][newCol-1]!=null) {
            if (board[newRow - 1][newCol - 1].color != color) {
                beat(newRow-1, newCol-1, color);
                if(isBeatingPossibeAfterMove(newRow, newCol, board[oldRow][oldCol].type))
                    isMyTurn=true;
                else
                    isMyTurn=false;
                return true;
            }
            isMyTurn=false;
        }
        //lewy dolny
        if(newRow==oldRow+2 && newCol==oldCol-2 && board[newRow-1][newCol+1]!=null) {
            if (board[newRow - 1][newCol + 1].color != color) {
                beat(newRow-1, newCol+1, color);
                if(isBeatingPossibeAfterMove(newRow, newCol, board[oldRow][oldCol].type))
                    isMyTurn=true;
                else
                    isMyTurn=false;
                return true;
            }
            isMyTurn=false;
        }

        if(color.equals("red") && ((newRow==oldRow+1 && newCol==oldCol+1) || (newRow==oldRow+1 && newCol==oldCol-1))) {
            if (isBeatingPossibe())
                return false;
            isMyTurn=false;
            return true;
        }
        else if(color.equals("white") && ((newRow==oldRow-1 && newCol==oldCol+1) || (newRow==oldRow-1 && newCol==oldCol-1))) {
            if (isBeatingPossibe())
                return false;
            isMyTurn=false;
            return true;
        }
        return false;
    }
}
