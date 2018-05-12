package businessPackage;

import dataAccessPackage.ConnectionBDA;
import dataAccessPackage.ConnectionDA;
import exceptionsPackage.ExceptionsBD;

public class ConnectionManager {
    private ConnectionDA cda;

    public ConnectionManager()
    {
        cda = new ConnectionBDA();
    }

    public void fermetureConnection() throws ExceptionsBD
    {
        cda.fermetureConnection();
    }

}