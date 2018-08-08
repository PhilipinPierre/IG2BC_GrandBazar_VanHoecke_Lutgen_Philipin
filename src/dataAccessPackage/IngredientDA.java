package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Ingredient;
import java.util.ArrayList;

public interface IngredientDA {
    void ajouterIngredient(ApplicationController applicationController, Ingredient i) throws ExceptionsBD;
}
