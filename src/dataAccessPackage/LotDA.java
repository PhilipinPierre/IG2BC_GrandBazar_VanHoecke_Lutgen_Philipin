package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Lot;
import java.util.ArrayList;

public interface LotDA {
    public ArrayList<Lot> getAllLot() throws ExceptionsBD;
}
