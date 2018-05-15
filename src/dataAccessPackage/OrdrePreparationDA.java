package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;

import javax.naming.NamingException;
import javax.print.attribute.standard.MediaSize;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdrePreparationDA {
    ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD;
    ArrayList<Integer> getNumSeqOrdrePreparation()throws ExceptionsBD;
    ArrayList<OrdrePreparation> rechercheOrdrePreparationViaNumSeq(Integer numeroSequentiel) throws ExceptionsBD;
}
