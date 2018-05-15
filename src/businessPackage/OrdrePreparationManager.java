package businessPackage;

import dataAccessPackage.OrdrePreparationBDA;
import dataAccessPackage.OrdrePreparationDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import java.util.ArrayList;

public class OrdrePreparationManager {
    private OrdrePreparationDA ordrePreparationBDA;

    public OrdrePreparationManager() {ordrePreparationBDA = new OrdrePreparationBDA();}

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD
    {
        ArrayList<OrdrePreparation> ordrePreparationList = ordrePreparationBDA.getAllOrdrePreparation();
        return ordrePreparationList;
    }

    public ArrayList<Integer> getNumSeqOrdrePreparation() throws  ExceptionsBD{
        return ordrePreparationBDA.getNumSeqOrdrePreparation();
    }

}