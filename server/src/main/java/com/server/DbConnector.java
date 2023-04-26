package com.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * laczenie z baza danych
 */
public class DbConnector {

    private static String URL = "jdbc:mysql://127.0.0.1:3306/warcaby";
    private static String USER = "root";
    private static String PASSWORD = "";

    /**
     * ustanawia polaczenie z baza danych
     * @return identyfikator polaczenia z baza danych
     */
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
