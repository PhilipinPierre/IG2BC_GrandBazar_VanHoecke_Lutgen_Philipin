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