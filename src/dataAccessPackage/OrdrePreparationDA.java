package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.*;
import modelPackage.*;
import java.util.ArrayList;

public interface OrdrePreparationDA {
    ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD;
    void setOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD;
    void supprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD;
    void modifierOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD;
}