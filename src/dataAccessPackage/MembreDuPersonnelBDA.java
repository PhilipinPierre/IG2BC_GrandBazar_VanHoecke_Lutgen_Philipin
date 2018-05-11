package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembreDuPersonnelBDA implements MembreDuPersonnelDA{
    public ArrayList<MembreDuPersonnel> getAllMdp() throws ExceptionsBD, SQLException, NamingException{
        ArrayList<MembreDuPersonnel> liste = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from MembreDuPersonnel";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
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