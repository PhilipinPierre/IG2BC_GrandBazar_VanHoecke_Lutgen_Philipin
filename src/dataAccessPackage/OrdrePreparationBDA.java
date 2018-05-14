package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrdrePreparationBDA implements OrdrePreparationDA {
    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD{
        try {
            ArrayList<OrdrePreparation> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from ordrepreparation";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                OrdrePreparation ordrePreparation = new OrdrePreparation();
                completerOrdrePreparation(donnees, ordrePreparation);
                liste.add(ordrePreparation);
            }
            return liste;
        } catch (Exception e){
            throw  new ExceptionsBD("recherche de tout les ordres de préparations");
        }
    }

    private void completerOrdrePreparation(ResultSet donnees, OrdrePreparation ordrePreparation)throws SQLException{
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(donnees.getDate("date"));
        ordrePreparation.setDate(date);
        Integer numeroSequentiel = new Integer(donnees.getInt("numerosequential"));
        ordrePreparation.setNumeroSequentiel(numeroSequentiel);
        Integer quantiteprevue = donnees.getInt("quantiteprevue");
        ordrePreparation.setQuantitePrevue(quantiteprevue);
        Integer quantitéProduite = donnees.getInt("quantiteproduite");
        if(!donnees.wasNull())
            ordrePreparation.setQuantitePrevue(donnees.getInt("quantiteproduite"));
        java.sql.Date dateTest = donnees.getDate("datevente");
        if(!donnees.wasNull()){
            GregorianCalendar dateVente = new GregorianCalendar();
            dateVente.setTime(dateTest);
            ordrePreparation.setDateVente(dateVente);
        }
        dateTest = donnees.getDate("datepreparation");
        if(!donnees.wasNull()){
            GregorianCalendar datePreparation = new GregorianCalendar();
            datePreparation.setTime(dateTest);
            ordrePreparation.setDatePreparation(datePreparation);
        }
        String remarque = donnees.getString("remarque");
        if(!donnees.wasNull())
            ordrePreparation.setRemarque(remarque);
        ordrePreparation.setEstUrgent(donnees.getBoolean("esturgent"));

        //A COMPLETER

    }
}
