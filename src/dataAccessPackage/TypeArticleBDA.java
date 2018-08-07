package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;
import java.sql.*;
import java.util.ArrayList;

public class TypeArticleBDA implements TypeArticleDA{
    public ArrayList<TypeArticle> getAllTypeArticle() throws ExceptionsBD, SQLException
    {
        ArrayList<TypeArticle> liste = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from typearticle order by CodeBarre";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();

        while (donnees.next())
        {
            TypeArticle typeArticle = new TypeArticle();
            typeArticle.setLibelle(donnees.getString("libelle"));
            typeArticle.setCodeBarre(donnees.getInt("codeBarre"));
            liste.add(typeArticle);
        }
        return liste;
    }

    public TypeArticle getTypeArticle(int codeBarre) throws ExceptionsBD, SQLException
    {
        TypeArticle typeArticle = new TypeArticle();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from typearticle where codebarre = " + codeBarre;
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();
        completerTypeArticle(donnees);

        return typeArticle;
    }

    public Integer rechercheTypeArticleViaLibelle(String libelle) throws ExceptionsBD, SQLException
    {
        Integer codeBarre;

        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from typearticle where libelle = " + libelle;
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();
        codeBarre =  donnees.getInt("codebarre");

        return codeBarre;
    }


    public void ajouterTypeArticle(ApplicationController applicationController, TypeArticle typeArticle) throws ExceptionsBD, SQLException
    {
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
    }

    protected static TypeArticle completerTypeArticle(ResultSet donnees) throws SQLException
    {
        TypeArticle typeArticle = new TypeArticle();

        typeArticle.setCodeBarre(donnees.getInt("codebarre"));

        return typeArticle;
    }
}
