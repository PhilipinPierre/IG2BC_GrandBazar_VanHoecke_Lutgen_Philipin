package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.CategorieArticle;
import java.util.ArrayList;

public interface CategorieArticleDA {
    ArrayList<CategorieArticle> getAllCategorieArticle() throws ExceptionsBD;
}
