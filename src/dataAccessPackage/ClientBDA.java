package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import java.sql.Connection;
import java.util.ArrayList;

public class ClientBDA {
    public ArrayList<Client> getAllClient throws ExceptionsBD
    {
        try
        {
            ;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Problème BD Client !");
        }
    }
}
