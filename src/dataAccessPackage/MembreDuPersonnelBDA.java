package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembreDuPersonnelBDA /*implements MembreDuPersonnelDA*/{
    /*ArrayList<MembreDuPersonnel> getAllCuisiniers() throws ExceptionsBD;
    ArrayList<MembreDuPersonnel> getAllResponsableDesVentes() throws  ExceptionsBD;
    ArrayList<MembreDuPersonnel> getAllEmployees() throws ExceptionsBD;
    ArrayList<MembreDuPersonnel> getAllMDP() throws ExceptionsBD;
    MembreDuPersonnel getUser(int matricule, String mdp) throws ExceptionsBD;*/

    private ArrayList<MembreDuPersonnel> getAllMdpParType(String typeEmploye) throws Exception{
        ArrayList<MembreDuPersonnel> liste = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from MembreDuPersonnel where categorie = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        preparedStatement.setString(1,typeEmploye);
        ResultSet donnees = preparedStatement.executeQuery();
        while(donnees.next()){
            MembreDuPersonnel membreDuPersonnel = new MembreDuPersonnel();
            completerMDP(donnees, membreDuPersonnel);
            if(membreDuPersonnel.getDateSortie() == null)
                liste.add(membreDuPersonnel);
        }
        return liste;
    }

    private void completerMDP(ResultSet donnees, MembreDuPersonnel membreDuPersonnel) throws SQLException{
        membreDuPersonnel.setMatricule(donnees.getInt("matricule"));
        membreDuPersonnel.setNom(donnees.getString("nom"));
        membreDuPersonnel.setPrenom(donnees.getString("prenom"));
        membreDuPersonnel.setCodePostal(donnees.getInt("codepostal"));
        membreDuPersonnel.setLocalite(donnees.getString("localite"));

    }
}
