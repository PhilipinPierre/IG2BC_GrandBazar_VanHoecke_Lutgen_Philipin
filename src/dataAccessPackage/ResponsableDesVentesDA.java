package dataAccessPackage;

import modelPackage.ResponsableDesVentes;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ResponsableDesVentesDA {
    ArrayList<ResponsableDesVentes> getAllResponsableDesVentes() throws SQLException, NamingException;
}
