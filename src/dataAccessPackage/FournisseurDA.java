package dataAccessPackage;

import exceptionsPackage.*;
import modelPackage.*;
import java.util.*;

public interface FournisseurDA {
    public ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD;
}

