package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.*;
import modelPackage.*;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdrePreparationDA {
    ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD, SQLException;
    ArrayList<Integer> getNumSeqOrdrePreparation() throws ExceptionsBD, SQLException;
    void setOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD, SQLException;
    void supprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD, SQLException;
    void modifierOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD, SQLException;
}