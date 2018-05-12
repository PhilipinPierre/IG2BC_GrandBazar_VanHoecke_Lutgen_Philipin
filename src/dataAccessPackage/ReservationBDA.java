package dataAccessPackage;

import modelPackage.Reservation;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBDA implements ReservationDA {
    public ArrayList<Reservation> getAllReservation() throws SQLException, NamingException{
        ArrayList<Reservation> liste = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from reservation";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();
        while(donnees.next()){
            Reservation reservation = new Reservation();
            CompleterReservation(donnees, reservation);
            liste.add(reservation);
        }
        return liste;
    }

    private void CompleterReservation(ResultSet donnees, Reservation reservation) throws SQLException{
        Integer qunatiteReserve = new Integer(donnees.getInt("quantitereserve"));
        reservation.setQuantiteReservee(qunatiteReserve);
    }
}
