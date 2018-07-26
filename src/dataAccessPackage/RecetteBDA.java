package dataAccessPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;

import javax.swing.*;
import java.sql.SQLException;

public class RecetteBDA implements RecetteDA {
    ArrayList<Recette> listeRecette;

    private RecetteBDA() throws ExceptionsBD{
        listeRecette = new ArrayList<>();
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select nom from recette order by 'nom'";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                Recette recette = new Recette();
                recette.setNom(donnees.getString("Nom"));
                listeRecette.add(recette);
            }
        } catch (Exception e) {
            throw new ExceptionsBD("recherche de toute les recettes");
        }
    }

    public void AjouterRecette(Recette recette) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into recette values('"+recette.getNom()+"',"+recette.getDLC()+",'"+recette.getDescriptif()+"');";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        } catch (Exception e){
            throw  new ExceptionsBD("ajout d'une recette à la base de donnée");
        }
    }


    public Recette getRecette(String nom) throws ExceptionsBD{
        try {
            for (Recette recette : listeRecette) {
                if (recette.getNom().equals(nom))
                    return recette;
            }
        } catch (Exception e){
            throw new ExceptionsBD("recherche d'une recette introuvable.");
        }
        return null;
    }
    public ArrayList<Recette> getAllRecette() throws ExceptionsBD {
        return listeRecette;
    }

    /*protected static Recette getRecette(String nom) throws ExceptionsBD {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from recette where nom = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            Recette recette = new Recette();
            CompleterRecette(donnees, recette);
            return recette;
        } catch (Exception e) {
            throw new ExceptionsBD("recherche de la recette " + nom);
        }
    }*/

    protected static Recette CompleterRecette(ResultSet donnees, Recette recette) throws SQLException {
        recette.setNom(donnees.getString("nom"));
        recette.setDescriptif(donnees.getString("descriptif"));
        Integer DLC = donnees.getInt("dlc");
        recette.setDLC(DLC);
        return recette;
    }
}
