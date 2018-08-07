package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Cuisinier;
import java.util.ArrayList;

public interface CuisinierDA {
    ArrayList<Cuisinier> getAllCuisinier() throws ExceptionsBD;
}
