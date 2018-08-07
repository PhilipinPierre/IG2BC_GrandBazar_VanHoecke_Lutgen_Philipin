package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionBDA implements ConnectionDA{
    @Override
    public void fermetureConnection() throws ExceptionsBD, SQLException
    {
        Connection connection = SingletonConnexion.getInstance();
        connection.close();
    }
}
