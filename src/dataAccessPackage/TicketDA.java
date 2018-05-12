package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Ticket;
import java.util.ArrayList;

public interface TicketDA {
    public ArrayList<Ticket> getAllTicket() throws ExceptionsBD;
}
