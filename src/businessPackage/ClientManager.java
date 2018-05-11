package businessPackage;

import dataAccessPackage.ClientBDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import java.util.ArrayList;

public class ClientManager {
    private ClientBDA clientBDA;

    public ClientManager()
    {
        clientBDA = new ClientBDA();
    }

    public ArrayList<Client> getAllClient() throws ExceptionsBD
    {
        ArrayList<Client> clientList = clientBDA.getAllClient();

        return clientList;
    }

    /*public ArrayList<Client> getAllClient() throws ExceptionsBD
    {
        return clientBDA.getAllClient();
    }*/
}
