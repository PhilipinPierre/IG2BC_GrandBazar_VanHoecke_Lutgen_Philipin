package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.*;
import java.util.*;

public class ArticlePerimeBDA implements ArticlePerimeDA {
    public ArrayList<ArticlePerime> getAllArticlePerime() throws ExceptionsBD {
        try {
            ArrayList<ArticlePerime> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from articleperime";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                ArticlePerime articlePerime = new ArticlePerime();
                CompleterArticlePerime(donnees, articlePerime);
                liste.add(articlePerime);
            }
            return liste;
        } catch (Exception e) {
            throw new ExceptionsBD("Problème BD article périmé");
        }
    }

    private void CompleterArticlePerime(ResultSet donnees, ArticlePerime articlePerime) throws SQLException {
        articlePerime.setId(donnees.getString("id"));
        Integer quantiteJete = new Integer(donnees.getInt("quantitejete"));
        articlePerime.setQuantiteJetee(quantiteJete);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(donnees.getDate("date"));
        articlePerime.setDate(date);
        TypeArticle t = new TypeArticle();
        articlePerime.setCodeBarre(t);
        MembreDuPersonnel mb = new MembreDuPersonnel();
        articlePerime.setMatricule(mb);
    }

    /*public ArrayList<ArticlePerime> effectuerRecherche1(GregorianCalendar date) throws ExceptionsBD
    {
        ArrayList<ArticlePerime> listeArticlePerime = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requete = "select * from articleperime where donnees.getTime() between date1 and date2"; // DATE DE LA RECHERCHE
            PreparedStatement prepStat = connection.prepareStatement(requete);
            prepStat.setTime(1, date);
            ResultSet donnees = prepStat.executeQuery();
            while(donnees.next())
            {
                ArticlePerime ligne = new ArticlePerime();

                GregorianCalendar date = new GregorianCalendar();
                date.setTime(donnees.getDate("datePerime"));
            }

            return listeArticlePerime;

        }catch(Exception e)
        {

            throw new DataAccessException("la base de données", "la recherche des résultats");
        }
    }*/
}