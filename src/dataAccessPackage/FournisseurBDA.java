package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;
import java.sql.*;
import java.util.ArrayList;

public class FournisseurBDA implements FournisseurDA
{
    public ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD {
        try
        {
            ArrayList<Fournisseur> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from fournisseur";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()){
                Fournisseur fournisseur = new Fournisseur();
                CompleterFournisseur(donnees, fournisseur);
                liste.add(fournisseur);
            }

            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Problème BD Client");
        }
    }

    private void CompleterFournisseur(ResultSet donnees, Fournisseur fournisseur)throws SQLException {
        Integer numeroTva = new Integer(donnees.getInt("numerotva"));
        fournisseur.setNumeroTVA(numeroTva);
        fournisseur.setNom(donnees.getString("nom"));
        fournisseur.setLocalite(donnees.getString("localite"));
        Integer codePostal = new Integer(donnees.getInt("codepostal"));
        fournisseur.setCodePostal(codePostal);
        fournisseur.setRue(donnees.getString("rue"));
    }
}