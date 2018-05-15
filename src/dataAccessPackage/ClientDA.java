package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import java.util.ArrayList;

public interface ClientDA {
    ArrayList<Client> getAllClient() throws ExceptionsBD;
}
