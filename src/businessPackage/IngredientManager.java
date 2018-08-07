package businessPackage;

import controllerPackage.ApplicationController;
import dataAccessPackage.IngredientBDA;
import dataAccessPackage.IngredientDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Ingredient;
import java.util.ArrayList;

public class IngredientManager {
    private IngredientDA ingredientDA;

    public IngredientManager() {ingredientDA = new IngredientBDA();}

    public ArrayList<Ingredient> getAllIngredient() throws ExceptionsBD
    {
        return ingredientDA.getAllIngredient();
    }

    public void ajouterIngredient(ApplicationController applicationController, Ingredient i) throws ExceptionsBD
    {
        ingredientDA.ajouterIngredient(applicationController, i);
    }

}