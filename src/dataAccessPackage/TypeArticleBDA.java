package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TypeArticleBDA implements TypeArticleDA{
    public ArrayList<TypeArticle> getAllTypeArticle()throws ExceptionsBD{
        ArrayList<TypeArticle> liste = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from typearticle";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();

            while (donnees.next()) {
                TypeArticle typeArticle = new TypeArticle();
                typeArticle.setLibelle(donnees.getString("Libelle"));
                liste.add(typeArticle);
            }
        } catch (Exception e){
            throw new ExceptionsBD("recherche de tout les type d'articles");
        }
        return liste;
    }

    protected static TypeArticle getTypeArticle(int codeBarre){
        return new TypeArticle();
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
            throw new ExceptionsBD("Recherche d'une type d'article via son libellé");
        }
        return codeBarre;
    }

    private static void completerTypeArticle(ResultSet donnees, TypeArticle typeArticle) throws SQLException{
        typeArticle.setCodeBarre(donnees.getInt("codebarre"));
        typeArticle.setLibelle(donnees.getString("libelle"));
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
        typeArticle.setQuantiteeMinimal(donnees.getInt("quantiteminimale"));

        //A COMPLETER
    }
}
