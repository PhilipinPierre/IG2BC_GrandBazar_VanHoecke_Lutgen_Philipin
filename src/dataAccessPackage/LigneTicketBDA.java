package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.LigneTicket;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LigneTicketBDA implements LigneTicketDA {
    public ArrayList<LigneTicket> getAllLigneTicket() throws ExceptionsBD, SQLException, NamingException{
        ArrayList<LigneTicket> liste = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from ligneticket";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();

        while(donnees.next()){
            LigneTicket ligneTicket = new LigneTicket();
            completerLigneTicket(donnees, ligneTicket);
            liste.add(ligneTicket);
        }
        return liste;
    }

    private void completerLigneTicket(ResultSet donnees, LigneTicket ligneTicket){
        ;
    }
}