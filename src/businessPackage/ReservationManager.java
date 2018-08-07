package businessPackage;

import dataAccessPackage.ReservationBDA;
import dataAccessPackage.ReservationDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Reservation;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationManager {
    private ReservationDA reservationDA;

    public ReservationManager() {reservationDA = new ReservationBDA();}

    public ArrayList<Reservation> getAllReservation() throws ExceptionsBD, SQLException
    {
        return reservationDA.getAllReservation();
    }

    public void supprimerReservation(Integer numeroSequentiel) throws ExceptionsBD, SQLException
    {
        reservationDA.supprimerReservation(numeroSequentiel);
    }

    public void modifierReservation(Reservation reservation) throws ExceptionsBD, SQLException
    {
        reservationDA.modifierReservation(reservation);
    }
}
