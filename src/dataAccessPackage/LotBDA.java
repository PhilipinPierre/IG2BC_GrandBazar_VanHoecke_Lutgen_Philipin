package dataAccessPackage;

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
                CompleterLot(donnees, lot);
                liste.add(lot);
            }
            return liste;
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de la recherche de tout les lots");
        }
    }

    public ArrayList<Lot> RechercheLotViaLocaliteFournisseur(String localite) throws ExceptionsBD{
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

    public ArrayList<Lot> RechercheLotViaTypeArticle(String libelle) throws ExceptionsBD{
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

    private void CompleterLot(ResultSet donnees, Lot lot) throws SQLException{
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
