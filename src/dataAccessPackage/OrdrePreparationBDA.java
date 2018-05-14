package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.*;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private void completerOrdrePreparation(ResultSet donnees, OrdrePreparation ordrePreparation)throws SQLException{
        GregorianCalendar date = new GregorianCalendar();
        date.setTime(donnees.getDate("date"));
        ordrePreparation.setDate(date);
        Integer numeroSequentiel = new Integer(donnees.getInt("numerosequential"));
        ordrePreparation.setNumeroSequentiel(numeroSequentiel);
        Integer quantiteprevue = new Integer(donnees.getInt("quantiteprevue"));
        ordrePreparation.setQuantitePrevue(quantiteprevue);
        Integer quantiteProduite = new Integer(donnees.getInt("quantiteproduite"));
        if(quantiteProduite != null)
            ordrePreparation.setQuantitePrevue(quantiteProduite);
        if(donnees.getDate("datevente")!=null){
            GregorianCalendar dateVente = new GregorianCalendar();
            dateVente.setTime(donnees.getDate("datevente"));
            ordrePreparation.setDateVente(dateVente);
        }
        if(donnees.getDate("datePreparation")!=null){
            GregorianCalendar datePreparation = new GregorianCalendar();
            datePreparation.setTime(donnees.getDate("datePreparation"));
            ordrePreparation.setDatePreparation(datePreparation);
        }
        if(donnees.getString("remarque")!=null)
            ordrePreparation.setRemarque(donnees.getString("remarque"));
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
