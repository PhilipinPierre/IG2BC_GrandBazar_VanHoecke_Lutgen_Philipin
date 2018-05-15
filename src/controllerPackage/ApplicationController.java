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

    /*public MembreDuPersonnel getUtilisateur(String utilisateur, String motDePasse) throws ExceptionsBD
    {
        return membreDuPersonnel.getUtilisateur(utilisateur, motDePasse);
    }*/

    public void fermetureConnection() throws ExceptionsBD
    {
        connection.fermetureConnection();
    }

}
