package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TypeArticleBDA implements TypeArticleDA{
    public ArrayList<TypeArticle> getAllTypeArticle() throws ExceptionsBD{
        try {
            ArrayList<TypeArticle> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from typearticle order by Libelle";
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

    protected static TypeArticle completerTypeArticle(ResultSet donnees) throws SQLException
    {
        TypeArticle typeArticle = new TypeArticle();

        typeArticle.setCodeBarre(donnees.getInt("codebarre"));
        /*typeArticle.setLibelle(donnees.getString("libelle"));
        typeArticle.setPrix(donnees.getDouble("prix"));
        typeArticle.setQuantiteeEnStock(donnees.getInt("quantiteenstock"));
        if(donnees.getDate("datepromotiondebut") != null){
            GregorianCalendar datePromoDbt = new GregorianCalendar();
            datePromoDbt.setTime(donnees.getDate("datepromotiondebut"));
            typeArticle.setDatePromotionDebut(datePromoDbt);
        }
        if(donnees.getDate("datepromotionfin") != null){
            GregorianCalendar datePromoFin = new GregorianCalendar();
            datePromoFin.setTime(donnees.getDate("datepromotionfin"));
            typeArticle.setDatePromotionFin(datePromoFin);
        }
        typeArticle.setEstPerissable(donnees.getBoolean("estperissable"));
        typeArticle.setQuantiteeMinimal(donnees.getInt("quantiteminimale"));*/

        return typeArticle;
    }
}
