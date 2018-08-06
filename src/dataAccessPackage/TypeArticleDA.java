package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;
import java.util.ArrayList;

public interface TypeArticleDA {
    ArrayList<TypeArticle> getAllTypeArticle()throws ExceptionsBD;
    void ajouterTypeArticle(ApplicationController applicationController, TypeArticle typeArticle) throws ExceptionsBD;
}
