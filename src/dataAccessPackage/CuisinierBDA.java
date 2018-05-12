package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Cuisinier;
import java.sql.*;
import java.util.*;

public class CuisinierBDA implements CuisinierDA {
    public ArrayList<Cuisinier> getAllCuisinier() throws ExceptionsBD {
        try
        {
            ArrayList<Cuisinier> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from membredupersonnel m, cuisinier c where m.matricule = c.matricule_cui";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                Cuisinier cuisinier = new Cuisinier();
                completerCuisinier(donnees, cuisinier);
                liste.add(cuisinier);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Problème BD Cuisinier");
        }
    }


    private void completerCuisinier(ResultSet donnees, Cuisinier cuisinier) throws SQLException {
        cuisinier.setNom(donnees.getString("nom"));
        cuisinier.setPrenom(donnees.getString("prenom"));
        GregorianCalendar dateNaissance = new GregorianCalendar();
        dateNaissance.setTime(donnees.getDate("datenaissance"));
        cuisinier.setDateNaissance(dateNaissance);
        cuisinier.setCodePostal(donnees.getInt("codepostal"));
        cuisinier.setLocalite(donnees.getString("localite"));
        cuisinier.setRue(donnees.getString("rue"));
        GregorianCalendar dateEntree = new GregorianCalendar();
        cuisinier.setDateNaissance(dateEntree);
        GregorianCalendar dateSortie = new GregorianCalendar();
        cuisinier.setDateNaissance(dateSortie);
    }

}
