package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FournisseurDA {
    ArrayList<Fournisseur> getAllFOurnisseur() throws ExceptionsBD, SQLException, NamingException;
}
