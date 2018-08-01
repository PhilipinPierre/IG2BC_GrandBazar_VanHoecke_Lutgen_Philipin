package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;
import java.util.ArrayList;

public interface FournisseurDA {
    ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD;
}
