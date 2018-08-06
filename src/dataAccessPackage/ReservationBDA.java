package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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

                GregorianCalendar date = new GregorianCalendar();
                date.setTime(donnees.getDate("date"));
                reservation.setDate(date);

                reservation.setNumeroSequentiel(donnees.getInt("numeroSequentiel"));

                int codeBarre = donnees.getInt("codeBarre");
                if(!donnees.wasNull())
                {
                    TypeArticle ta = TypeArticleBDA.completerTypeArticle(donnees);
                    reservation.setCodeBarre(ta);
                }

                reservation.setQuantiteReservee(donnees.getInt("quantiteReserve"));

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

    public void modifierReservation(Reservation reservation) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert reservation values(?,?,?,?) ";

            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setDate(1, new java.sql.Date(reservation.getDate().getTimeInMillis()));
            preparedStatement.setInt(2, reservation.getNumeroSequentiel());
            preparedStatement.setInt(3, reservation.getCodeBarre().getCodeBarre());
            preparedStatement.setInt(4, reservation.getQuantiteReservee());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de modification d'un ordre de préparation");
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
