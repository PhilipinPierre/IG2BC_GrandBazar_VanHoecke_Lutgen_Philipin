package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;

import java.sql.Connection;

public class ConnectionBDA implements ConnectionDA{
    @Override
    public void fermetureConnection() throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            connection.close();
        }
        catch (Exception e){
            throw new ExceptionsBD("la fermeture de la connexion à la base de donnée");
        }
    }
}
