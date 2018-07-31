package dataAccessPackage;

import exceptionsPackage.*;
import modelPackage.*;
import java.util.ArrayList;

public interface OrdrePreparationDA {
    ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD;
    ArrayList<Integer> getNumSeqOrdrePreparation() throws ExceptionsBD;
    void SetOrdrePreparation(OrdrePreparation ordrePreparation) throws ExceptionsBD;
    void SupprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD;
    void ModifierOrdrePreparation(OrdrePreparation ordrePreparation) throws ExceptionsBD;
}