package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import java.util.ArrayList;

public interface ClientDA {
    public ArrayList<Client> getAllClient() throws ExceptionsBD;
}
