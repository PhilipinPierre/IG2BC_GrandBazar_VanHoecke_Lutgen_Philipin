package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.Client;
import java.sql.*;
import java.util.*;

public class ClientBDA implements ClientDA {

    @Override
    public ArrayList<Client> getAllClient() throws ExceptionsBD, SQLException
    {
        ArrayList<Client> listeClient = new ArrayList<>();
        Connection connection = SingletonConnexion.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Client");
        ResultSet donnees = preparedStatement.executeQuery();

        while(donnees.next())
        {
            Client client = new Client();
            completerClient(donnees,client);
            listeClient.add(client);
        }
        return listeClient;
    }

    private void completerClient(ResultSet donnees, Client client) throws SQLException
    {
        client.setNumClient(donnees.getInt("numClient"));
        client.setNom(donnees.getString("nom"));
        client.setPrenom(donnees.getString("prenom"));
        GregorianCalendar dateNaissance = new GregorianCalendar();
        dateNaissance.setTime(donnees.getDate("datenaissance"));
        client.setDateNaissance(dateNaissance);
        client.setCodePostal(donnees.getInt("codepostal"));
        client.setLocalite(donnees.getString("localite"));
        client.setRue(donnees.getString("rue"));
        GregorianCalendar dateCreation = new GregorianCalendar();
        dateCreation.setTime(donnees.getDate("datecreationcompte"));
        client.setDateCreationCompte(dateCreation);
    }
}
