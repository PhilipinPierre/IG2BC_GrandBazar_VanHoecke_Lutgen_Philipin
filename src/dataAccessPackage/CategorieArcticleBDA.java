package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.CategorieArticle;

import javax.naming.NamingException;
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
                CategorieArticle categorieArticle = new CategorieArticle();
                CompleterCategorieArticle(donnees, categorieArticle);
                liste.add(categorieArticle);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Problème recherche catégorie article");
        }

    }

    private void CompleterCategorieArticle(ResultSet donnees, CategorieArticle categorieArticle) throws SQLException{
        categorieArticle.setId(donnees.getString("id"));
        categorieArticle.setLibelle(donnees.getString("libelle"));
    }

}
