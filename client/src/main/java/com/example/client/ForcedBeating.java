package com.example.client;

import static com.example.client.GameController.board;
import static com.example.client.GameController.playerColor;

/**
 * wymuszanie bic
 */
public class ForcedBeating {
    /**
     * sprawdza mozliwosc bicia
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    public static boolean isBeatingPossibe()
    {
        for(int row=0; row<8; row++)
        {
            for(int col=0; col<8; col++)
            {
                if(((row%2==1 && col%2==0) || (row%2==0 && col%2==1)))
                {
                    if(board[row][col] == null)
                        continue;
                    if (!board[row][col].color.equals(playerColor))
                        continue;

                    if(row > 1 && col > 1 ) {
                        if (board[row][col].type == -1 || board[row][col].type == 1) {
                            if (leftUpPiece(row, col))
                                return true;
                        } else {
                            if (leftUpKing(row, col))
                                return true;
                        }
                    }
                    if(row > 1 && col < 6 ) {
                        if (board[row][col].type == -1 || board[row][col].type == 1) {
                            if (rightUpPiece(row, col))
                                return true;
                        } else {
                            if (rightUpKing(row, col))
                                return true;
                        }
                    }
                    if(row < 6 && col < 6 ) {
                        if (board[row][col].type == -1 || board[row][col].type == 1) {
                            if (rightDownPiece(row, col))
                                return true;
                        } else {
                            if (rightDownKing(row, col))
                                return true;
                        }
                    }
                    if(row < 6 && col > 1 ) {
                        if (board[row][col].type == -1 || board[row][col].type == 1) {
                            if (leftDownPiece(row, col))
                                return true;
                        } else {
                            if (leftDownKing(row, col))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * sprawdza czy po ruchu mozliwe jest bicie
     * @param row   pozycja pionka(wiersz)
     * @param col   pozycja pionka(kolumna)
     * @param type  typ pionka
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    public static boolean isBeatingPossibeAfterMove(int row, int col, int type)
    {
        if(row > 1 && col > 1 ) {
            if (type == -1 || type == 1) {
                if (leftUpPiece(row, col))
                    return true;
            } else {
                if (leftUpKing(row, col))
                    return true;
            }
        }
        if(row > 1 && col < 6 ) {
            if (type == -1 || type == 1) {
                if (rightUpPiece(row, col))
                    return true;
            } else {
                if (rightUpKing(row, col))
                    return true;
            }
        }
        if(row < 6 && col < 6 ) {
            if (type == -1 || type == 1) {
                if (rightDownPiece(row, col))
                    return true;
            } else {
                if (rightDownKing(row, col))
                    return true;
            }
        }
        if(row < 6 && col > 1 ) {
            if (type == -1 || type == 1) {
                if (leftDownPiece(row, col))
                    return true;
            } else {
                if (leftDownKing(row, col))
                    return true;
            }
        }
        return false;
    }

    /**
     * sprawdza mozliwosc bicia dla pionka w lewym gornym rogu
     * @param row   pozycja pionka(wiersz)
     * @param col   pozycja pionka(kolumna)
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    private static boolean leftUpPiece(int row, int col)
    {
        if(board[row-1][col-1] == null)
            return false;
        if(board[row-1][col-1].color.equals(playerColor))
            return false;
        else if(board[row-2][col-2] == null)
                return true;
        return false;
    }

    /**
     * sprawdza mozliwosc bicia dla pionka w prawym gornym rogu
     * @param row   pozycja pionka(wiersz)
     * @param col   pozycja pionka(kolumna)
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    private static boolean rightUpPiece(int row, int col)
    {
        if(board[row-1][col+1] == null)
            return false;
        if(board[row-1][col+1].color.equals(playerColor))
            return false;
        else if(board[row-2][col+2] == null)
            return true;
        return false;
    }

    /**
     * sprawdza mozliwosc bicia dla pionka w prawym dolnym rogu
     * @param row   pozycja pionka(wiersz)
     * @param col   pozycja pionka(kolumna)
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    private static boolean rightDownPiece(int row, int col)
    {
        if(board[row+1][col+1] == null)
            return false;
        if(board[row+1][col+1].color.equals(playerColor))
            return false;
        else if(board[row+2][col+2] == null)
            return true;
        return false;
    }

    /**
     * sprawdza mozliwosc bicia dla pionka w lewym dolnym rogu
     * @param row   pozycja pionka(wiersz)
     * @param col   pozycja pionka(kolumna)
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    private static boolean leftDownPiece(int row, int col)
    {
        if(board[row+1][col-1] == null)
            return false;
        if(board[row+1][col-1].color.equals(playerColor))
            return false;
        else if(board[row+2][col-2] == null)
            return true;
        return false;
    }

    /**
     * sprawdza mozliwosc bicia dla dami w lewym gornym rogu
     * @param row   pozycja damki(wiersz)
     * @param col   pozycja damki(kolumna)
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    private static boolean leftUpKing(int row, int col)
    {
        while(row > 1 && col > 1)
        {
            row--;
            col--;
            if(board[row][col] != null) {
                if (!board[row][col].color.equals(playerColor)) {
                    if (board[row - 1][col - 1] == null) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * sprawdza mozliwosc bicia dla dami w prawym gornym rogu
     * @param row   pozycja damki(wiersz)
     * @param col   pozycja damki(kolumna)
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    private static boolean rightUpKing(int row, int col)
    {
        while(row>1 && col<6)
        {
            row--;
            col++;
            if(board[row][col] != null) {
                if (!board[row][col].color.equals(playerColor)) {
                    if (board[row - 1][col + 1] == null) {
                        return true;
                    }
                    else {
                        return false;
                    }

                }
            }
        }
        return false;
    }

    /**
     * sprawdza mozliwosc bicia dla dami w prawym dolnym rogu
     * @param row   pozycja damki(wiersz)
     * @param col   pozycja damki(kolumna)
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    private static boolean rightDownKing(int row, int col)
    {
        while(row<6 && col<6)
        {
            row++;
            col++;
            if(board[row][col] != null) {
                if (!board[row][col].color.equals(playerColor)){
                    if (board[row+1][col+1] == null)
                     return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * sprawdza mozliwosc bicia dla dami w lewym dolnym rogu
     * @param row   pozycja damki(wiersz)
     * @param col   pozycja damki(kolumna)
     * @return true jezeli bicie jest mozliwe lub false w przeciwnym wypadku
     */
    private static boolean leftDownKing(int row, int col)
    {
        while(row<6 && col>1)
        {
            row++;
            col--;
            if(board[row][col] != null) {
                if (!board[row][col].color.equals(playerColor)) {
                    if (board[row + 1][col - 1] == null)
                        return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
}
