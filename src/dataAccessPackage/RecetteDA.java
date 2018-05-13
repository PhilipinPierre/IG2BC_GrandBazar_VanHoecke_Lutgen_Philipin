package dataAccessPackage;

import modelPackage.Recette;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RecetteDA {
    ArrayList<Recette> getAllRecette()throws SQLException, NamingException;
}
