package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import java.util.ArrayList;

public interface OrdrePreparationDA {
    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD;
}
