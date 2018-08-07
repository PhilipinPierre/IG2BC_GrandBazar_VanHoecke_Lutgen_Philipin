package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Lot;
import java.sql.SQLException;
import java.util.ArrayList;

public interface LotDA {
    ArrayList<Lot> getAllLot() throws ExceptionsBD, SQLException;
    ArrayList<Lot> rechercheLotViaTypeArticle(String libelle) throws ExceptionsBD, SQLException;
    ArrayList<Lot> rechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD, SQLException;
    void ajouterLot(ApplicationController applicationController, Lot lot) throws ExceptionsBD, SQLException;
}
