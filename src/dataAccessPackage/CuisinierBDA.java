package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Cuisinier;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CuisinierBDA extends MembreDuPersonnelBDA implements CuisinierDA {
    public ArrayList<Cuisinier> getAllCuisinier() throws ExceptionsBD{
        try {
            ArrayList<Cuisinier> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from membredupersonnel m, cuisinier c where m.matricule = c.matricule_cui";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                Cuisinier cuisinier = new Cuisinier();
                super.CompleterMDPAutre(donnees, cuisinier);
                liste.add(cuisinier);
            }
            return liste;
        } catch (Exception e){
            throw new ExceptionsBD("recherche de tout les cuisinier");
        }
    }

}
