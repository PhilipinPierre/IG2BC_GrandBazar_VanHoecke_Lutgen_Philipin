package controllerPackage;

import businessPackage.*;
import exceptionsPackage.*;
import modelPackage.*;
import java.util.*;

public class ApplicationController {

    private ConnectionManager connection;
    private FournisseurManager fournisseur;
    private LotManager lot;
    private MembreDuPersonnelManager membreDuPersonnel;
    private ResponsableDesVentesManager responsableDesVentes;
    private CuisinierManager cuisinier;
    private OrdrePreparationManager ordrePreparation;
    private RecetteManager recette;
    private TypeArticleManager typeArticle;
    private ArticlePerimeManager articlePerime;
    private IngredientManager ingredient;
    private CategorieArticleManager categorieArticle;
    private ReservationManager reservation;

    public ApplicationController()
    {
        connection = new ConnectionManager();
        fournisseur = new FournisseurManager();
        lot = new LotManager();
        membreDuPersonnel = new MembreDuPersonnelManager();
        responsableDesVentes = new ResponsableDesVentesManager();
        cuisinier = new CuisinierManager();
        ordrePreparation = new OrdrePreparationManager();
        recette = new RecetteManager();
        typeArticle = new TypeArticleManager();
        articlePerime = new ArticlePerimeManager();
        ingredient = new IngredientManager();
        categorieArticle = new CategorieArticleManager();
        reservation = new ReservationManager();
    }

    public ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD
    {
        return fournisseur.getAllFournisseur();
    }

    public ArrayList<Lot> getAllLot() throws ExceptionsBD
    {
        return lot.getAllLot();
    }

    public ArrayList<Lot> rechercheLotViaTypeArticle(String libelle) throws ExceptionsBD{
        return lot.rechercheLotViaTypeArticle(libelle);
    }

    public ArrayList<Lot> rechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD{
        return lot.rechercheLotViaLocaliteFournisseur(localite);
    }

    public ArrayList<MembreDuPersonnel> getAllMembreDuPersonnel() throws ExceptionsBD
    {
        return membreDuPersonnel.getAllMembreDuPersonnel();
    }

    public ArrayList<ResponsableDesVentes> getAllResponsableDesVentes() throws ExceptionsBD{
        return responsableDesVentes.getAllResponsableDesVentes();
    }

    public ArrayList<Cuisinier> getAllCuisinier() throws ExceptionsBD{
        return cuisinier.getAllCuisinier();
    }

    public ArrayList<CategorieArticle> getAllCategorieArticle()throws ExceptionsBD
    {
        return categorieArticle.getAllCategorieArticle();
    }

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD
    {
        return ordrePreparation.getAllOrdrePreparation();
    }

    public ArrayList<TypeArticle> getAllTypeArticle() throws ExceptionsBD
    {
        return typeArticle.getAllTypeArticle();
    }

    public ArrayList<Recette> getAllRecette() throws ExceptionsBD
    {
        return recette.getAllRecette();
    }

    public ArrayList<Reservation> getAllReservation() throws ExceptionsBD
    {
        return reservation.getAllReservation();
    }

    public void ajouterRecette(ApplicationController applicationController, Recette r) throws ExceptionsBD
    {
        recette.ajouterRecette(applicationController, r);
    }

    public void ajouterTypeArticle(ApplicationController applicationController, TypeArticle t) throws ExceptionsBD
    {
        typeArticle.ajouterTypeArticle(applicationController, t);
    }

    public void ajouterIngredient(ApplicationController applicationController, Ingredient i) throws ExceptionsBD
    {
        ingredient.ajouterIngredient(applicationController, i);
    }

    public void ajouterLot(ApplicationController applicationController, Lot l) throws ExceptionsBD
    {
        lot.ajouterLot(applicationController, l);
    }

    public void ajouterFournisseur(ApplicationController applicationController, Fournisseur f) throws ExceptionsBD
    {
        fournisseur.ajouterFournisseur(applicationController, f);
    }

    public void supprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD
    {
        ordrePreparation.supprimerOrdrePreparation(numeroSequentiel);
    }

    public void supprimerReservation(Integer numeroSequentiel) throws ExceptionsBD
    {
        reservation.supprimerReservation(numeroSequentiel);
    }

    /*public MembreDuPersonnel getUtilisateur(String utilisateur, String motDePasse) throws ExceptionsBD
    {
        return membreDuPersonnel.getUtilisateur(utilisateur, motDePasse);
    }*/

    public void fermetureConnection() throws ExceptionsBD
    {
        connection.fermetureConnection();
    }

    public void setOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePrep) throws ExceptionsBD
    {
        ordrePreparation.setOrdrePreparation(applicationController, ordrePrep);
    }

    public void modifierOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparat) throws ExceptionsBD
    {
        ordrePreparation.modifierOrdrePreparation(applicationController, ordrePreparat);
    }

    public void modifierReservation(Reservation reservat) throws ExceptionsBD
    {
        reservation.modifierReservation(reservat);
    }

    public ArrayList<ArticlePerime> rechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD
    {
        return articlePerime.rechercheArticlePerimeEntre2Date(date1, date2);
    }
}
