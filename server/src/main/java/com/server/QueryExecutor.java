package com.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * wykonywanie zapytan do bazy danych
 */
public class QueryExecutor {
    /**
     * wykonuje zapytanie select do bazy danych
     * @param selectQuery   zapytanie
     * @return              wynik zapytania
     */
    public static ResultSet executeSelect(String selectQuery)
    {
        try {
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * wykonuje zapytania do bazy danych poza select
     * @param query zapytanie
     */
    public static void executeQuert(String query)
    {
        try {
            Connection connection = DbConnector.connect();
            Statement statement = connection.createStatement();
            statement.execute(query);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
}
