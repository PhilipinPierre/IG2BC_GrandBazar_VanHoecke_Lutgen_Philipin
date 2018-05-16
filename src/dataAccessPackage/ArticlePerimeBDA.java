package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ArticlePerime;
import modelPackage.Lot;
import modelPackage.MembreDuPersonnel;
import modelPackage.TypeArticle;

import javax.naming.NamingException;
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
                CompleterArticlePerime(donnees, articlePerime);
                liste.add(articlePerime);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("la recherche de tout les articles périmé dans la base de donnée");
        }
    }

    public ArrayList<ArticlePerime> RechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD{
        try{
            if(date1.getTimeInMillis() > date2.getTimeInMillis()){
                GregorianCalendar date = date1;
                date1 = date2;
                date2 = date;
            }
            ArrayList<ArticlePerime> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from articleperiem where date > ? and date < ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setDate(1, new java.sql.Date(date1.getTimeInMillis()));
            preparedStatement.setDate(2, new java.sql.Date(date2.getTimeInMillis()));
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                ArticlePerime articlePerime = new ArticlePerime();
                CompleterArticlePerime(donnees, articlePerime);
                System.out.println(articlePerime.getMatricule().getNom());
                liste.add(articlePerime);
            }
            return liste;
        }catch (Exception e){
            throw new ExceptionsBD("la recherche des articles périmé entre "+ date1.toString() + " et "+ date2.toString());
        }
    }

    private void CompleterArticlePerime(ResultSet donnees, ArticlePerime articlePerime) throws ExceptionsBD{
        try {
            articlePerime.setId(donnees.getString("id"));
            Integer quantiteJete = donnees.getInt("quantitejete");
            articlePerime.setQuantiteJetee(quantiteJete);
            GregorianCalendar date = new GregorianCalendar();
            date.setTime(donnees.getDate("date"));
            articlePerime.setDate(date);

            articlePerime.setMatricule(new MembreDuPersonnelBDA().getMembreDuPersonnel(articlePerime.getMatricule().getMatricule()));
            articlePerime.setCodeBarre(new TypeArticleBDA().getTypeArticle(articlePerime.getCodeBarre().getCodeBarre()));
        } catch (Exception e){
            throw new ExceptionsBD("recherche article périmé");
        }
    }
}
