package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Cuisinier;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CuisinierDA {
    ArrayList<Cuisinier> getAllCuisinier() throws ExceptionsBD;
}
