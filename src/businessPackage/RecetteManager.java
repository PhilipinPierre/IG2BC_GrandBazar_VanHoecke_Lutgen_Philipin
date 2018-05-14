package businessPackage;

import dataAccessPackage.RecetteBDA;
import dataAccessPackage.RecetteDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Recette;

import java.sql.SQLException;
import java.util.ArrayList;

public class RecetteManager {
    private RecetteDA recetteBDA;

    public RecetteManager() {recetteBDA = new RecetteBDA();}

    public ArrayList<Recette> getAllRecette() throws ExceptionsBD
    {
        return recetteBDA.getAllRecette();
    }
}