package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.*;
import modelPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdrePreparationDA {
    OrdrePreparation getOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD;
    ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD;
    ArrayList<Integer> getNumSeqOrdrePreparation() throws ExceptionsBD;
    void setOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD;
    void supprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD;
    void modifierOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD;
}