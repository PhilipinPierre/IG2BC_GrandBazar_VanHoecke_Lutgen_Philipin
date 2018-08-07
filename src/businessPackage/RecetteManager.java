package businessPackage;

import controllerPackage.ApplicationController;
import dataAccessPackage.RecetteBDA;
import dataAccessPackage.RecetteDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Recette;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecetteManager {
    private RecetteDA recetteDA;

    public RecetteManager() {recetteDA = new RecetteBDA();}

    public ArrayList<Recette> getAllRecette() throws ExceptionsBD, SQLException
    {
        return recetteDA.getAllRecette();
    }

    public Recette getRecette(String nom) throws ExceptionsBD, SQLException
    {
        return recetteDA.getRecette(nom);
    }

    public void ajouterRecette(ApplicationController applicationController, Recette r) throws ExceptionsBD, SQLException
    {
        recetteDA.ajouterRecette(applicationController, r);
    }
}