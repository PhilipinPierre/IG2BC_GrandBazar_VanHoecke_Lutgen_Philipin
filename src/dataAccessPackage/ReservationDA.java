package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Reservation;
import java.util.ArrayList;

public interface ReservationDA {
    ArrayList<Reservation> getAllReservation() throws ExceptionsBD;
}
