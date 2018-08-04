package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ListingOrdrePrepa extends JPanel {
    private OrdrePreparation ordrePreparation;
    private ApplicationController applicationController;
    private ArrayList<OrdrePreparation> listeOrdrePrepa;

    public ListingOrdrePrepa(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;

            listeOrdrePrepa = applicationController.getAllOrdrePreparation();

            removeAll();
            validate();
            PanelSuppModifOrdrePrepa panelSuppModifOrdrePrepa = new PanelSuppModifOrdrePrepa(applicationController, listeOrdrePrepa);
            add(panelSuppModifOrdrePrepa);
            revalidate();
            repaint();

        }
        catch (ExceptionsBD ebd)
        {
            JOptionPane.showMessageDialog(this, ebd.getMessage(), "Erreur lors du listing des ordres de préparation", JOptionPane.ERROR_MESSAGE);
        }
    }

}
