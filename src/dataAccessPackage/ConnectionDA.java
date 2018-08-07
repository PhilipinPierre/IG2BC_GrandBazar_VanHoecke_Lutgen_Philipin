package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import java.sql.SQLException;

public interface ConnectionDA
{
    void fermetureConnection() throws ExceptionsBD, SQLException;
}
