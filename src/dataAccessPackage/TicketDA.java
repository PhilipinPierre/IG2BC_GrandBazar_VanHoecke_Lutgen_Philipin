package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Ticket;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TicketDA {
    ArrayList<Ticket> getAllTicket() throws ExceptionsBD, SQLException;
}
