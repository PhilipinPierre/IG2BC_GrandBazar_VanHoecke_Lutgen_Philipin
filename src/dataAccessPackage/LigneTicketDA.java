package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.LigneTicket;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LigneTicketDA {
    ArrayList<LigneTicket> getAllLigneTicket() throws ExceptionsBD;
}
