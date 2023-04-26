package com.example.client;

import static com.example.client.ForcedBeating.isBeatingPossibe;
import static com.example.client.ForcedBeating.isBeatingPossibeAfterMove;
import static com.example.client.GameController.*;
import static com.example.client.Piece.beat;

/**
 * sprawdzanie ruchow damek
 */
public class CheckKingMove {
    /**
     * sprawdzanie poprawnosci ruchu damki
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     * @return          zwraca true jezeli ruch jest mozliwy lub false w przeciwnym wypdaku
     */
    public static boolean kingMove(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        if(newRow>7 || newRow<0 || newCol>7 || newCol<0)
            return false;
        if(board[newRow][newCol] != null)
            return false;
        if((newRow%2==0 && newCol%2==0 || newRow%2==1 && newCol%2==1))
            return false;
        if(Math.abs(oldRow-newRow) != Math.abs(oldCol-newCol))
            return false;

        if(oldRow>newRow && oldCol>newCol)
            return leftUpMove(oldRow, oldCol, newRow, newCol, color);
        else if(oldRow>newRow && oldCol<newCol)
            return rightUpMove(oldRow, oldCol, newRow, newCol, color);
        else if(oldRow<newRow && oldCol<newCol)
            return rightDownMove(oldRow, oldCol, newRow, newCol, color);
        else if(oldRow<newRow && oldCol>newCol)
            return leftDownMove(oldRow, oldCol, newRow, newCol, color);
        return false;
    }

    /**
     * metoda sprawdza mozliwosc ruchu w lewym gornym kierunku
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     * @return          zwraca true jezeli ruch jest mozliwy lub false w przeciwnym wypdaku
     */
    private static boolean leftUpMove(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        int tmpRow = oldRow, tmpCol = oldCol;
        int enemyCounter=0;
        boolean isBeat=false;
        while(oldRow>newRow && oldCol>newCol)
        {
            oldRow--;
            oldCol--;
            if(board[oldRow][oldCol] != null) {
                enemyCounter++;
                if (board[oldRow][oldCol].color == color)
                    return false;
            }
            else {
                if(enemyCounter!=0)
                    isBeat=true;
                enemyCounter = 0;
            }
            if(enemyCounter>1)
                return false;
        }
        if (isBeat == isBeatingPossibe() )
        {
            leftUpBeat(tmpRow, tmpCol, newRow, newCol, color);
            if(isBeatingPossibeAfterMove(newRow, newCol, board[tmpRow][tmpCol].type))
                isMyTurn=true;
            else
                isMyTurn=false;
            return true;
        }
        return false;
    }

    /**
     * metoda sprawdza mozliwosc ruchu w prawym gornym kierunku
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     * @return          zwraca true jezeli ruch jest mozliwy lub false w przeciwnym wypdaku
     */
    private static boolean rightUpMove(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        int tmpRow = oldRow, tmpCol = oldCol;
        int enemyCounter=0;
        boolean isBeat=false;
        while(oldRow>newRow && oldCol<newCol)
        {
            oldRow--;
            oldCol++;
            if(board[oldRow][oldCol] != null) {
                enemyCounter++;
                if (board[oldRow][oldCol].color == color)
                    return false;
            }
            else {
                if(enemyCounter!=0)
                    isBeat=true;
                enemyCounter = 0;
            }

            if(enemyCounter>1)
                return false;
        }
        if (isBeat == isBeatingPossibe() )
        {
            rightUpBeat(tmpRow, tmpCol, newRow, newCol, color);
            if(isBeatingPossibeAfterMove(newRow, newCol, board[tmpRow][tmpCol].type))
                isMyTurn=true;
            else
                isMyTurn=false;
            return true;
        }
        return false;
    }

