package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Lot;
import java.util.ArrayList;

public interface LotDA {
    ArrayList<Lot> getAllLot() throws ExceptionsBD;
    ArrayList<Lot> RechercheLotViaTypeArticle(String libelle) throws ExceptionsBD;
    ArrayList<Lot> RechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD;
}
