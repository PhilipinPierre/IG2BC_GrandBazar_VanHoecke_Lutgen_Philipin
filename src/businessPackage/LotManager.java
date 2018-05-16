package businessPackage;

import dataAccessPackage.LotBDA;
import dataAccessPackage.LotDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Lot;
import java.util.ArrayList;

public class LotManager {
    private LotDA lotBDA;

    public LotManager() {lotBDA = new LotBDA();}

    public ArrayList<Lot> getAllLot() throws ExceptionsBD
    {
        return lotBDA.getAllLot();
    }

    public ArrayList<Lot> RechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD{
        return lotBDA.RechercheLotViaLocaliteFournisseur(localite);
    }

    public ArrayList<Lot> RechercheLotViaTypeArticle(Integer codeBarre) throws ExceptionsBD{
        return lotBDA.RechercheLotViaTypeArticle(codeBarre);
    }

}