package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrdrePreparationBDA implements OrdrePreparationDA {
    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD{
        try {
            ArrayList<OrdrePreparation> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from ordrepreparation";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()) {
                OrdrePreparation ordrePreparation = new OrdrePreparation();
                completerOrdrePreparation(donnees, ordrePreparation);
                liste.add(ordrePreparation);
            }
            return liste;
        } catch (Exception e){
            throw  new ExceptionsBD("recherche de tout les ordres de préparations");
        }
    }

    public ArrayList<Integer> getNumSeqOrdrePreparation()throws ExceptionsBD{
        try{
            ArrayList<Integer> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select numerosequentiel from ordrepreparation";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()){
                liste.add(donnees.getInt("numerosequentiel"));
            }
            return liste;
        }catch (Exception e){
            throw new ExceptionsBD("Recherche des Numéro Séquentiels des ordres de préparations inaccessible");
        }
    }

    public void SupprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD{
        try{
            OrdrePreparation ordrePreparation = rechercheOrdrePreparationViaNumSeq(numeroSequentiel);
            if(ordrePreparation != null){
                Connection connection = SingletonConnexion.getInstance();
                String requeteSQL = "delete from ordrepreparation where numerosequentiel = " + numeroSequentiel;
                PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
                preparedStatement.executeQuery();
            }
        } catch (Exception e){
            throw new ExceptionsBD("Impossible de supprimer cet ordre de préparation " + numeroSequentiel);
        }
    }

    public OrdrePreparation rechercheOrdrePreparationViaNumSeq(Integer numeroSequentiel) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from ordrepreparation where numerosequeltion = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while (donnees.next()){
                if(donnees.getInt("numerosequentiel") == numeroSequentiel){
                OrdrePreparation ordrePreparation = new OrdrePreparation();
                completerOrdrePreparation(donnees, ordrePreparation);
                return ordrePreparation;
                }
            }
            return null;
        } catch (Exception e){
            throw  new ExceptionsBD(" recherche d'un ordre de préparation impossible");
        }
    }

    public void SetOrdrePreparation(OrdrePreparation ordrePreparation) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "insert into ordrepreparation values(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setDate(1, new java.sql.Date(ordrePreparation.getDate().getTimeInMillis()));
            preparedStatement.setInt(2,ordrePreparation.getNumeroSequentiel());
            preparedStatement.setInt(3,ordrePreparation.getQuantitePrevue());
            if(ordrePreparation.getQuantiteProduite() == null)
                preparedStatement.setNull(4, Types.INTEGER);
            else
                preparedStatement.setInt(4, ordrePreparation.getQuantiteProduite());
            if(ordrePreparation.getDateVente() == null)
                preparedStatement.setNull(5, Types.TIMESTAMP);
            else
                preparedStatement.setDate(5, new java.sql.Date(ordrePreparation.getDateVente().getTimeInMillis()));
            if(ordrePreparation.getDatePreparation() == null)
                preparedStatement.setNull(6, Types.TIMESTAMP);
            else
                preparedStatement.setDate(6, new java.sql.Date(ordrePreparation.getDatePreparation().getTimeInMillis()));
            if(ordrePreparation.getRemarque() == null)
                preparedStatement.setNull(7, Types.VARCHAR);
            else
                preparedStatement.setString(7, ordrePreparation.getRemarque());
            preparedStatement.setBoolean(8, ordrePreparation.getEstUrgent());
            preparedStatement.setString(9, ordrePreparation.getNom().getNom());
            if(ordrePreparation.getCodeBarre().getCodeBarre() == null)
                preparedStatement.setNull(10, Types.INTEGER);
            else
                preparedStatement.setInt(10, ordrePreparation.getCodeBarre().getCodeBarre());
            if(ordrePreparation.getMatriculeCui().getMatricule() == null)
                preparedStatement.setNull(11, Types.INTEGER);
            else
                preparedStatement.setInt(11, ordrePreparation.getMatriculeCui().getMatricule());
            preparedStatement.setInt(12, ordrePreparation.getMatriculeRes().getMatricule());


        } catch (Exception e){
            throw  new ExceptionsBD(" accès à la base de données");
        }
    }

    private void completerOrdrePreparation(ResultSet donnees, OrdrePreparation ordrePreparation)throws SQLException{
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(donnees.getDate("date"));
        ordrePreparation.setDate(date);
        Integer numeroSequentiel = donnees.getInt("numerosequential");
        ordrePreparation.setNumeroSequentiel(numeroSequentiel);
        Integer quantiteprevue = donnees.getInt("quantiteprevue");
        ordrePreparation.setQuantitePrevue(quantiteprevue);
        Integer quantiteProduite = donnees.getInt("quantiteproduite");
        if(!donnees.wasNull())
            ordrePreparation.setQuantitePrevue(donnees.getInt("quantiteproduite"));
        java.sql.Date dateTest = donnees.getDate("datevente");
        if(!donnees.wasNull()){
            GregorianCalendar dateVente = new GregorianCalendar();
            dateVente.setTime(dateTest);
            ordrePreparation.setDateVente(dateVente);
        }
        dateTest = donnees.getDate("datepreparation");
        if(!donnees.wasNull()){
            GregorianCalendar datePreparation = new GregorianCalendar();
            datePreparation.setTime(dateTest);
            ordrePreparation.setDatePreparation(datePreparation);
        }
        String remarque = donnees.getString("remarque");
        if(!donnees.wasNull())
            ordrePreparation.setRemarque(remarque);
        ordrePreparation.setEstUrgent(donnees.getBoolean("esturgent"));

        Cuisinier c = new Cuisinier();
        ordrePreparation.setMatriculeCui(c);
        Recette r = new Recette();
        ordrePreparation.setNom(r);
        ResponsableDesVentes rv = new ResponsableDesVentes();
        ordrePreparation.setMatriculeRes(rv);
        TypeArticle t = new TypeArticle();
        ordrePreparation.setCodeBarre(t);
    }
}
