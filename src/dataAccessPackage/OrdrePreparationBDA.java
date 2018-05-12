package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import java.sql.*;
import java.util.*;

public class OrdrePreparationBDA implements OrdrePreparationDA {
    public ArrayList<OrdrePreparation> getAllOrdrePreparation() throws ExceptionsBD {
        try
        {
            ArrayList<OrdrePreparation> liste = new ArrayList<>();
            Connection connection = SingletonConnexion.getInstance();
            String requeteSQL = "select * from ordrepreparation";
            PreparedStatement preparedStatement = connection.prepareStatement(requeteSQL);
            ResultSet donnees = preparedStatement.executeQuery();
            while(donnees.next()){
                OrdrePreparation ordrePreparation = new OrdrePreparation();
                completerOrdrePreparation(donnees, ordrePreparation);
                liste.add(ordrePreparation);
            }
            return liste;
        }
        catch (Exception e){
            throw new ExceptionsBD("la recherche des clients dans la base de donnée");
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

        TypeArticle t = new TypeArticle();
        ordrePreparation.setCodeBarre(t);
        Cuisinier c = new Cuisinier();
        ordrePreparation.setMatriculeCui(c);
        ResponsableDesVentes rv = new ResponsableDesVentes();
        ordrePreparation.setMatriculeRes(rv);
        Recette r = new Recette();
        ordrePreparation.setNom(r);

    }
}
