package dataAccessPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.SQLException;

public class RecetteBDA implements RecetteDA {

    public ArrayList<Recette> getAllRecette() throws ExceptionsBD {
        ArrayList<Recette> listeRecette = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select nom from recette order by 'nom'";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                Recette recette = new Recette();
                recette.setNom(donnees.getString("Nom"));
                //recette.setDescriptif(donnees.getString("Descriptif"));
                //recette.setDLC(donnees.getInt("DLC"));
                listeRecette.add(recette);
            }
        } catch (Exception e) {
            throw new ExceptionsBD("Erreur lors de la recherche de toutes les recettes");
        }
        return listeRecette;
    }

    public void AjouterRecette(Recette recette) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into recette values('"+recette.getNom()+"',"+recette.getDLC()+",'"+recette.getDescriptif()+"');";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de l'ajout d'une recette à la base de données");
        }
    }


    public Recette getRecette(String nom) throws ExceptionsBD{
        try {
            for (Recette recette : getAllRecette()) {
                if (recette.getNom().equals(nom))
                    return recette;
            }
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la recherche d'une recette introuvable.");
        }
        return null;
    }

    protected static Recette CompleterRecette(ResultSet donnees) throws SQLException {
        Recette recette = new Recette();
        recette.setNom(donnees.getString("nom"));
        //recette.setDescriptif(donnees.getString("descriptif"));
        //recette.setDLC(donnees.getInt("dlc"));
        return recette;
    }
}