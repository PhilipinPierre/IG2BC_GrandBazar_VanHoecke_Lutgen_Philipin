package dataAccessPackage;

import controllerPackage.ApplicationController;
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
                completerIngredient(donnees, ingredient);
                liste.add(ingredient);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Problème ingredients");
        }
    }

    public void ajouterIngredient(ApplicationController applicationController, Ingredient ingredient) throws ExceptionsBD
    {
        try
        {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into ingredient values (?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);

            preparedStatement.setString(1, ingredient.getNom().getNom());
            //POUR CONVERTIR LE LIBELLE DU TYPE D'ARTICLE EN MATRICULE
            String typeArticle = ingredient.getCodeBarre().getLibelle();
            ArrayList <TypeArticle> listeTypeArticle = applicationController.getAllTypeArticle();
            int matriculeTypeArticle = 0;
            for(TypeArticle ta : listeTypeArticle)
            {
                if (typeArticle.equals(ta.getLibelle()))
                    matriculeTypeArticle = ta.getCodeBarre();
            }
            preparedStatement.setInt(2, matriculeTypeArticle);
            preparedStatement.setInt(3, ingredient.getQuantitePortion());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de l'ajout d'un ingrédients à la base de données");
        }
    }

    private void completerIngredient(ResultSet donnees, Ingredient ingredient) throws SQLException{
        Integer quantitePortion = new Integer(donnees.getInt("quantiteportion"));
        ingredient.setQuantitePortion(quantitePortion);

        Recette r = new Recette();
        ingredient.setNom(r);
        TypeArticle t = new TypeArticle();
        ingredient.setCodeBarre(t);
    }
}
