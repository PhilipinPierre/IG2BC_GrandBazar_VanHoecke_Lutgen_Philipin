package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;
import java.sql.*;
import java.util.*;

public class MembreDuPersonnelBDA implements MembreDuPersonnelDA{
    public ArrayList<MembreDuPersonnel> getAllMembreDuPersonnel() throws ExceptionsBD {
        try
        {
            ArrayList<MembreDuPersonnel> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from MembreDuPersonnel";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                MembreDuPersonnel membreDuPersonnel = new MembreDuPersonnel();
                CompleterMDP(donnees, membreDuPersonnel);
                if(membreDuPersonnel.getDateSortie() == null)
                    liste.add(membreDuPersonnel);
            }
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Problème BD MembrePerso");
        }
    }

    /*protected void CompleterMDPAutre(ResultSet donnees, MembreDuPersonnel membreDuPersonnel) throws SQLException{
        if(this.getClass().getSimpleName().equals("CuisinierBDA") || this.getClass().getSimpleName().equals("ResponableDesVentesBDA"));
            CompleterMDP(donnees, membreDuPersonnel);
    }*/

    private void CompleterMDP(ResultSet donnees, MembreDuPersonnel membreDuPersonnel) throws SQLException{
        membreDuPersonnel.setMatricule(donnees.getInt("matricule"));
        membreDuPersonnel.setNom(donnees.getString("nom"));
        membreDuPersonnel.setPrenom(donnees.getString("prenom"));
        membreDuPersonnel.setCodePostal(donnees.getInt("codepostal"));
        membreDuPersonnel.setLocalite(donnees.getString("localite"));

        GregorianCalendar dateNaiss = new GregorianCalendar();
        dateNaiss.setTime(donnees.getDate("dateNaissance"));
        membreDuPersonnel.setDateNaissance(dateNaiss);

        GregorianCalendar dateEntree = new GregorianCalendar();
        dateEntree.setTime(donnees.getDate("dateEntree"));
        membreDuPersonnel.setDateEntree(dateEntree);

        GregorianCalendar dateSortie = new GregorianCalendar();
        dateSortie.setTime(donnees.getDate("dateSortie"));
        membreDuPersonnel.setDateNaissance(dateSortie);
    }

}
