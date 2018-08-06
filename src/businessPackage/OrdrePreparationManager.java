package businessPackage;

import controllerPackage.ApplicationController;
import dataAccessPackage.*;
import exceptionsPackage.*;
import modelPackage.OrdrePreparation;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrdrePreparationManager {
    private OrdrePreparationDA ordrePreparationBDA;

    public OrdrePreparationManager() {ordrePreparationBDA = new OrdrePreparationBDA();}

    public OrdrePreparation getOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD{
        return ordrePreparationBDA.getOrdrePreparation(numeroSequentiel);
    }

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD
    {
        return ordrePreparationBDA.getAllOrdrePreparation();
    }

    public ArrayList<Integer> getNumSeqOrdrePreparation() throws  ExceptionsBD{
        return ordrePreparationBDA.getNumSeqOrdrePreparation();
    }

    public void supprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD{
        ordrePreparationBDA.supprimerOrdrePreparation(numeroSequentiel);
    }

    public void setOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD{
        ordrePreparationBDA.setOrdrePreparation(applicationController, ordrePreparation);
    }

    public void modifierOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD{
        ordrePreparationBDA.modifierOrdrePreparation(applicationController, ordrePreparation);
    }

}