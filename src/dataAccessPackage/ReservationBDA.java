package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ReservationBDA implements ReservationDA {
    public ArrayList<Reservation> getAllReservation() throws ExceptionsBD, SQLException
    {
        ArrayList<Reservation> liste = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from reservation order by NumeroSequentiel";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();
        while(donnees.next())
        {
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

    public void supprimerReservation(Integer numeroSequentiel) throws ExceptionsBD, SQLException
    {
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "delete from reservation where numerosequentiel = " + numeroSequentiel;
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        preparedStatement.executeUpdate();
    }

    public void modifierReservation(Reservation reservation) throws ExceptionsBD, SQLException
    {
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "insert reservation values(?,?,?,?) ";

        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        preparedStatement.setDate(1, new java.sql.Date(reservation.getDate().getTimeInMillis()));
        preparedStatement.setInt(2, reservation.getNumeroSequentiel());
        preparedStatement.setInt(3, reservation.getCodeBarre().getCodeBarre());
        preparedStatement.setInt(4, reservation.getQuantiteReservee());

        preparedStatement.executeUpdate();
    }

    private void completerReservation(ResultSet donnees, Reservation reservation) throws SQLException
    {
        OrdrePreparation ordrePreparation = new OrdrePreparation();
        reservation.setOrdrePreparation(ordrePreparation);

        reservation.setQuantiteReservee(donnees.getInt("quantiteReserve"));

        TypeArticle t = new TypeArticle();
        reservation.setCodeBarre(t);
    }
}
