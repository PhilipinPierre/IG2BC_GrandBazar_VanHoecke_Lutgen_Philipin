package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ArticlePerime;
import modelPackage.Lot;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ArticlePerimeBDA implements ArticlePerimeDA {
    public ArrayList<ArticlePerime> getAllArticlePerime() throws ExceptionsBD{
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
        } catch (Exception e){
            throw new ExceptionsBD("recherche de tout les articles périmé");
        }
    }

    private void CompleterArticlePerime(ResultSet donnees, ArticlePerime articlePerime) throws SQLException{
        articlePerime.setId(donnees.getString("id"));
        Integer quantiteJete = new Integer(donnees.getInt("quantitejete"));
        articlePerime.setQuantiteJetee(quantiteJete);
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(donnees.getDate("date"));
        articlePerime.setDate(date);

        //A COMPLETER
    }
}
