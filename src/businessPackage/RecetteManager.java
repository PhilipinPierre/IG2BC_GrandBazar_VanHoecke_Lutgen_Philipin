package businessPackage;

import controllerPackage.ApplicationController;
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
        return recetteDA.getAllRecette();
    }

    public Recette getRecette(String nom) throws ExceptionsBD{
        return recetteDA.getRecette(nom);
    }

    public void ajouterRecette(ApplicationController applicationController, Recette r) throws ExceptionsBD
    {
        recetteDA.ajouterRecette(applicationController, r);
    }
}