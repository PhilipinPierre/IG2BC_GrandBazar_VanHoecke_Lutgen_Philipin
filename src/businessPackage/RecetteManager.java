package businessPackage;

import dataAccessPackage.RecetteBDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Recette;
import java.util.ArrayList;

public class RecetteManager {
    private RecetteBDA recetteBDA;

    public RecetteManager() {recetteBDA = new RecetteBDA();}

    public ArrayList<Recette> getAllRecette() throws ExceptionsBD
    {
        ArrayList<Recette> recetteList = recetteBDA.getAllRecette();
        return recetteList;
    }
}