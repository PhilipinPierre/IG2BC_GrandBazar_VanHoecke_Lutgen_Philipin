package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.CategorieArticle;
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
            String requeteSQL = "select * from typearticle order by CodeBarre";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();

            while (donnees.next()) {
                TypeArticle typeArticle = completerTypeArticle(donnees);
                liste.add(typeArticle);
            }
            return liste;
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la echerche de tout les types d'articles");
        }
    }

    public static TypeArticle getTypeArticle(int codeBarre) throws ExceptionsBD{
        TypeArticle typeArticle = new TypeArticle();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from typearticle where codebarre = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setInt(1,codeBarre);
            ResultSet donnees = preparedStatement.executeQuery();

            typeArticle = completerTypeArticle(donnees);
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la recherche de type d'article via leur code barre");
        }
        return typeArticle;
    }

    public TypeArticle rechercheTypeArticleViaLibelle(String libelle) throws ExceptionsBD{
        TypeArticle typeArticle = new TypeArticle();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from typearticle where libelle = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setString(1,libelle);
            ResultSet donnees = preparedStatement.executeQuery();
            if(!donnees.wasNull()) {
                System.out.println("donnée non null");
                typeArticle = completerTypeArticle(donnees);
            }
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la recherche d'une type d'article via son libellé");
        }
        return typeArticle;
    }


    public void ajouterTypeArticle(TypeArticle typeArticle) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into typearticle values (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);



            //POUR SAVOIR LE CODE BARRE
            ArrayList <TypeArticle> listeTypeArticle = getAllTypeArticle();
            int codeBarre = 1;
            for(TypeArticle ta : listeTypeArticle)
            {
                codeBarre++;
            }

            preparedStatement.setInt(1, codeBarre);
            preparedStatement.setString(2, typeArticle.getLibelle());
            preparedStatement.setDouble(3, typeArticle.getPrix());
            preparedStatement.setInt(4, typeArticle.getQuantiteeEnStock());

            preparedStatement.setDate(5, new java.sql.Date(typeArticle.getDatePromotionDebut().getTimeInMillis()));

            preparedStatement.setDate(6, new java.sql.Date(typeArticle.getDatePromotionFin().getTimeInMillis()));

            preparedStatement.setBoolean(7, typeArticle.getEstPerissable());
            preparedStatement.setInt(8, typeArticle.getQuantiteeMinimal());
            preparedStatement.setString(9, typeArticle.getId().getId());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de l'ajout d'un type d'article à la base de données");
        }
    }

    protected static TypeArticle completerTypeArticle(ResultSet donnees) throws ExceptionsBD
    {
        TypeArticle typeArticle = new TypeArticle();
        try {
            int codeBarre = donnees.getInt("codeBarre");
            typeArticle.setCodeBarre(codeBarre);
            String libelle = donnees.getString("libelle");
            typeArticle.setLibelle(libelle);
            double prix = Double.valueOf(donnees.getString("prix"));
            typeArticle.setPrix(prix);
            int quantiteEnStock = donnees.getInt("quantiteEnStock");
            typeArticle.setQuantiteeEnStock(quantiteEnStock);
            if (donnees.getDate("datepromotiondebut") != null) {
                GregorianCalendar datePromoDbt = new GregorianCalendar();
                datePromoDbt.setTime(donnees.getDate("datepromotiondebut"));
                typeArticle.setDatePromotionDebut(datePromoDbt);
            }
            if (donnees.getDate("datepromotionfin") != null) {
                GregorianCalendar datePromoFin = new GregorianCalendar();
                datePromoFin.setTime(donnees.getDate("datepromotionfin"));
                typeArticle.setDatePromotionFin(datePromoFin);
            }
            Boolean estPerissable = donnees.getBoolean("estPerissable");
            typeArticle.setEstPerissable(estPerissable);
            int quantiteMinimal = donnees.getInt("quantiteMinimal");
            typeArticle.setQuantiteeMinimal(quantiteMinimal);
            String id = donnees.getString("id");
            //CategorieArticle categorieArticle = CategorieArcticleBDA.getCategorieArticle(id);

        } catch (Exception e){
            throw new ExceptionsBD("erreur lors de l'allocation des information au programme");
        }
        return typeArticle;
    }
}
