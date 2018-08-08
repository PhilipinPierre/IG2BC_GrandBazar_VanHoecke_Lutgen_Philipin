package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ArticlePerime;
import modelPackage.MembreDuPersonnel;
import modelPackage.TypeArticle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ArticlePerimeBDA implements ArticlePerimeDA {
    public ArrayList<ArticlePerime> getAllArticlePerime() throws ExceptionsBD {
        try
        {
            ArrayList<ArticlePerime> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from articleperime";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                ArticlePerime articlePerime = new ArticlePerime();
                completerArticlePerime(donnees, articlePerime);
                liste.add(articlePerime);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("la recherche de tout les articles périmé dans la base de donnée");
        }
    }

    public ArrayList<ArticlePerime> rechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD{
        try{
            if(date1.getTimeInMillis() > date2.getTimeInMillis()){
                GregorianCalendar date = date1;
                date1 = date2;
                date2 = date;
            }
            ArrayList<ArticlePerime> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from articleperiem " +
                    " join membredupersonnel m ON articleperiem.Matricule = m.Matricule" +
                    " join typearticle t ON articleperiem.CodeBarre = t.CodeBarre " +
                    " where articleperiem.Matricule = m.Matricule" +
                    " and articleperiem.CodeBarre = t.CodeBarre " +
                    " and articleperiem.Date  BETWEEN ? AND ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setDate(1, new java.sql.Date(date1.getTimeInMillis()));
            preparedStatement.setDate(2, new java.sql.Date(date2.getTimeInMillis()));
            ResultSet donnees = preparedStatement.executeQuery();

            while(donnees.next()){
                ArticlePerime articlePerime = new ArticlePerime();

                MembreDuPersonnel membreDuPersonnel = new MembreDuPersonnel();
                membreDuPersonnel.setNom(donnees.getString("nom"));
                membreDuPersonnel.setPrenom(donnees.getString("prenom"));
                articlePerime.setMatricule(membreDuPersonnel);

                TypeArticle typeArticle = new TypeArticle();
                typeArticle.setLibelle(donnees.getString("libelle"));
                articlePerime.setCodeBarre(typeArticle);

                articlePerime.setQuantiteJetee(donnees.getInt("quantiteJete"));

                liste.add(articlePerime);
            }
            return liste;
        }catch (Exception e){
            throw new ExceptionsBD("la recherche des articles périmé entre "+ date1.getWeeksInWeekYear() + " et "+ date2.getWeeksInWeekYear());
        }
    }

    private void completerArticlePerime(ResultSet donnees, ArticlePerime articlePerime) throws SQLException
    {
        articlePerime.setId(donnees.getString("id"));
        Integer quantiteJete = donnees.getInt("quantitejete");
        articlePerime.setQuantiteJetee(quantiteJete);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(donnees.getDate("date"));
        articlePerime.setDate(date);
        MembreDuPersonnelBDA membreDuPersonnelBDA = new MembreDuPersonnelBDA();
        MembreDuPersonnel matricule = new MembreDuPersonnel();
        matricule.setMatricule(donnees.getInt("matricule"));
        articlePerime.setMatricule(matricule);
        TypeArticle typeArticle = new TypeArticle();
        typeArticle.setCodeBarre(donnees.getInt("codebarre"));
        articlePerime.setCodeBarre(typeArticle);
    }
}
