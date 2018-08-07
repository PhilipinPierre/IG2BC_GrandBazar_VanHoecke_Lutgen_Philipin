package businessPackage;

import controllerPackage.ApplicationController;
import dataAccessPackage.TypeArticleBDA;
import dataAccessPackage.TypeArticleDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeArticleManager {
    private TypeArticleDA typeArticleBDA;

    public TypeArticleManager() {typeArticleBDA = new TypeArticleBDA();}

    public ArrayList<TypeArticle> getAllTypeArticle() throws ExceptionsBD, SQLException
    {
        ArrayList<TypeArticle> typeArticleList = typeArticleBDA.getAllTypeArticle();
        return typeArticleList;
    }
    public void ajouterTypeArticle(ApplicationController applicationController, TypeArticle typeArticle) throws ExceptionsBD, SQLException
    {
        typeArticleBDA.ajouterTypeArticle(applicationController, typeArticle);
    }
}