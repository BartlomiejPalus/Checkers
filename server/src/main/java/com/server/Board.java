package com.server;

/**
 * plansza odwzorowana za pomoca tablicy
 */
public class Board {
    private int[][] board = new int[8][8];

    /**
     * wywoluje inicjacje tablicy z pionkami
     */
    Board()
    {
        initBoard();
    }

    /**
     * przesuwa pionek w tablicy
     * @param oldRow    stara pozycja(wiersz)
     * @param oldCol    stara pozycja(kolumna)
     * @param newRow    nowa pozycja(wiersz)
     * @param newCol    nowa pozycja(kolumna)
     */
    public void movePiece(int oldRow, int oldCol, int newRow, int newCol)
    {
        board[newRow][newCol] = board[oldRow][oldCol];
        board[oldRow][oldCol] = 0;
    }

    /**
     * promuje pionka na damke
     * @param row   pozycja(wiersz)
     * @param col   pozycja(kolumna)
     * @param type  typ pionka
     */
    public void promote(int row, int col, int type)
    {
        board[row][col] = type;
    }

    /**
     * uwuwa pionka z tablicy
     * @param row   pozycja(wiersz)
     * @param col   pozycja(kolumna)
     */
    public void beat(int row, int col)
    {
        board[row][col] = 0;
    }

    /**
     * inicjuje tablice wartosciami poczatkowymi
     */
    private void initBoard()
    {
        //puste 0
        //biale 1
        //biala damka 2
        //czerwone -1
        //czerwona damka -2

        for(int i=0; i<8; i++)
        {
            for(int j=0;j<8;j++)
            {
                board[i][j]=0;
            }
        }
        for(int row=0; row<8; row++)
        {
            for(int col=0; col<8; col++)
            {
                if(((row%2==1 && col%2==0) || (row%2==0 && col%2==1))  && (row<3 || row>4))
                {
                    if (row<3)
                    {
                        board[row][col] = -1;
                    }
                    else if(row>4)
                    {
                        board[row][col] = 1;
                    }
                }
            }
        }
    }

    /**
     * sprwadza czy ktorys gracz wygral poprzez zbicie wszystkich pionkow przeciwnika
     * @return  zwraca 1 jesli wygral bialy, -1 jesli wygral czerwony lub 0 jesli nikt nie wygral
     */
    public int endGame()
    {
        boolean white=false, red=false;
        for (int i=0; i<8;i++)
        {
            for (int j=0; j<8;j++)
            {
                if(board[i][j] < 0)
                    red = true;
                else if(board[i][j] > 0)
                    white = true;

                if(white && red)
                    return 0;
            }
        }
        if (white && red)
            return 0;
        if(white)
            return 1;
        else if(red)
            return -1;
        return 0;
    }

    /**
     * wypisuje zawartosc tablicy w konsoli
     */
    public void wypiszTablice() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }
}
