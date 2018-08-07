package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Cuisinier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CuisinierBDA extends MembreDuPersonnelBDA implements CuisinierDA {
    public ArrayList<Cuisinier> getAllCuisinier() throws ExceptionsBD{
        ArrayList<Cuisinier> liste = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from membredupersonnel m, cuisinier c where m.matricule = c.matricule_cui";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                Cuisinier cuisinier = new Cuisinier();
                cuisinier.setNom(donnees.getString("nom"));
                cuisinier.setMatricule(donnees.getInt("matricule_cui"));
                liste.add(cuisinier);
            }

        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la recherche de tout les cuisiniers");
        }
        return liste;
    }

    protected static Cuisinier completerCuisinier(ResultSet donnees) throws SQLException {
        Cuisinier cuisinier = new Cuisinier();
        cuisinier.setMatricule(donnees.getInt("matricule_cui"));
        return cuisinier;
    }

}
