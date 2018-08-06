package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;
import java.util.ArrayList;

public interface TypeArticleDA {
    ArrayList<TypeArticle> getAllTypeArticle()throws ExceptionsBD;
    void ajouterTypeArticle( TypeArticle typeArticle) throws ExceptionsBD;
    TypeArticle rechercheTypeArticleViaLibelle(String libelle) throws ExceptionsBD;

}
