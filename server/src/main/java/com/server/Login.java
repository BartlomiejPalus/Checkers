package com.server;

import java.sql.ResultSet;

/**
 * logowanie gracza
 */
public class Login{
    /**
     * sprawdza poprawnosc danych podanych przez uzytkownika do logowania
     * @param login     login podany przez uzytkownika
     * @param password  haslo podane przez uzytkownika
     * @return  true jezeli dane sa poprawne lub false w przciwnym wypadku
     */
    public static boolean checkData(String login, String password)
    {
        ResultSet result = QueryExecutor.executeSelect("SELECT login, haslo FROM dane_logowania WHERE login = '" + login + "' AND haslo = '" + password + "';");

        try
        {
            if(result.isBeforeFirst())
            {
                result.next();

                if (result.getString("login").equals(login) && result.getString("haslo").equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
