package dataAccessPackage;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class SingletonConnexion {
    private static Connection instance;

    public static Connection getInstance() throws SQLException, NamingException{
        if(instance == null){
            Context context = new InitialContext();
            DataSource source = (DataSource)context.lookup("jdbc/dbmagasin");
            instance = source.getConnection();
        }
        return instance;
    }
}