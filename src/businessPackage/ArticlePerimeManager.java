package businessPackage;

import dataAccessPackage.ArticlePerimeBDA;
import dataAccessPackage.ArticlePerimeDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.ArticlePerime;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ArticlePerimeManager {
    private ArticlePerimeDA articlePerime;

    public ArticlePerimeManager(){this.articlePerime = new ArticlePerimeBDA();}

    public ArrayList<ArticlePerime> RechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD{
        return articlePerime.RechercheArticlePerimeEntre2Date(date1,date2);
    }

}
