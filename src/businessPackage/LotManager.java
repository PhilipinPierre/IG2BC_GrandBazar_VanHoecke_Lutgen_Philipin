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
        ArrayList<Lot> lotList = lotBDA.getAllLot();
        return lotList;
    }

}