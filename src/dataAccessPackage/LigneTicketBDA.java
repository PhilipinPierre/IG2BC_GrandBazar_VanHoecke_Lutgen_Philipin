package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.*;
import java.util.ArrayList;

public class LigneTicketBDA implements LigneTicketDA {
    public ArrayList<LigneTicket> getAllLigneTicket() throws ExceptionsBD {
        try
        {
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
        catch (Exception e){
            throw new ExceptionsBD("Problème dans le ligne ticket BD");
        }
    }

    private void completerLigneTicket(ResultSet donnees, LigneTicket ligneTicket) throws SQLException {
        ligneTicket.setPrixReel(donnees.getDouble("prix Reel"));
        ligneTicket.setQuantite(donnees.getInt("quantité"));
        Ticket tk = new Ticket();
        ligneTicket.setTicket(tk);
        TypeArticle t = new TypeArticle();
        ligneTicket.setTypeArticle(t);
    }
}