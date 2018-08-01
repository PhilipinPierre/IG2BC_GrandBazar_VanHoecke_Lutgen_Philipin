package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;
import java.util.ArrayList;

public interface TypeArticleDA {
    ArrayList<TypeArticle> getAllTypeArticle()throws ExceptionsBD;
}
