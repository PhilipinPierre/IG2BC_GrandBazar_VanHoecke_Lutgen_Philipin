package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.OrdrePreparation;
import modelPackage.Reservation;
import javax.swing.*;
import java.util.ArrayList;

public class ListingOrdrePrepa extends JPanel {
    private OrdrePreparation ordrePreparation;
    private ApplicationController applicationController;
    private ArrayList<OrdrePreparation> listeOrdrePrepa;
    private ArrayList<Reservation> listeReservation;

    public ListingOrdrePrepa(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;

            listeOrdrePrepa = applicationController.getAllOrdrePreparation();
            listeReservation = applicationController.getAllReservation();

            removeAll();
            validate();
            PanelSuppModifOrdrePrepa panelSuppModifOrdrePrepa = new PanelSuppModifOrdrePrepa(applicationController, listeOrdrePrepa, listeReservation);
            add(panelSuppModifOrdrePrepa);
            revalidate();
            repaint();

        }
        catch (Exception ebd)
        {
            JOptionPane.showMessageDialog(this, ebd.getMessage(), "Erreur lors du listing des ordres de préparation", JOptionPane.ERROR_MESSAGE);
        }
    }

}
