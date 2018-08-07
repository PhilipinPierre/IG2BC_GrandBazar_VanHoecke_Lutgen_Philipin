package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Ingredient;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientDA {
    ArrayList<Ingredient> getAllIngredient()throws ExceptionsBD, SQLException;
    void ajouterIngredient(ApplicationController applicationController, Ingredient i) throws ExceptionsBD, SQLException;
}
