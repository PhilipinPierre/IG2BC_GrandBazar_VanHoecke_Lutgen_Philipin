package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;

import java.sql.*;

public class SingletonConnexion {
    private static Connection instance;

    private SingletonConnexion(){}

    public static Connection getInstance() throws ExceptionsBD{
        try {
            if (instance == null) {
                Class.forName("com.mysql.jdbc.Driver");
                instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmagasin?useSSL=false", "root", "root");
            }

        } catch (Exception e){
            throw new ExceptionsBD("établissement connection");
        }
        return instance;
    }
}