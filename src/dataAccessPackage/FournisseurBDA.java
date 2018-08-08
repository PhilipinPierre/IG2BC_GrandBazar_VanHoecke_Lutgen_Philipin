package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FournisseurBDA implements FournisseurDA {
    public ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD{
        try {
            ArrayList<Fournisseur> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from fournisseur";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnes = preparedStatement.executeQuery();
            while (donnes.next()) {
                Fournisseur fournisseur = new Fournisseur();
                completerFournisseur(donnes, fournisseur);
                liste.add(fournisseur);
            }

            return liste;
        } catch (Exception e){
            throw  new ExceptionsBD("recherche de tout les fournisseurs");
        }
    }

    public void ajouterFournisseur(ApplicationController applicationController, Fournisseur fournisseur) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into fournisseur values (?,?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);

            preparedStatement.setInt(1, fournisseur.getNumeroTVA());
            preparedStatement.setString(2, fournisseur.getNom());
            preparedStatement.setString(3, fournisseur.getLocalite());
            preparedStatement.setInt(4, fournisseur.getCodePostal());
            preparedStatement.setString(5, fournisseur.getRue());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de l'ajout d'un type d'article à la base de données");
        }
    }

    protected static void completerFournisseur(ResultSet donnees, Fournisseur fournisseur)throws SQLException{
        Integer numeroTva = new Integer(donnees.getInt("numerotva"));
        fournisseur.setNumeroTVA(numeroTva);
        fournisseur.setNom(donnees.getString("nom"));
        fournisseur.setLocalite(donnees.getString("localite"));
        Integer codePostal = new Integer(donnees.getInt("codepostal"));
        fournisseur.setCodePostal(codePostal);
        fournisseur.setRue(donnees.getString("rue"));
    }
}
