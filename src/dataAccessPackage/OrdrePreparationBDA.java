package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrdrePreparationBDA implements OrdrePreparationDA {

    private ArrayList<OrdrePreparation> getOrdrePrepa(ResultSet donnees, PreparedStatement preparedStatement) throws SQLException
    {
        ArrayList<OrdrePreparation> listeOrdrePreparation = new ArrayList<>();

        while (donnees.next()) {
            OrdrePreparation ordrePreparation = new OrdrePreparation();

            GregorianCalendar date = new GregorianCalendar();
            date.setTime(donnees.getDate("date"));
            ordrePreparation.setDate(date);

            ordrePreparation.setNumeroSequentiel(donnees.getInt("numerosequentiel"));

            ordrePreparation.setQuantitePrevue(donnees.getInt("quantiteprevue"));

            int quantiteProduite = donnees.getInt("quantiteProduite");
            if(!donnees.wasNull())
                ordrePreparation.setQuantiteProduite(quantiteProduite);

            java.sql.Date dateBD = donnees.getDate("datevente");
            if(!donnees.wasNull())
            {
                date = new GregorianCalendar();
                date.setTime(dateBD);
                ordrePreparation.setDateVente(date);
            }

            dateBD = donnees.getDate("datepreparation");
            if(!donnees.wasNull())
            {
                date = new GregorianCalendar();
                date.setTime(dateBD);
                ordrePreparation.setDatePreparation(date);
            }

            String remarque = donnees.getString("remarque");
            if(!donnees.wasNull())
                ordrePreparation.setRemarque(remarque);

            ordrePreparation.setEstUrgent(donnees.getBoolean("esturgent"));

            Recette recette = RecetteBDA.completerRecette(donnees);
            ordrePreparation.setNom(recette);

            donnees.getInt("codeBarre");
            if(!donnees.wasNull())
            {
                TypeArticle ta = TypeArticleBDA.completerTypeArticle(donnees);
                ordrePreparation.setCodeBarre(ta);
            }

            donnees.getInt("matricule_cui");
            if(!donnees.wasNull())
            {
                Cuisinier cuisinier = CuisinierBDA.completerCuisinier(donnees);
                ordrePreparation.setMatriculeCui(cuisinier);
            }

            ResponsableDesVentes rdv = ResponsableDesVentesBDA.completerResponsableDesVentes(donnees);
            ordrePreparation.setMatriculeRes(rdv);

            listeOrdrePreparation.add(ordrePreparation);
        }

        return listeOrdrePreparation;
    }

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from ordrepreparation order by NumeroSequentiel ";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            return getOrdrePrepa(donnees, preparedStatement);
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Erreur lors de l'accès à la base de données");
        }
    }

    public void supprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "delete from ordrepreparation where numerosequentiel = " + numeroSequentiel;
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new ExceptionsBD("Impossible de supprimer cet ordre de préparation : " + numeroSequentiel);
        }
    }

    public void modifierOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD
    {
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "update ordrepreparation " +
                    "set Date = ?"+
                    ", NumeroSequentiel = ?"+
                    ", QuantitePrevue = ?"+
                    ", QuantiteProduite = ?"+
                    ", DateVente = ?"+
                    ", DatePreparation = ?"+
                    ", Remarque = ?"+
                    ", EstUrgent = ?"+
                    ", Nom = ?"+
                    ", CodeBarre = ?"+
                    ", Matricule_Cui = ?"+
                    ", Matricule_Res = ?"+
                    " where NumeroSequentiel = " + ordrePreparation.getNumeroSequentiel();

            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);

            preparedStatement = completerOrdrePreparation(preparedStatement, ordrePreparation, applicationController);

            preparedStatement.executeUpdate();

        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de modification d'un ordre de préparation");
        }
    }

    public void setOrdrePreparation(ApplicationController applicationController, OrdrePreparation ordrePreparation) throws ExceptionsBD
    {
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into ordrepreparation values(?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);

            preparedStatement = completerOrdrePreparation(preparedStatement, ordrePreparation, applicationController);

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors de l'ajout d'un ordre de préparation");
        }
    }

    public PreparedStatement completerOrdrePreparation(PreparedStatement preparedStatement, OrdrePreparation ordrePreparation, ApplicationController applicationController) throws ExceptionsBD, SQLException
    {
        preparedStatement.setDate(1, new java.sql.Date(ordrePreparation.getDate().getTimeInMillis()));
        preparedStatement.setInt(2, ordrePreparation.getNumeroSequentiel());
        preparedStatement.setInt(3,ordrePreparation.getQuantitePrevue());
        if(ordrePreparation.getQuantiteProduite() == null)
            preparedStatement.setNull(4, Types.INTEGER);
        else
            preparedStatement.setInt(4, ordrePreparation.getQuantiteProduite());

        if(ordrePreparation.getDateVente() == null)
            preparedStatement.setNull(5, Types.TIME);
        else
            preparedStatement.setDate(5, new java.sql.Date(ordrePreparation.getDateVente().getTimeInMillis()));

        if(ordrePreparation.getDatePreparation() == null)
            preparedStatement.setNull(6, Types.TIME);
        else
            preparedStatement.setDate(6, new java.sql.Date(ordrePreparation.getDatePreparation().getTimeInMillis()));

        if(ordrePreparation.getRemarque() == null)
            preparedStatement.setNull(7, Types.VARCHAR);
        else
            preparedStatement.setString(7, ordrePreparation.getRemarque());
        preparedStatement.setBoolean(8, ordrePreparation.getEstUrgent());
        preparedStatement.setString(9, ordrePreparation.getNom().getNom());

        if(ordrePreparation.getCodeBarre() == null)
        {
            preparedStatement.setNull(10, Types.INTEGER);
        }
        else {
            //POUR CONVERTIR LE LIBELLE DU TYPE D'ARTICLE EN MATRICULE
            String typeArticle = ordrePreparation.getCodeBarre().getLibelle();
            ArrayList <TypeArticle> listeTypeArticle = applicationController.getAllTypeArticle();
            Integer matriculeTypeArticle = null;
            for(TypeArticle ta : listeTypeArticle)
            {
                if (typeArticle.equals(ta.getLibelle()))
                    matriculeTypeArticle = ta.getCodeBarre();
            }
            preparedStatement.setInt(10, matriculeTypeArticle);
        }

        if(ordrePreparation.getMatriculeCui() == null)
        {
            preparedStatement.setNull(11, Types.INTEGER);
        }
        else {
            //POUR CONVERTIR LE NOM DU CUISINIER EN MATRICULE
            String cuisinier = ordrePreparation.getMatriculeCui().getNom();
            ArrayList <Cuisinier> listeCuisinier = applicationController.getAllCuisinier();
            Integer matriculeCuisinier = null;
            for(Cuisinier c : listeCuisinier)
            {
                if(cuisinier.equals(c.getNom()))
                    matriculeCuisinier = c.getMatricule();
            }
            preparedStatement.setInt(11, matriculeCuisinier);
        }

        //POUR CONVERTIR LE NOM DU RESPONSABLE DE VENTE EN MATRICULE
        String respVente = ordrePreparation.getMatriculeRes().getNom();
        ArrayList <ResponsableDesVentes> listeResponsableDesVentes = applicationController.getAllResponsableDesVentes();
        Integer matriculeRespVente = null;
        for(ResponsableDesVentes rdv : listeResponsableDesVentes)
        {
            if(respVente.equals(rdv.getNom()))
                matriculeRespVente = rdv.getMatricule();
        }
        preparedStatement.setInt(12, matriculeRespVente);

        return preparedStatement;
    }
}
