package businessPackage;

import dataAccessPackage.LigneTicketBDA;
import dataAccessPackage.LigneTicketDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.LigneTicket;
import java.sql.SQLException;
import java.util.ArrayList;

public class LigneTicketManager {

    private LigneTicketDA ligneTicketBDA;

    public LigneTicketManager() {ligneTicketBDA = new LigneTicketBDA();}

    public ArrayList<LigneTicket> getAllLigneTicket() throws ExceptionsBD, SQLException
    {
        ArrayList<LigneTicket> ligneTicketList = ligneTicketBDA.getAllLigneTicket();
        return ligneTicketList;
    }
}
