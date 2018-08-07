package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FournisseurDA {
    ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD, SQLException;
    void ajouterFournisseur(ApplicationController applicationController, Fournisseur fournisseur) throws ExceptionsBD, SQLException;
}
