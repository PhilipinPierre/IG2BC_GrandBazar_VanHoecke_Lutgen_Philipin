package businessPackage;

import dataAccessPackage.ClientBDA;
import dataAccessPackage.ClientDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientManager {
    private ClientDA clientBDA;

    public ClientManager()
    {
        clientBDA = new ClientBDA();
    }

    public ArrayList<Client> getAllClient() throws ExceptionsBD, SQLException
    {
        ArrayList<Client> clientList = clientBDA.getAllClient();
        return clientList;
    }

    /*public ArrayList<Client> getAllClient() throws ExceptionsBD
    {
        return clientBDA.getAllClient();
    }*/
}
