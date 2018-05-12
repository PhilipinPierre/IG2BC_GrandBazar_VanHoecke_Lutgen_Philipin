package dataAccessPackage;

import modelPackage.ArticlePerime;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ArticlePerimeDA {
    ArrayList<ArticlePerime> getAllArticlePerime() throws SQLException, NamingException;
}
