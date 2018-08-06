package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Reservation;
import java.util.ArrayList;

public interface ReservationDA {
    ArrayList<Reservation> getAllReservation() throws ExceptionsBD;
    void supprimerReservation(Integer numeroSequentiel) throws ExceptionsBD;
}
