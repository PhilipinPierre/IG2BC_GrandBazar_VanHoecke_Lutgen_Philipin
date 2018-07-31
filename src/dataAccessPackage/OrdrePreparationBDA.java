package dataAccessPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class OrdrePreparationBDA implements OrdrePreparationDA {

    private ArrayList<OrdrePreparation> getOrdrePrepa(Connection connection, PreparedStatement preparedStatement) throws SQLException
    {
        ArrayList<OrdrePreparation> listeOrdrePreparation = new ArrayList<>();
        ApplicationController applicationController = new ApplicationController();
        ResultSet donnees = preparedStatement.executeQuery();

        while (donnees.next()) {
            OrdrePreparation ordrePreparation = new OrdrePreparation();

            GregorianCalendar date = new GregorianCalendar();
            date.setTime(donnees.getDate("date"));
            ordrePreparation.setDate(date);

            ordrePreparation.setNumeroSequentiel(donnees.getInt("numerosequentiel"));

            ordrePreparation.setQuantitePrevue(donnees.getInt("quantiteprevue"));

            ordrePreparation.setQuantiteProduite(donnees.getInt("quantiteproduite"));

            GregorianCalendar datev = new GregorianCalendar();
            datev.setTime(donnees.getDate("datevente"));
            ordrePreparation.setDateVente(datev);

            if(donnees.getDate("datepreparation") != null) {
                GregorianCalendar dateP = new GregorianCalendar();
                dateP.setTime(donnees.getDate("datepreparation"));
                ordrePreparation.setDatePreparation(dateP);
            }

            String remarque = donnees.getString("remarque");
            if(!donnees.wasNull())
                ordrePreparation.setRemarque(remarque);

            ordrePreparation.setEstUrgent(donnees.getBoolean("esturgent"));


            // BUG ICI A CAUSE DE RECETTE !!!! TOUT LES CLES ETRANGERE ????
            //Recette recette = RecetteBDA.CompleterRecette(donnees);
            //ordrePreparation.setNom(recette);

            // FONCTIONNE PAS
            //MembreDuPersonnel cuisinier = MembreDuPersonnelBDA.getMembreDuPersonnel(donnees.getInt("cuisinier"));
            //ordrePreparation.setMatriculeCui(cuisinier);

            // FONCTIONNE PAS
            //MembreDuPersonnel rdv = MembreDuPersonnelBDA.getMembreDuPersonnel(donnees.getInt("respDesVentes"));
            //ordrePreparation.setMatriculeRes(rdv);

            // FONCTIONNE PAS
            /*int codeBarre = donnees.getInt("codeBarre");
            if(!donnees.wasNull())
            {
                TypeArticle ta = new TypeArticle();
                ta.setCodeBarre(codeBarre);
                ta.setLibelle(donnees.getString("libelle"));
                ordrePreparation.setCodeBarre(ta);
            }*/

            listeOrdrePreparation.add(ordrePreparation);
        }

        return listeOrdrePreparation;
    }

    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from ordrepreparation order by 'NumeroSequentiel'";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            return getOrdrePrepa(connection, preparedStatement);
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Erreur lors de l'accès à la base de données");
        }
    }

    public ArrayList<Integer> getNumSeqOrdrePreparation() throws ExceptionsBD {
        try {
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from ordrepreparation order by 'NumeroSequentiel'";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);

            ArrayList<Integer> liste = new ArrayList<>();
            for(OrdrePreparation ordre : getOrdrePrepa(connection, preparedStatement))
                liste.add(ordre.getNumeroSequentiel());
            return liste;
        }
        catch (Exception e)
        {
            throw new ExceptionsBD("Erreur lors de l'accès à la base de données");
        }
    }

    public void SupprimerOrdrePreparation(Integer numeroSequentiel) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "delete from ordrepreparation where numerosequentiel = " + numeroSequentiel;
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new ExceptionsBD("Impossible de supprimer cet ordre de préparation " + numeroSequentiel);
        }
    }

    public void ModifierOrdrePreparation(OrdrePreparation ordrePreparation) throws ExceptionsBD{
        try{
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "update OrdreDePreparation " +
                    "set date = ?"+
                    ", numerosequentiel = ?"+
                    ",quantitePrevue = ?"+
                    ", quantiteProduite = ?"+
                    ", dateVente = ?"+
                    ", datePreparation = ?"+
                    ", remarque = ?"+
                    ", estUrgent = ?"+
                    ", nom = ?"+
                    ", codebarre = ?"+
                    ", matricule_cui = ?"+
                    ", matricule_res = ?"+
                    " where numeroSequentiel = ?" + ordrePreparation.getNumeroSequentiel();
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            preparedStatement.setDate(1, new java.sql.Date(ordrePreparation.getDate().getTimeInMillis()));
            preparedStatement.setInt(2,ordrePreparation.getNumeroSequentiel());
            preparedStatement.setInt(3,ordrePreparation.getQuantitePrevue());
            if(ordrePreparation.getQuantiteProduite() == null)
                preparedStatement.setNull(4, Types.INTEGER);
            else
                preparedStatement.setInt(4, ordrePreparation.getQuantiteProduite());

            preparedStatement.setDate(5, new java.sql.Date(ordrePreparation.getDateVente().getTimeInMillis()));
            preparedStatement.setDate(6, new java.sql.Date(ordrePreparation.getDatePreparation().getTimeInMillis()));

            if(ordrePreparation.getRemarque() == null)
                preparedStatement.setNull(7, Types.VARCHAR);
            else
                preparedStatement.setString(7, ordrePreparation.getRemarque());
            preparedStatement.setBoolean(8, ordrePreparation.getEstUrgent());
            preparedStatement.setString(9, ordrePreparation.getNom().getNom());
            if(ordrePreparation.getCodeBarre().getCodeBarre() == null)
                preparedStatement.setNull(10, Types.INTEGER);
            else {
                preparedStatement.setInt(10, ordrePreparation.getCodeBarre().getCodeBarre());
            }
            if(ordrePreparation.getMatriculeCui().getMatricule() == null)
                preparedStatement.setNull(11, Types.INTEGER);
            else
                preparedStatement.setInt(11, ordrePreparation.getMatriculeCui().getMatricule());
            preparedStatement.setInt(12, ordrePreparation.getMatriculeRes().getMatricule());

            preparedStatement.executeUpdate();
        } catch (Exception e){
            throw new ExceptionsBD("Erreur lors de la modification d'un ordre de préparation");
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

            preparedStatement.setDate(5, new java.sql.Date(ordrePreparation.getDateVente().getTimeInMillis()));
            preparedStatement.setDate(6, new java.sql.Date(ordrePreparation.getDatePreparation().getTimeInMillis()));

            if(ordrePreparation.getRemarque() == null)
                preparedStatement.setNull(7, Types.VARCHAR);
            else
                preparedStatement.setString(7, ordrePreparation.getRemarque());
            preparedStatement.setBoolean(8, ordrePreparation.getEstUrgent());
            preparedStatement.setString(9, ordrePreparation.getNom().getNom());
            if(ordrePreparation.getCodeBarre().getCodeBarre() == null)
                preparedStatement.setNull(10, Types.INTEGER);
            else {
                preparedStatement.setInt(10, ordrePreparation.getCodeBarre().getCodeBarre());
            }
            if(ordrePreparation.getMatriculeCui().getMatricule() == null)
                preparedStatement.setNull(11, Types.INTEGER);
            else
                preparedStatement.setInt(11, ordrePreparation.getMatriculeCui().getMatricule());
            preparedStatement.setInt(12, ordrePreparation.getMatriculeRes().getMatricule());

            preparedStatement.executeUpdate();

        } catch (Exception e){
            throw  new ExceptionsBD("Erreur lors d'une modification d'un ordre de préparation");
        }
    }

    /*public void completerOrdrePreparation(ResultSet donnees, OrdrePreparation ordrePreparation)throws SQLException{
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
    }*/
}
