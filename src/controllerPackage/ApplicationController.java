package controllerPackage;

import businessPackage.*;
import exceptionsPackage.*;
import modelPackage.*;

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
    }

    public ArrayList<Client> getAllClient() throws ExceptionsBD
    {
        return client.getAllClient();
    }

    public ArrayList<Fournisseur> getAllFournisseur() throws ExceptionsBD
    {
        return fournisseur.getAllFournisseur();
    }

    public ArrayList<LigneTicket> getAllLigneTicket() throws ExceptionsBD
    {
        return ligneTicket.getAllLigneTicket();
    }

    public ArrayList<Lot> getAllLot() throws ExceptionsBD
    {
        return lot.getAllLot();
    }
    public ArrayList<Lot> RechercheLotViaTypeArticle(Integer codeBarre) throws ExceptionsBD{
        return lot.RechercheLotViaTypeArticle(codeBarre);
    }

    public ArrayList<Lot> RechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD{
        return lot.RechercheLotViaLocaliteFournisseur(localite);
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

    public ArrayList<OrdrePreparation> getNumSeqOrdrePreparation() throws  ExceptionsBD{
        return ordrePreparation.getNumSeqOrdrePreparation();
    }

    public void SupprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD{
        ordrePreparation.SupprimerOrdrePreparation(numeroSequentiel);
    }

    /*public MembreDuPersonnel getUtilisateur(String utilisateur, String motDePasse) throws ExceptionsBD
    {
        return membreDuPersonnel.getUtilisateur(utilisateur, motDePasse);
    }*/

    public void fermetureConnection() throws ExceptionsBD
    {
        connection.fermetureConnection();
    }

    public void SetOrdrePreparation(OrdrePreparation ordrePrep) throws ExceptionsBD{
        ordrePreparation.SetOrdrePreparation(ordrePrep);
    }

    public void ModifierOrdrePreparation(OrdrePreparation ordrePreparat) throws ExceptionsBD{
        ordrePreparation.ModifierOrdrePreparation(ordrePreparat);
    }

    public ArrayList<ArticlePerime> RechercheArticlePerimeEntre2Date(GregorianCalendar date1, GregorianCalendar date2) throws ExceptionsBD
    {
        return articlePerime.RechercheArticlePerimeEntre2Date(date1, date2);
    }
}
