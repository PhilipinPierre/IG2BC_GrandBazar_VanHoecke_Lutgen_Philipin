package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;
import java.sql.*;
import java.util.*;

public class TypeArticleBDA implements TypeArticleDA{
    public ArrayList<TypeArticle> getAllTypeArticle() throws ExceptionsBD {
        try
        {
            ArrayList<TypeArticle> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from typearticle";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();

            while(donnees.next()){
                TypeArticle typeArticle = new TypeArticle();
                completerTypeArticle(donnees, typeArticle);
                liste.add(typeArticle);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Probleme BD TYPEarticle");
        }
    }

    private void completerTypeArticle(ResultSet donnees, TypeArticle typeArticle) throws SQLException{
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
    }
}
