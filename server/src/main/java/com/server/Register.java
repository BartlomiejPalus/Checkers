package com.server;

import java.sql.ResultSet;

/**
 * rejestracja nowego uzytkownika
 */
public class Register{
    /**
     * tworzy nowe konto o danych podanych przez uzytkownika
     * @param login     login podany przez uzytkoniwka do rejestracji
     * @param password  haslo podane przez uzytkownika do rejestracji
     * @return  zwraca true jeszeli udalo sie utworzyc konto lub false w przeciwnym wypadku
     */
    public static boolean register(String login, String password) {

        ResultSet result = QueryExecutor.executeSelect("SELECT login FROM dane_logowania WHERE login = '" + login + "';");
        try
        {
            if(result.isBeforeFirst())
            {
                return false;
            }
            else
            {
                result = QueryExecutor.executeSelect("SELECT max(id) FROM dane_logowania;");

                if(result.isBeforeFirst())
                {
                    result.next();

                    int id = result.getInt("max(id)")+1;
                    QueryExecutor.executeQuert("INSERT INTO dane_logowania values ("+id+", '"+login+"', '"+password+"');");
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
