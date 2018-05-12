package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ArticlePerime;
import java.util.ArrayList;

public interface ArticlePerimeDA {
    public ArrayList<ArticlePerime> getAllArticlePerime() throws ExceptionsBD;
}
