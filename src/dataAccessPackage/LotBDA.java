package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class LotBDA implements LotDA {
    public ArrayList<Lot> getAllLot() throws ExceptionsBD{
        try {
            ArrayList<Lot> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from lot";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                Lot lot = new Lot();
                completerLot(donnees, lot);
                liste.add(lot);
            }
            return liste;
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de la recherche de tout les lots");
        }
    }

    public ArrayList<Lot> rechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD{
        try{
            ArrayList<Lot> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from lot " +
                    "join fournisseur f ON lot.NumeroTVA = f.NumeroTVA " +
                    "join typearticle t ON lot.CodeBarre = t.CodeBarre " +
                    "join categoriearticle c ON t.ID = c.ID " +
                    "where lot.CodeBarre = t.CodeBarre " +
                    "and f.NumeroTVA = lot.NumeroTVA " +
                    "and t.ID = c.ID " +
                    "and f.localite = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setString(1, localite);
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                Lot lot = new Lot();

                TypeArticle typeArticle = new TypeArticle();
                typeArticle.setLibelle(donnees.getString("libelle"));

                CategorieArticle categorieArticle = new CategorieArticle();
                categorieArticle.setId(donnees.getString("id"));

                typeArticle.setID(categorieArticle);
                lot.setCodeBarre(typeArticle);

                lot.setQuantite(donnees.getInt("quantite"));

                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setNom(donnees.getString("nom"));

                lot.setNumeroTVA(fournisseur);

                liste.add(lot);
            }
            return liste;
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la recherche des lots via la localité du fournisseur");
        }
    }

    public ArrayList<Lot> rechercheLotViaTypeArticle(String libelle) throws ExceptionsBD{
        try{
            ArrayList<Lot> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from lot " +
                    "join fournisseur f ON lot.NumeroTVA = f.NumeroTVA " +
                    "join membredupersonnel m ON lot.Matricule = m.Matricule " +
                    "join typearticle t ON lot.CodeBarre = t.CodeBarre " +
                    "where lot.NumeroTVA = f.NumeroTVA " +
                    "and lot.Matricule = m.Matricule " +
                    "and lot.CodeBarre = t.CodeBarre " +
                    "and t.Libelle = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setString(1, libelle);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()){
                Lot lot = new Lot();

                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setNom(donnees.getString("nom"));

                lot.setNumeroTVA(fournisseur);

                lot.setQuantite(donnees.getInt("quantite"));

                MembreDuPersonnel membreDuPersonnel = new MembreDuPersonnel();
                membreDuPersonnel.setNom(donnees.getString("nom"));
                membreDuPersonnel.setPrenom(donnees.getString("prenom"));

                lot.setMatricule(membreDuPersonnel);

                liste.add(lot);
            }
            return liste;
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la recherche des lots via le type d'article");
        }
    }

    public void ajouterLot(ApplicationController applicationController, Lot lot) throws ExceptionsBD{
        try
        {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into lot values (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);

            //POUR SAVOIR LE CODE BARRE
            ArrayList <Lot> listeLot = applicationController.getAllLot();
            int i = 1;
            String id;
            for(Lot l : listeLot)
            {
                i++;
            }

            if (i > 999)
            {
                id = "lot" + i;
            }
            else
            {
                if (i > 99)
                {
                    id = "lot0" + i;
                }
                else
                {
                    if (i > 9)
                    {
                        id = "lot00" + i;
                    }
                    else
                    {
                        id = "lot0000" + i;
                    }
                }
            }
            preparedStatement.setString(1, id);

            preparedStatement.setDate(2, new java.sql.Date(lot.getDatePeremption().getTimeInMillis()));
            preparedStatement.setInt(3, lot.getQuantite());
            preparedStatement.setInt(4, lot.getCodeLot());
            preparedStatement.setDate(5, new java.sql.Date(lot.getDateFourniturePrevue().getTimeInMillis()));
            preparedStatement.setDate(6, new java.sql.Date(lot.getDateCommande().getTimeInMillis()));

            //POUR CONVERTIR LE LIBELLE DE TYPE ARTICLE EN CODE BARRE
            String typeArticle = lot.getCodeBarre().getLibelle();
            ArrayList <TypeArticle> listeTypeArticle = applicationController.getAllTypeArticle();
            int codeBarreTypeArticle = 0;
            for(TypeArticle ta : listeTypeArticle)
            {
                if (typeArticle.equals(ta.getLibelle()))
                    codeBarreTypeArticle = ta.getCodeBarre();
            }
            preparedStatement.setInt(7, codeBarreTypeArticle);

            //POUR CONVERTIR NOM DU MEMBRE DU PERSONNEL EN MATRICULE
            String membreDuPersonnel = lot.getMatricule().getNom();
            ArrayList <MembreDuPersonnel> listeMembreDuPersonnel = applicationController.getAllMembreDuPersonnel();
            int matriculeMembreDuPersonnel = 0;
            for(MembreDuPersonnel mp : listeMembreDuPersonnel)
            {
                if (membreDuPersonnel.equals(mp.getNom()))
                    matriculeMembreDuPersonnel = mp.getMatricule();
            }
            preparedStatement.setInt(8, matriculeMembreDuPersonnel);

            //POUR CONVERTIR LE NOM DU FOURNISSEUR EN NUMERO DE TVA
            String fournisseur = lot.getNumeroTVA().getNom();
            ArrayList <Fournisseur> listeFournisseur = applicationController.getAllFournisseur();
            int numeroTvaFournisseur = 0;
            for(Fournisseur f : listeFournisseur)
            {
                if (fournisseur.equals(f.getNom()))
                    numeroTvaFournisseur = f.getNumeroTVA();
            }
            preparedStatement.setInt(9, numeroTvaFournisseur);

            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            throw  new ExceptionsBD("Erreur lors de l'ajout d'une recette à la base de données");
        }
    }

    private void completerLot(ResultSet donnees, Lot lot) throws SQLException{
        lot.setId(donnees.getString("id"));
        if(donnees.getDate("dateperemption")!=null){
            GregorianCalendar date = new GregorianCalendar();
            date.setTime(donnees.getDate("dateperemption"));
            lot.setDatePeremption(date);
        }
        lot.setQuantite(donnees.getInt("quantite"));
        Integer codeLot = donnees.getInt("codeLot");
        if(!donnees.wasNull())
            lot.setCodeLot(codeLot);
        if(donnees.getDate("datefournitureprevue")!=null){
            GregorianCalendar date = new GregorianCalendar();
            date.setTime(donnees.getDate("datefournitureprevue"));
            lot.setDateFourniturePrevue(date);
        }
        GregorianCalendar dateCommande = new GregorianCalendar();
        dateCommande.setTime(donnees.getDate("datecommande"));
        lot.setDateCommande(dateCommande);

        Fournisseur f = new Fournisseur();
        lot.setNumeroTVA(f);
        MembreDuPersonnel mb = new MembreDuPersonnel();
        lot.setMatricule(mb);

    }
}
