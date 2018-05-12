package dataAccessPackage;

import modelPackage.OrdrePreparation;

import javax.naming.NamingException;
import javax.print.attribute.standard.MediaSize;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdrePreparationDA {
    ArrayList<OrdrePreparation> getAllOrdrePreparation() throws SQLException, NamingException;
}
