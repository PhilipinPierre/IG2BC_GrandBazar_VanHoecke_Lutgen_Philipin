package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import javax.swing.*;
import java.util.ArrayList;

public class ListingOrdrePrepa extends JPanel {
    private OrdrePreparation ordrePreparation;
    private ApplicationController applicationController;
    private ArrayList<OrdrePreparation> listeOrdrePrepa;
    private JComboBox numeroSequentiel;
    private JLabel listing;

    public ListingOrdrePrepa(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;

            listing = new JLabel("Listing Ordre De Préparation : ");

            listeOrdrePrepa = applicationController.getNumSeqOrdrePreparation();
            ArrayList<Integer> valuesOrdrePrepa = new ArrayList<>();
            for(OrdrePreparation op : listeOrdrePrepa)
            {
                valuesOrdrePrepa.add(op.getNumeroSequentiel());
            }
            numeroSequentiel = new JComboBox(valuesOrdrePrepa.toArray(new Integer[0]));
            numeroSequentiel.setEnabled(true);
            add(listing);
            add(numeroSequentiel);
        }
        catch (ExceptionsBD ebd)
        {
            JOptionPane.showMessageDialog(this, ebd.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
        }
    }

}
