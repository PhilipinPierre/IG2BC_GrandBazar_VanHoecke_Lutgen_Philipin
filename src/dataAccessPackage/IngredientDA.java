package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Ingredient;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IngredientDA {
    ArrayList<Ingredient> getAllIngredient()throws ExceptionsBD;
}
