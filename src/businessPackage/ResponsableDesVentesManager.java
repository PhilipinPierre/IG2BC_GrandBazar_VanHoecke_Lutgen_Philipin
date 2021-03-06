package businessPackage;

import dataAccessPackage.ResponsableDesVentesBDA;
import dataAccessPackage.ResponsableDesVentesDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.ResponsableDesVentes;
import java.util.ArrayList;

public class ResponsableDesVentesManager {
    private ResponsableDesVentesDA responsableDesVentes;

    public ResponsableDesVentesManager() {
        responsableDesVentes = new ResponsableDesVentesBDA();
    }

    public ArrayList<ResponsableDesVentes> getAllResponsableDesVentes() throws ExceptionsBD{
        return responsableDesVentes.getAllResponsableDesVentes();
    }
}
