package businessPackage;

import dataAccessPackage.ConnectionBDA;
import dataAccessPackage.ConnectionDA;
import exceptionsPackage.ExceptionsBD;
import java.sql.SQLException;

public class ConnectionManager {
    private ConnectionDA cda;

    public ConnectionManager()
    {
        cda = new ConnectionBDA();
    }

    public void fermetureConnection() throws ExceptionsBD, SQLException
    {
        cda.fermetureConnection();
    }
}