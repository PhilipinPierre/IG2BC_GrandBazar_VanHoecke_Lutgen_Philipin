package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Lot;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LotDA {
    ArrayList<Lot> getAllLot() throws ExceptionsBD, SQLException, NamingException;
}
