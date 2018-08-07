package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Lot;
import java.util.ArrayList;

public interface LotDA {
    ArrayList<Lot> getAllLot() throws ExceptionsBD;
    ArrayList<Lot> rechercheLotViaTypeArticle(String libelle) throws ExceptionsBD;
    ArrayList<Lot> rechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD;
    void ajouterLot(ApplicationController applicationController, Lot lot) throws ExceptionsBD;
}
