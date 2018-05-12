package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ResponsableDesVentes;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ResponsableDesVentesBDA implements ResponsableDesVentesDA {
    public ArrayList<ResponsableDesVentes> getAllResponsableDesVentes() throws ExceptionsBD {
        try
        {
            ArrayList<ResponsableDesVentes> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from membredupersonnel m, responsabledesventes r where m.matricule = r.matricule_res";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()){
                ResponsableDesVentes responsableDesVentes = new ResponsableDesVentes();
                completerResponsableVente(donnees, responsableDesVentes);
                liste.add(responsableDesVentes);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Problème BD Responsable vente");
        }
    }

    private void completerResponsableVente(ResultSet donnees, ResponsableDesVentes rdv) throws SQLException {
        rdv.setNom(donnees.getString("nom"));
        rdv.setPrenom(donnees.getString("prenom"));
        GregorianCalendar dateNaissance = new GregorianCalendar();
        dateNaissance.setTime(donnees.getDate("datenaissance"));
        rdv.setDateNaissance(dateNaissance);
        rdv.setCodePostal(donnees.getInt("codepostal"));
        rdv.setLocalite(donnees.getString("localite"));
        rdv.setRue(donnees.getString("rue"));
        GregorianCalendar dateEntree = new GregorianCalendar();
        rdv.setDateNaissance(dateEntree);
        GregorianCalendar dateSortie = new GregorianCalendar();
        rdv.setDateNaissance(dateSortie);
    }
}
