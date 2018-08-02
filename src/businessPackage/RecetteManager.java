package businessPackage;

import dataAccessPackage.RecetteBDA;
import dataAccessPackage.RecetteDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Recette;
import java.util.ArrayList;

public class RecetteManager {
    private RecetteDA recetteDA;

    public RecetteManager() {recetteDA = new RecetteBDA();}

    public ArrayList<Recette> getAllRecette() throws ExceptionsBD
    {
        return RecetteDA.getAllRecette();
    }

    public Recette getRecette(String nom) throws ExceptionsBD{
        return RecetteDA.getRecette(nom);
    }
}