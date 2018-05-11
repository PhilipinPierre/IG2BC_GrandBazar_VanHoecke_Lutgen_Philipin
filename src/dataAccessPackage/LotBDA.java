package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Lot;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class LotBDA implements LotDA {
    public ArrayList<Lot> getAllLot() throws SQLException, NamingException{
        ArrayList<Lot> liste = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from lot";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();
        while(donnees.next()){
            Lot lot = new Lot();
            CompleterLot(donnees, lot);
            liste.add(lot);
        }
        return liste;
    }

    private void CompleterLot(ResultSet donnees, Lot lot) throws SQLException{
        lot.setId(donnees.getString("id"));
        if(donnees.getDate("dateperemption")!=null){
            GregorianCalendar date = new GregorianCalendar();
            date.setTime(donnees.getDate("dateperemption"));
            lot.setDatePeremption(date);
        }
        lot.setQuantite(donnees.getInt("quantite"));
        Integer codeLot = new Integer(donnees.getInt("codeLot"));
        if(codeLot != null)
            lot.setCodeLot(codeLot);
        if(donnees.getDate("datefournitureprevue")!=null){
            GregorianCalendar date = new GregorianCalendar();
            date.setTime(donnees.getDate("datefournitureprevue"));
            lot.setDateFourniturePrevue(date);
        }
        GregorianCalendar dateCommande = new GregorianCalendar();
        dateCommande.setTime(donnees.getDate("datecommande"));
        lot.setDateCommande(dateCommande);

        //A COMPLETER

    }
}
