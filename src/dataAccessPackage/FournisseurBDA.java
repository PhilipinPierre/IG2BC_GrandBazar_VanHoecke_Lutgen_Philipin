package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Fournisseur;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FournisseurBDA /*implements FournisseurDA*/ {
    public static ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD{
        try {
            ArrayList<Fournisseur> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from fournisseur";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnes = preparedStatement.executeQuery();
            while (donnes.next()) {
                Fournisseur fournisseur = new Fournisseur();
                CompleterFournisseur(donnes, fournisseur);
                liste.add(fournisseur);
            }
            return liste;
        } catch (Exception e){
            throw new ExceptionsBD("recherche de tout les fournisseurs");
        }
    }

    private static void CompleterFournisseur(ResultSet donnees, Fournisseur fournisseur)throws Exception{
        Integer numeroTva = new Integer(donnees.getInt("numerotva"));
        fournisseur.setNumeroTVA(numeroTva);
        fournisseur.setNom(donnees.getString("nom"));
        fournisseur.setLocalite(donnees.getString("localite"));
        Integer codePostal = new Integer(donnees.getInt("codepostal"));
        fournisseur.setCodePostal(codePostal);
        fournisseur.setRue(donnees.getString("rue"));
    }
}
