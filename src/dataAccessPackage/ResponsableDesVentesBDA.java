package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ResponsableDesVentes;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResponsableDesVentesBDA extends MembreDuPersonnelBDA implements ResponsableDesVentesDA {
    public ArrayList<ResponsableDesVentes> getAllResponsableDesVentes() throws ExceptionsBD{
        ArrayList<ResponsableDesVentes> liste = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from membredupersonnel m, responsablevente r where m.matricule = r.matricule_res";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                ResponsableDesVentes responsableDesVentes = new ResponsableDesVentes();
                responsableDesVentes.setNom(donnees.getString("nom"));
                responsableDesVentes.setMatricule(donnees.getInt("matricule_res"));
                liste.add(responsableDesVentes);
            }
        } catch (Exception e){
            throw new ExceptionsBD("recherche de tout les responsables des ventes");
        }
        return liste;
    }
}
