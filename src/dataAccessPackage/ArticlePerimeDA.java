package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ArticlePerime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface ArticlePerimeDA {
    ArrayList<ArticlePerime> getAllArticlePerime() throws ExceptionsBD;
    ArrayList<ArticlePerime> RechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD;
}
