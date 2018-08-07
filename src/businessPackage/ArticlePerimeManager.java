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

    public ArrayList<ArticlePerime> rechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD{
        return articlePerime.rechercheArticlePerimeEntre2Date(date1,date2);
    }

}
