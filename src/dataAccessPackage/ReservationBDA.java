package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import modelPackage.Reservation;
import modelPackage.TypeArticle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBDA implements ReservationDA {
    public ArrayList<Reservation> getAllReservation() throws ExceptionsBD {
        try
        {
            ArrayList<Reservation> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from reservation order by NumeroSequentiel";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                Reservation reservation = new Reservation();

                reservation.setNumeroSequentiel(donnees.getInt("numeroSequentiel"));

                liste.add(reservation);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Probleme recette !");
        }
    }

    public void supprimerReservation(Integer numeroSequentiel) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "delete from reservation where numerosequentiel = " + numeroSequentiel;
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new ExceptionsBD("Impossible de supprimer cet réservation : " + numeroSequentiel);
        }
    }

    private void completerReservation(ResultSet donnees, Reservation reservation) throws SQLException{

        OrdrePreparation ordrePreparation = new OrdrePreparation();
        reservation.setOrdrePreparation(ordrePreparation);

        reservation.setQuantiteReservee(donnees.getInt("quantiteReserve"));

        TypeArticle t = new TypeArticle();
        reservation.setCodeBarre(t);
    }
}
