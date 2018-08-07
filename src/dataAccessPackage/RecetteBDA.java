package dataAccessPackage;

import java.sql.*;
import java.util.*;
import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;

public class RecetteBDA implements RecetteDA {

    public ArrayList<Recette> getAllRecette() throws ExceptionsBD, SQLException
    {
        ArrayList<Recette> listeRecette = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "select * from recette order by nom ";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        ResultSet donnees = preparedStatement.executeQuery();
        while (donnees.next())
        {
            Recette recette = new Recette();
            recette.setNom(donnees.getString("nom"));
            recette.setDLC(donnees.getInt("dlc"));
            recette.setDescriptif(donnees.getString("descriptif"));
            listeRecette.add(recette);
        }
        return listeRecette;
    }

    public void ajouterRecette(ApplicationController applicationController, Recette recette) throws ExceptionsBD, SQLException
    {
        Connection connection = SingletonConnexion.getInstance();
        String requeteSQL = "insert into recette values (?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);

        preparedStatement.setString(1, recette.getNom());
        preparedStatement.setInt(2, recette.getDLC());
        preparedStatement.setString(3, recette.getDescriptif());

        preparedStatement.executeUpdate();
    }

    public Recette getRecette(String nom) throws ExceptionsBD, SQLException
    {
        for (Recette recette : getAllRecette())
        {
            if (recette.getNom().equals(nom))
                return recette;
        }
        return null;
    }

    protected static Recette completerRecette(ResultSet donnees) throws SQLException
    {
        Recette recette = new Recette();
        recette.setNom(donnees.getString("nom"));

        return recette;
    }
}