package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Ingredient;
import modelPackage.Recette;
import modelPackage.TypeArticle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientBDA implements IngredientDA {
    public ArrayList<Ingredient> getAllIngredient()throws ExceptionsBD {
        try
        {
            ArrayList<Ingredient> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from ingredient ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                Ingredient ingredient = new Ingredient();
                CompleterIngredient(donnees, ingredient);
                liste.add(ingredient);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Problème ingredients");
        }
    }

    private void CompleterIngredient(ResultSet donnees, Ingredient ingredient) throws SQLException{
        Integer quantitePortion = new Integer(donnees.getInt("quantiteportion"));
        ingredient.setQuantitePortion(quantitePortion);

        Recette r = new Recette();
        ingredient.setNom(r);
        TypeArticle t = new TypeArticle();
        ingredient.setCodeBarre(t);
    }
}
