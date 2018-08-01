package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Ingredient;
import java.util.ArrayList;

public interface IngredientDA {
    ArrayList<Ingredient> getAllIngredient()throws ExceptionsBD;
}
