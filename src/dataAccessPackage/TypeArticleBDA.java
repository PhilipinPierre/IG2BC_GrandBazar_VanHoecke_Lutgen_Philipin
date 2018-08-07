package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.CategorieArticle;
import modelPackage.TypeArticle;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TypeArticleBDA implements TypeArticleDA{
    public ArrayList<TypeArticle> getAllTypeArticle() throws ExceptionsBD{
        try {
            ArrayList<TypeArticle> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from typearticle order by CodeBarre";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();

            while (donnees.next()) {
                TypeArticle typeArticle = new TypeArticle();
                typeArticle.setLibelle(donnees.getString("libelle"));
                typeArticle.setCodeBarre(donnees.getInt("codeBarre"));
                liste.add(typeArticle);
            }
            return liste;
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la echerche de tout les types d'articles");
        }
    }

    public TypeArticle getTypeArticle(int codeBarre) throws ExceptionsBD{
        TypeArticle typeArticle = new TypeArticle();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from typearticle where codebarre = " + codeBarre;
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            completerTypeArticle(donnees);
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la recherche de type d'article vie leur code barra");
        }
        return typeArticle;
    }

    public Integer rechercheTypeArticleViaLibelle(String libelle) throws ExceptionsBD{
        Integer codeBarre;
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from typearticle where libelle = " + libelle;
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            codeBarre =  donnees.getInt("codebarre");
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la recherche d'une type d'article via son libellé");
        }
        return codeBarre;
    }


    public void ajouterTypeArticle(ApplicationController applicationController, TypeArticle typeArticle) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into typearticle values (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);

            //POUR SAVOIR LE CODE BARRE
            ArrayList <TypeArticle> listeTypeArticle = applicationController.getAllTypeArticle();
            int codeBarre = 1;
            for(TypeArticle ta : listeTypeArticle)
            {
                codeBarre++;
            }

            preparedStatement.setInt(1, codeBarre);
            preparedStatement.setString(2, typeArticle.getLibelle());
            preparedStatement.setDouble(3, typeArticle.getPrix());
            preparedStatement.setInt(4, typeArticle.getQuantiteeEnStock());

            if(typeArticle.getDatePromotionDebut() == null)
                preparedStatement.setNull(5, Types.TIME);
            else
                preparedStatement.setDate(5, new java.sql.Date(typeArticle.getDatePromotionDebut().getTimeInMillis()));

            if(typeArticle.getDatePromotionFin() == null)
                preparedStatement.setNull(6, Types.TIME);
            else
                preparedStatement.setDate(6, new java.sql.Date(typeArticle.getDatePromotionFin().getTimeInMillis()));

            preparedStatement.setBoolean(7, typeArticle.getEstPerissable());

            if(typeArticle.getQuantiteeMinimal() == null)
                preparedStatement.setNull(8, Types.INTEGER);
            else
                preparedStatement.setInt(8, typeArticle.getQuantiteeMinimal());
            preparedStatement.setString(9, typeArticle.getId().getId());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de l'ajout d'un type d'article à la base de données");
        }
    }

    protected static TypeArticle completerTypeArticle(ResultSet donnees) throws SQLException
    {
        TypeArticle typeArticle = new TypeArticle();

        typeArticle.setCodeBarre(donnees.getInt("codebarre"));

        return typeArticle;
    }
}
