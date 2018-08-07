package businessPackage;

import controllerPackage.ApplicationController;
import dataAccessPackage.LotBDA;
import dataAccessPackage.LotDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Lot;
import java.sql.SQLException;
import java.util.ArrayList;

public class LotManager {
    private LotDA lotBDA;

    public LotManager() {lotBDA = new LotBDA();}

    public ArrayList<Lot> getAllLot() throws ExceptionsBD, SQLException
    {
        return lotBDA.getAllLot();
    }

    public ArrayList<Lot> rechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD, SQLException
    {
        return lotBDA.rechercheLotViaLocaliteFournisseur(localite);
    }

    public ArrayList<Lot> rechercheLotViaTypeArticle(String libelle) throws ExceptionsBD, SQLException
    {
        return lotBDA.rechercheLotViaTypeArticle(libelle);
    }

    public void ajouterLot(ApplicationController applicationController, Lot lot) throws ExceptionsBD, SQLException
    {
        lotBDA.ajouterLot(applicationController, lot);
    }

}