package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class MembreDuPersonnelBDA implements MembreDuPersonnelDA{
    public ArrayList<MembreDuPersonnel> getAllMembreDuPersonnel() throws ExceptionsBD, SQLException
    {
        ArrayList<MembreDuPersonnel> liste = new ArrayList<>();

        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from MembreDuPersonnel";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();
        while (donnees.next()) {
            MembreDuPersonnel membreDuPersonnel = new MembreDuPersonnel();
            completerMDP(donnees, membreDuPersonnel);
            liste.add(membreDuPersonnel);
        }

        return liste;
    }

    protected void completerMDPAutre(ResultSet donnees, MembreDuPersonnel membreDuPersonnel) throws SQLException
    {
        if(this.getClass().getSimpleName().equals("CuisinierBDA") || this.getClass().getSimpleName().equals("ResponableDesVentesBDA"))
            completerMDP(donnees, membreDuPersonnel);
    }

    protected static void completerMDP(ResultSet donnees, MembreDuPersonnel membreDuPersonnel) throws SQLException
    {
        membreDuPersonnel.setMatricule(donnees.getInt("matricule"));
        membreDuPersonnel.setNom(donnees.getString("nom"));
        membreDuPersonnel.setPrenom(donnees.getString("prenom"));
        GregorianCalendar dateNaissance = new GregorianCalendar();
        dateNaissance.setTime(donnees.getDate("datenaissance"));
        membreDuPersonnel.setDateNaissance(dateNaissance);
        membreDuPersonnel.setCodePostal(donnees.getInt("codepostal"));
        membreDuPersonnel.setLocalite(donnees.getString("localite"));
        membreDuPersonnel.setRue(donnees.getString("rue"));
        GregorianCalendar dateEntree = new GregorianCalendar();
        dateEntree.setTime(donnees.getDate("dateentree"));
        membreDuPersonnel.setDateEntree(dateEntree);
        if(donnees.getDate("datesortie") != null) {
            GregorianCalendar dateSortie = new GregorianCalendar();
            dateSortie.setTime(donnees.getDate("datesortie"));
            membreDuPersonnel.setDateSortie(dateSortie);
        }
    }

    protected static MembreDuPersonnel getMembreDuPersonnel(int matricule)throws ExceptionsBD, SQLException
    {
        MembreDuPersonnel membreDuPersonnel = new MembreDuPersonnel();

        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from membredupersonnel where matricule = " + matricule;
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();
        System.out.println(donnees.getString("nom"));
        completerMDP(donnees, membreDuPersonnel);
        System.out.println(membreDuPersonnel.getMatricule());

        return membreDuPersonnel;
    }


    public ArrayList<MembreDuPersonnel> getAllCuisiniers() throws Exception
    {
        return getAllEmployesParType(MembreDuPersonnel.CUISINIER);
    }

    public ArrayList<MembreDuPersonnel> getAllRespDesVentes() throws Exception
    {

        return getAllEmployesParType(MembreDuPersonnel.RESP_DES_VENTES);
    }

    private ArrayList<MembreDuPersonnel> getAllEmployesParType(String typeEmploye) throws Exception, SQLException
    {
        ArrayList<MembreDuPersonnel> listeEmploye = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requete = "select * from MembreDuPersonnel where typePersonnel = ?";
        PreparedStatement prepStat = connection.prepareStatement(requete);
        prepStat.setString(1, typeEmploye);
        ResultSet donnees = prepStat.executeQuery();
        while(donnees.next())
        {
            MembreDuPersonnel mdp = new MembreDuPersonnel();
            completerMDP(donnees, mdp);
            if(mdp.getDateSortie() == null)
                listeEmploye.add(mdp);
        }
        return listeEmploye;
    }
}


