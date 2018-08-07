package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;
import java.util.ArrayList;

public interface FournisseurDA {
    ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD;
    void ajouterFournisseur(ApplicationController applicationController, Fournisseur fournisseur) throws ExceptionsBD;
}
