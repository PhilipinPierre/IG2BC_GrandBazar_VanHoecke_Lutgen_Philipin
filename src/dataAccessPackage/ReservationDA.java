package dataAccessPackage;

import modelPackage.Reservation;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDA {
    ArrayList<Reservation> getAllReservation() throws SQLException, NamingException;
}
