package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Reservation;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDA {
    ArrayList<Reservation> getAllReservation() throws ExceptionsBD, SQLException;
    void supprimerReservation(Integer numeroSequentiel) throws ExceptionsBD, SQLException;
    void modifierReservation(Reservation reservation) throws ExceptionsBD, SQLException;
}
