package businessPackage;

import dataAccessPackage.FournisseurBDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;
import java.util.ArrayList;

public class FournisseurManager {

    private FournisseurBDA fournisseurBDA;

    public FournisseurManager() {fournisseurBDA = new FournisseurBDA();}

    public ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD
    {
        ArrayList<Fournisseur> fournisseurList = fournisseurBDA.getAllFournisseur();
        return fournisseurList;
    }


}