package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Recette;
import java.util.ArrayList;

public interface RecetteDA {
    ArrayList<Recette> getAllRecette() throws ExceptionsBD;
}
