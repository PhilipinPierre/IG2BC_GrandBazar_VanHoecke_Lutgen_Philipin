package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import modelPackage.Ticket;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TicketBDA {
    ArrayList<Ticket> getAllTicket()throws ExceptionsBD, SQLException, NamingException{
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

    private void completerTicket(ResultSet donnees, Ticket ticket)throws SQLException{
        ticket.setNumTicket(donnees.getInt("numticket"));
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(donnees.getDate("date"));
        ticket.setDate(date);
        ticket.setHeure(donnees.getInt("heure"));
    }
}
