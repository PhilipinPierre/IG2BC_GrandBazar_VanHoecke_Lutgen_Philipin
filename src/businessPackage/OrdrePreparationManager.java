package businessPackage;

import dataAccessPackage.OrdrePreparationBDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import java.util.ArrayList;

public class OrdrePreparationManager {
    private OrdrePreparationBDA ordrePreparationBDA;

    public OrdrePreparationManager() {ordrePreparationBDA = new OrdrePreparationBDA();}

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD
    {
        ArrayList<OrdrePreparation> ordrePreparationList = ordrePreparationBDA.getAllOrdrePreparation();
        return ordrePreparationList;
    }

}