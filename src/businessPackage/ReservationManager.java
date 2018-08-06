package businessPackage;

import dataAccessPackage.ReservationBDA;
import dataAccessPackage.ReservationDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Reservation;
import java.util.ArrayList;

public class ReservationManager {
    private ReservationDA reservationDA;

    public ReservationManager() {reservationDA = new ReservationBDA();}

    public ArrayList<Reservation> getAllReservation() throws ExceptionsBD
    {
        return reservationDA.getAllReservation();
    }

    public void supprimerReservation(Integer numeroSequentiel) throws ExceptionsBD
    {
        reservationDA.supprimerReservation(numeroSequentiel);
    }

    public void modifierReservation(Reservation reservation) throws ExceptionsBD
    {
        reservationDA.modifierReservation(reservation);
    }
}
