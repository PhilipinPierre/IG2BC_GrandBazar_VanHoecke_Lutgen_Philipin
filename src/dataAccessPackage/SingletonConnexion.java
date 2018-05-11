package dataAccessPackage;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection instance;

    private SingletonConnexion(){}

    public static Connection getInstance() throws SQLException, NamingException{
        if(instance == null){
            Context context = new InitialContext();
            DataSource source = (DataSource)context.lookup("jdbc/BDGB");
            instance = source.getConnection();
        }
        return instance;
    }
}
