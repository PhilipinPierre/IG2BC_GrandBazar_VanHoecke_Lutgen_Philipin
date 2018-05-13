package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TypeArticleDA {
    ArrayList<TypeArticle> getAllTypeArticle()throws ExceptionsBD;
}
