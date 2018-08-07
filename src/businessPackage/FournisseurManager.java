package businessPackage;

import controllerPackage.ApplicationController;
import dataAccessPackage.FournisseurBDA;
import dataAccessPackage.FournisseurDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;
import java.sql.SQLException;
import java.util.ArrayList;

public class FournisseurManager {

    private FournisseurDA fournisseurBDA;

    public FournisseurManager() {fournisseurBDA = new FournisseurBDA();}

    public ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD, SQLException
    {
        ArrayList<Fournisseur> fournisseurList = fournisseurBDA.getAllFournisseur();
        return fournisseurList;
    }

    public void ajouterFournisseur(ApplicationController applicationController, Fournisseur fournisseur) throws ExceptionsBD, SQLException
    {
        fournisseurBDA.ajouterFournisseur(applicationController, fournisseur);
    }


}