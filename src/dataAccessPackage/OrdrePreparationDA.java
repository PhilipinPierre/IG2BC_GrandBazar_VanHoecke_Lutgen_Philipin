package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;

import javax.naming.NamingException;
import javax.print.attribute.standard.MediaSize;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdrePreparationDA {
    ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD;
    ArrayList<OrdrePreparation> getNumSeqOrdrePreparation()throws ExceptionsBD;
    void SetOrdrePreparation(OrdrePreparation ordrePreparation) throws ExceptionsBD;
    void SupprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD;
    void ModifierOrdrePreparation(OrdrePreparation ordrePreparation) throws ExceptionsBD;
}
