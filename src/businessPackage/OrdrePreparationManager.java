package businessPackage;

import controllerPackage.ApplicationController;
import dataAccessPackage.*;
import exceptionsPackage.*;
import modelPackage.OrdrePreparation;
import java.util.ArrayList;

public class OrdrePreparationManager {
    private OrdrePreparationDA ordrePreparationBDA;

    public OrdrePreparationManager() {ordrePreparationBDA = new OrdrePreparationBDA();}

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD
    {
        return ordrePreparationBDA.getAllOrdrePreparation();
    }

    public ArrayList<Integer> getNumSeqOrdrePreparation() throws  ExceptionsBD{
        return ordrePreparationBDA.getNumSeqOrdrePreparation();
    }

    public void SupprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD{
        ordrePreparationBDA.SupprimerOrdrePreparation(numeroSequentiel);
    }

    public void SetOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD{
        ordrePreparationBDA.SetOrdrePreparation(applicationController, ordrePreparation);
    }

    public void ModifierOrdrePreparation(OrdrePreparation ordrePreparation) throws ExceptionsBD{
        ordrePreparationBDA.ModifierOrdrePreparation(ordrePreparation);
    }

}