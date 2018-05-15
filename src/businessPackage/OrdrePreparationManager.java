package businessPackage;

import dataAccessPackage.OrdrePreparationBDA;
import dataAccessPackage.OrdrePreparationDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import java.util.ArrayList;

public class OrdrePreparationManager {
    private OrdrePreparationDA ordrePreparationBDA;

    public OrdrePreparationManager() {ordrePreparationBDA = new OrdrePreparationBDA();}

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD
    {
        return ordrePreparationBDA.getAllOrdrePreparation();
    }

    public ArrayList<OrdrePreparation> getNumSeqOrdrePreparation() throws  ExceptionsBD{
        return ordrePreparationBDA.getNumSeqOrdrePreparation();
    }

    public OrdrePreparation rechercheOrdrePreparationViaNumSeq(Integer numeroSequentiel) throws ExceptionsBD{
        return ordrePreparationBDA.rechercheOrdrePreparationViaNumSeq(numeroSequentiel);
    }

    public void SupprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD{
        ordrePreparationBDA.SupprimerOrdrePreparation(numeroSequentiel);
    }

    public void SetOrdrePreparation(OrdrePreparation ordrePreparation) throws ExceptionsBD{
        ordrePreparationBDA.SetOrdrePreparation(ordrePreparation);
    }

}