package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.ListingFournisseurLocalite;

import java.sql.Connection;
import java.util.ArrayList;

public class ListingFournisseurLocaliteBDA implements ListingFournisseurLocaliteDA {
    public ArrayList<ListingFournisseurLocalite> getListingFournisseurLocalite(Integer localite) throws ExceptionsBD{
        try{
            ArrayList<ListingFournisseurLocalite> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "";
            return liste;

        } catch (Exception e){
            throw new ExceptionsBD("recherche du Listing Fournisseur par Localite");
        }
    }
}
