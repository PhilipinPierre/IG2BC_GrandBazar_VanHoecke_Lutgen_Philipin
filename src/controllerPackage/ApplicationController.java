package controllerPackage;

import businessPackage.*;
import exceptionsPackage.*;
import modelPackage.*;
import java.sql.SQLException;
import java.util.*;

public class ApplicationController {

    private ClientManager client;
    private ConnectionManager connection;
    private FournisseurManager fournisseur;
    private LigneTicketManager ligneTicket;
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
        client = new ClientManager();
        connection = new ConnectionManager();
        fournisseur = new FournisseurManager();
        ligneTicket = new LigneTicketManager();
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

    public ArrayList<Client> getAllClient() throws ExceptionsBD, SQLException
    {
        return client.getAllClient();
    }

    public ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD, SQLException
    {
        return fournisseur.getAllFournisseur();
    }

    public ArrayList<Ingredient> getAllIngredient() throws ExceptionsBD, SQLException
    {
        return ingredient.getAllIngredient();
    }

    public ArrayList<LigneTicket> getAllLigneTicket() throws ExceptionsBD, SQLException
    {
        return ligneTicket.getAllLigneTicket();
    }

    public ArrayList<Lot> getAllLot() throws ExceptionsBD, SQLException
    {
        return lot.getAllLot();
    }

    public ArrayList<Lot> rechercheLotViaTypeArticle(String libelle) throws ExceptionsBD, SQLException
    {
        return lot.rechercheLotViaTypeArticle(libelle);
    }

    public ArrayList<Lot> rechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD, SQLException
    {
        return lot.rechercheLotViaLocaliteFournisseur(localite);
    }

    public ArrayList<MembreDuPersonnel> getAllMembreDuPersonnel() throws ExceptionsBD, SQLException
    {
        return membreDuPersonnel.getAllMembreDuPersonnel();
    }

    public ArrayList<ResponsableDesVentes> getAllResponsableDesVentes() throws ExceptionsBD, SQLException
    {
        return responsableDesVentes.getAllResponsableDesVentes();
    }

    public ArrayList<Cuisinier> getAllCuisinier() throws ExceptionsBD, SQLException
    {
        return cuisinier.getAllCuisinier();
    }

    public ArrayList<CategorieArticle> getAllCategorieArticle()throws ExceptionsBD, SQLException
    {
        return categorieArticle.getAllCategorieArticle();
    }

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD, SQLException
    {
        return ordrePreparation.getAllOrdrePreparation();
    }

    public ArrayList<TypeArticle> getAllTypeArticle() throws ExceptionsBD, SQLException
    {
        return typeArticle.getAllTypeArticle();
    }

    public ArrayList<Recette> getAllRecette() throws ExceptionsBD, SQLException
    {
        return recette.getAllRecette();
    }

    public ArrayList<Reservation> getAllReservation() throws ExceptionsBD, SQLException
    {
        return reservation.getAllReservation();
    }

    public Recette getRecette(String nom) throws ExceptionsBD, SQLException
    {
        return recette.getRecette(nom);
    }

    public void ajouterRecette(ApplicationController applicationController, Recette r) throws ExceptionsBD, SQLException
    {
        recette.ajouterRecette(applicationController, r);
    }

    public void ajouterTypeArticle(ApplicationController applicationController, TypeArticle t) throws ExceptionsBD, SQLException
    {
        typeArticle.ajouterTypeArticle(applicationController, t);
    }

    public void ajouterIngredient(ApplicationController applicationController, Ingredient i) throws ExceptionsBD, SQLException
    {
        ingredient.ajouterIngredient(applicationController, i);
    }

    public void ajouterLot(ApplicationController applicationController, Lot l) throws ExceptionsBD, SQLException
    {
        lot.ajouterLot(applicationController, l);
    }

    public void ajouterFournisseur(ApplicationController applicationController, Fournisseur f) throws ExceptionsBD, SQLException
    {
        fournisseur.ajouterFournisseur(applicationController, f);
    }

    public ArrayList<Integer> getNumSeqOrdrePreparation() throws  ExceptionsBD, SQLException
    {
        return ordrePreparation.getNumSeqOrdrePreparation();
    }

    public void supprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD, SQLException
    {
        ordrePreparation.supprimerOrdrePreparation(numeroSequentiel);
    }

    public void supprimerReservation(Integer numeroSequentiel) throws ExceptionsBD, SQLException
    {
        reservation.supprimerReservation(numeroSequentiel);
    }

    /*public MembreDuPersonnel getUtilisateur(String utilisateur, String motDePasse) throws ExceptionsBD
    {
        return membreDuPersonnel.getUtilisateur(utilisateur, motDePasse);
    }*/

    public void fermetureConnection() throws ExceptionsBD, SQLException
    {
        connection.fermetureConnection();
    }

    public void setOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePrep) throws ExceptionsBD, SQLException
    {
        ordrePreparation.setOrdrePreparation(applicationController, ordrePrep);
    }

    public void modifierOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparat) throws ExceptionsBD, SQLException
    {
        ordrePreparation.modifierOrdrePreparation(applicationController, ordrePreparat);
    }

    public void modifierReservation(Reservation reservat) throws ExceptionsBD, SQLException
    {
        reservation.modifierReservation(reservat);
    }

    public ArrayList<ArticlePerime> rechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD, SQLException
    {
        return articlePerime.rechercheArticlePerimeEntre2Date(date1, date2);
    }
}
