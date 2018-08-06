package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.CategorieArticle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieArcticleBDA implements CategorieArticleDA {
    public ArrayList<CategorieArticle> getAllCategorieArticle()throws ExceptionsBD {
        try
        {
            ArrayList<CategorieArticle> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from categoriearticle";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                liste.add(completerCategorieArticle(donnees));
            }
            return liste;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ExceptionsBD("Problème recherche catégorie article");
        }

    }

    public static CategorieArticle getCategorieArticle(String id) throws ExceptionsBD{
        CategorieArticle categorieArticle;
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from categoriearticle where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setString(1,id);
            ResultSet donnee = preparedStatement.executeQuery();

            categorieArticle = completerCategorieArticle(donnee);
        } catch (Exception e){
            throw new ExceptionsBD("recherche d'une catégorie d'article impossible");
        }

        return categorieArticle;
    }

    private static CategorieArticle completerCategorieArticle(ResultSet donnees) throws ExceptionsBD{
        CategorieArticle categorieArticle = new CategorieArticle();
        try {
            String id = donnees.getString("id");
            categorieArticle.setId(id);
            String libelle = donnees.getString("libelle");
            categorieArticle.setLibelle(libelle);
        } catch (Exception e){
            System.out.println("ex");
            throw new ExceptionsBD("accès à certaine donnée des catégorie d'article impossible");
        }
        return categorieArticle;
    }

}
