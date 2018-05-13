package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Recette;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RecetteDA {
    ArrayList<Recette> getAllRecette()throws ExceptionsBD;
}
