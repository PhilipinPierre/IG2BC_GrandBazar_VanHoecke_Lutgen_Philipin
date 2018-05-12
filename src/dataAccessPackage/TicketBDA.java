package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import modelPackage.MembreDuPersonnel;
import modelPackage.Ticket;
import java.sql.*;
import java.util.*;

public class TicketBDA {
    public ArrayList<Ticket> getAllTicket() throws ExceptionsBD {
        try
        {
            ArrayList<Ticket> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from Ticket";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()){
                Ticket ticket = new Ticket();
                completerTicket(donnees, ticket);
                liste.add(ticket);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Probleme BD Ticket");
        }
    }

    private void completerTicket(ResultSet donnees, Ticket ticket) throws SQLException{
        ticket.setNumTicket(donnees.getInt("numticket"));
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(donnees.getDate("date"));
        ticket.setDate(date);
        ticket.setHeure(donnees.getInt("heure"));
        MembreDuPersonnel mb = new MembreDuPersonnel();
        ticket.setMatricule(mb);
        Client c = new Client();
        ticket.setNumClient(c);
    }
}
