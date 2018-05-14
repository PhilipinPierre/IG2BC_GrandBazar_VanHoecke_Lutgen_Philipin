package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ArticlePerime;
import java.util.ArrayList;

public interface ArticlePerimeDA {
    ArrayList<ArticlePerime> getAllArticlePerime() throws ExceptionsBD;
}
