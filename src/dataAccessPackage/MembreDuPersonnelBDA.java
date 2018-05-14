package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Cuisinier;
import modelPackage.MembreDuPersonnel;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembreDuPersonnelBDA implements MembreDuPersonnelDA{
    public ArrayList<MembreDuPersonnel> getAllMembreDuPersonnel() throws ExceptionsBD{
        try {
            ArrayList<MembreDuPersonnel> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from MembreDuPersonnel";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                MembreDuPersonnel membreDuPersonnel = new MembreDuPersonnel();
                CompleterMDP(donnees, membreDuPersonnel);
                if (membreDuPersonnel.getDateSortie() == null)
                    liste.add(membreDuPersonnel);
            }
            return liste;
        } catch (Exception e){
            throw  new ExceptionsBD("recherche de tout les membres du personnel");
        }
    }

    protected void CompleterMDPAutre(ResultSet donnees, MembreDuPersonnel membreDuPersonnel) throws SQLException{
        if(this.getClass().getSimpleName().equals("CuisinierBDA") || this.getClass().getSimpleName().equals("ResponableDesVentesBDA"))
            CompleterMDP(donnees, membreDuPersonnel);
    }

    private static void CompleterMDP(ResultSet donnees, MembreDuPersonnel membreDuPersonnel) throws SQLException{
        membreDuPersonnel.setMatricule(donnees.getInt("matricule"));
        membreDuPersonnel.setNom(donnees.getString("nom"));
        membreDuPersonnel.setPrenom(donnees.getString("prenom"));
        membreDuPersonnel.setCodePostal(donnees.getInt("codepostal"));
        membreDuPersonnel.setLocalite(donnees.getString("localite"));

    }

    protected static MembreDuPersonnel getMembreDuPersonnel(int matricule)throws ExceptionsBD{
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from memebredupersonnel where matricule = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            MembreDuPersonnel membreDuPersonnel = new MembreDuPersonnel();
            CompleterMDP(donnees, membreDuPersonnel);
            return membreDuPersonnel;
        } catch (Exception e){
            throw  new ExceptionsBD("recherche d'un memebre du personnel");
        }
    }
}
