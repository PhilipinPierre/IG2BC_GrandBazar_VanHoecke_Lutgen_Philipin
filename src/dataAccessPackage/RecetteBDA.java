package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Recette;
import java.sql.*;
import java.util.ArrayList;

public class RecetteBDA implements RecetteDA {
    public ArrayList<Recette> getAllRecette()throws ExceptionsBD {
        try
        {
            ArrayList<Recette> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from Recette";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()){
                Recette recette = new Recette();
                completerRecette(donnees, recette);
                liste.add(recette);
            }
            return liste;
        }
        catch (Exception e){
            throw new ExceptionsBD("Problème BD recette");
        }
    }

    private void completerRecette(ResultSet donnees, Recette recette) throws SQLException{
        recette.setNom(donnees.getString("nom"));
        recette.setDescriptif(donnees.getString("descriptif"));
        Integer DLC = new Integer(donnees.getInt("dlc"));
        recette.setDLC(DLC);
    }

}
