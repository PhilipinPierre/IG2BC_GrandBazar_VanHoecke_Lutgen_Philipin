package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ResponsableDesVentes;
import java.util.ArrayList;

public interface ResponsableDesVentesDA {
    public ArrayList<ResponsableDesVentes> getAllResponsableDesVentes() throws ExceptionsBD;
}
