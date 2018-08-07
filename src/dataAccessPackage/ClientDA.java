package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ClientDA {
    ArrayList<Client> getAllClient() throws ExceptionsBD, SQLException;
}
