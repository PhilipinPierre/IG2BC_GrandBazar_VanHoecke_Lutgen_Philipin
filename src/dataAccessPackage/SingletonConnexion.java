package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class SingletonConnexion {
    private static Connection instance;

    public static Connection getInstance() throws ExceptionsBD{
        try{
            if(instance == null){
                System.out.println("Ex");
                Context context = new InitialContext();
                System.out.println("Contexte");
                DataSource source = (DataSource)context.lookup("jdbc:mysql://localhost:3306/dbmagasin");
                System.out.println("Source");
                instance = source.getConnection();
                System.out.println("Instance");
            }
        } catch (Exception e){
            throw new ExceptionsBD(" connexion a la base de données");
        }
        return instance;
    }
}