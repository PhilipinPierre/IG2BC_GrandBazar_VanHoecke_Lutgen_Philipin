package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import modelPackage.Reservation;
import modelPackage.TypeArticle;
import java.sql.*;
import java.util.ArrayList;

public class ReservationBDA implements ReservationDA {
    public ArrayList<Reservation> getAllReservation() throws ExceptionsBD {
        try
        {
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
        catch (Exception e){
            throw new ExceptionsBD("Problème Reservation BD");
        }
    }

    private void CompleterReservation(ResultSet donnees, Reservation reservation) throws SQLException{
        Integer quantiteReserve = new Integer(donnees.getInt("quantitereserve"));
        reservation.setQuantiteReservee(quantiteReserve);
        TypeArticle t = new TypeArticle();
        reservation.setCodeBarre(t);
        OrdrePreparation op = new OrdrePreparation();
        reservation.setOrdrePreparation(op);
    }
}
