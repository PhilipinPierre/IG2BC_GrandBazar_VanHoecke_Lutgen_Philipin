package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;

import java.sql.*;
import exceptionsPackage.ExceptionsBD;
import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnexion {
    private static Connection instance;

    private SingletonConnexion(){}

    public static Connection getInstance() throws ExceptionsBD
    {
        try
        {
            if(instance == null)
            {
                Class.forName("com.mysql.jdbc.Driver");
                instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmagasin?useSSL=false", "root", "root");
            }
            return instance;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Probleme lors de la connexion à la base de données !");
        }

    }
}