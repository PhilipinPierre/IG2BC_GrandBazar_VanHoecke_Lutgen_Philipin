package businessPackage;

import dataAccessPackage.CategorieArcticleBDA;
import dataAccessPackage.CategorieArticleDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.CategorieArticle;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieArticleManager {
    private CategorieArticleDA categorieArticleDA;

    public CategorieArticleManager() {categorieArticleDA = new CategorieArcticleBDA();}

    public ArrayList<CategorieArticle> getAllCategorieArticle() throws ExceptionsBD, SQLException
    {
        return categorieArticleDA.getAllCategorieArticle();
    }
}