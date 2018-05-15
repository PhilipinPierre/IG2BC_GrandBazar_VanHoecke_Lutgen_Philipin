package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Recette;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecetteBDA implements RecetteDA {
    public ArrayList<Recette> getAllRecette()throws ExceptionsBD {
        ArrayList<Recette> liste = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from recette";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                Recette recette = new Recette();
                CompleterRecette(donnees,recette);
                liste.add(recette);
            }
            System.out.println(donnees.getString("nom"));
        } catch (Exception e){
            throw new ExceptionsBD("recherche de toute les recettes");
        }


        return liste;
    }

    protected static Recette getRecette(String nom) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from recette where nom = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            Recette recette = new Recette();
            CompleterRecette(donnees, recette);
            return recette;
        }catch (Exception e){
            throw  new ExceptionsBD("recherche de la recette " + nom);
        }
    }

    private static void CompleterRecette(ResultSet donnees, Recette recette) throws SQLException{
        recette.setNom(donnees.getString("nom"));
        recette.setDescriptif(donnees.getString("descriptif"));
        Integer DLC = donnees.getInt("dlc");
        recette.setDLC(DLC);

        System.out.println(recette.getNom() + recette.getDLC() + recette.getDescriptif());
    }

}
