package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ArticlePerime;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public interface ArticlePerimeDA {
    ArrayList<ArticlePerime> rechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD;
}
