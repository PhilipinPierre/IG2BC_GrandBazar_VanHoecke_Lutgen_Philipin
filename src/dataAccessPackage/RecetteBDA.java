package dataAccessPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.SQLException;

public class RecetteBDA implements RecetteDA {

    public void AjouterRecette(Recette recette) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into recette values('"+recette.getNom()+"',"+recette.getDLC()+",'"+recette.getDescriptif()+"');";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de l'ajout d'une recette à la base de données");
        }
    }

    protected static Recette CompleterRecette(ResultSet donnees) throws SQLException {
        Recette recette = new Recette();
        recette.setNom(donnees.getString("nom"));
        //recette.setDescriptif(donnees.getString("descriptif"));
        //recette.setDLC(donnees.getInt("dlc"));
        return recette;
    }
}