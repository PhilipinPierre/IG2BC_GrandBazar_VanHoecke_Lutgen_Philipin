package businessPackage;

import controllerPackage.ApplicationController;
import dataAccessPackage.IngredientBDA;
import dataAccessPackage.IngredientDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Ingredient;

public class IngredientManager {
    private IngredientDA ingredientDA;

    public IngredientManager() {ingredientDA = new IngredientBDA();}

    public void ajouterIngredient(ApplicationController applicationController, Ingredient i) throws ExceptionsBD
    {
        ingredientDA.ajouterIngredient(applicationController, i);
    }

}