    /**
     * metoda sprawdza mozliwosc ruchu w prawym dolnym kierunku
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     * @return          zwraca true jezeli ruch jest mozliwy lub false w przeciwnym wypdaku
     */
    private static boolean rightDownMove(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        int tmpRow = oldRow, tmpCol = oldCol;
        int enemyCounter=0;
        boolean isBeat=false;
        while(oldRow<newRow && oldCol<newCol)
        {
            oldRow++;
            oldCol++;
            if(board[oldRow][oldCol] != null) {
                enemyCounter++;
                if (board[oldRow][oldCol].color == color)
                    return false;
            }
            else {
                if(enemyCounter!=0)
                    isBeat=true;
                enemyCounter = 0;
            }

            if(enemyCounter>1)
                return false;
        }
        if (isBeat == isBeatingPossibe() )
        {
            rightDownBeat(tmpRow, tmpCol, newRow, newCol, color);
            if(isBeatingPossibeAfterMove(newRow, newCol, board[tmpRow][tmpCol].type))
                isMyTurn = true;
            else {
                isMyTurn = false;
            }
            return true;
        }
        else if (!isBeatingPossibe())
            isMyTurn=false;
        return false;
    }

    /**
     * metoda sprawdza mozliwosc ruchu w lewym dolnym kierunku
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     * @return          zwraca true jezeli ruch jest mozliwy lub false w przeciwnym wypdaku
     */
    private static boolean leftDownMove(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        int tmpRow = oldRow, tmpCol = oldCol;
        int enemyCounter=0;
        boolean isBeat=false;
        while(oldRow<newRow && oldCol>newCol)
        {
            oldRow++;
            oldCol--;
            if(board[oldRow][oldCol] != null) {
                enemyCounter++;
                if (board[oldRow][oldCol].color == color)
                    return false;
            }
            else {
                if(enemyCounter!=0)
                    isBeat=true;
                enemyCounter = 0;
            }

            if(enemyCounter>1)
                return false;
        }
        if (isBeat == isBeatingPossibe())
        {
            leftDownBeat(tmpRow, tmpCol, newRow, newCol, color);
            if(isBeatingPossibeAfterMove(newRow, newCol, board[tmpRow][tmpCol].type))
                isMyTurn=true;
            else
                isMyTurn=false;
            return true;
        }
        else if (!isBeatingPossibe())
            isMyTurn=false;
        return false;
    }

    /**
     * bicie pionkow pomiedzy poczatkowa pozycja damki a koncowa w kierunku lewym gornym
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     */
    private static void leftUpBeat(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        while(oldRow>newRow && oldCol>newCol)
        {
            oldRow--;
            oldCol--;
            if(board[oldRow][oldCol] != null) {
                beat(oldRow, oldCol, color);
            }
        }
    }

    /**
     * bicie pionkow pomiedzy poczatkowa pozycja damki a koncowa w kierunku prawym gornym
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     */
    private static void rightUpBeat(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        while(oldRow>newRow && oldCol<newCol)
        {
            oldRow--;
            oldCol++;
            if(board[oldRow][oldCol] != null) {
                beat(oldRow, oldCol, color);
            }
        }
    }

    /**
     * bicie pionkow pomiedzy poczatkowa pozycja damki a koncowa w kierunku prawym dolnym
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     */
    private static void rightDownBeat(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        while(oldRow<newRow && oldCol<newCol)
        {
            oldRow++;
            oldCol++;
            if(board[oldRow][oldCol] != null) {
                beat(oldRow, oldCol, color);
            }
        }
    }

    /**
     * bicie pionkow pomiedzy poczatkowa pozycja damki a koncowa w kierunku lewym dolnym
     * @param oldRow    poczatkowa pozycja damki(wiersz)
     * @param oldCol    poczatkowa pozycja damki(kolumna)
     * @param newRow    koncowa pozycja damki(wiersz)
     * @param newCol    koncowa pozycja damki(kolumna)
     * @param color     kolor damki
     */
    private static void leftDownBeat(int oldRow, int oldCol, int newRow, int newCol, String color)
    {
        while(oldRow<newRow && oldCol>newCol)
        {
            oldRow++;
            oldCol--;
            if(board[oldRow][oldCol] != null) {
                beat(oldRow, oldCol, color);
            }
        }
    }
}
