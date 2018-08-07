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

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD, SQLException
    {
        return ordrePreparationBDA.getAllOrdrePreparation();
    }

    public ArrayList<Integer> getNumSeqOrdrePreparation() throws  ExceptionsBD, SQLException
    {
        return ordrePreparationBDA.getNumSeqOrdrePreparation();
    }

    public void supprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD, SQLException
    {
        ordrePreparationBDA.supprimerOrdrePreparation(numeroSequentiel);
    }

    public void setOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD, SQLException
    {
        ordrePreparationBDA.setOrdrePreparation(applicationController, ordrePreparation);
    }

    public void modifierOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD, SQLException
    {
        ordrePreparationBDA.modifierOrdrePreparation(applicationController, ordrePreparation);
    }

}