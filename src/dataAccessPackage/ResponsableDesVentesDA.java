package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ResponsableDesVentes;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ResponsableDesVentesDA {
    ArrayList<ResponsableDesVentes> getAllResponsableDesVentes() throws ExceptionsBD, SQLException;
}
