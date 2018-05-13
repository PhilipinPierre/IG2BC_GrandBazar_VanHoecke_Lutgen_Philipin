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

    public ListingOrdrePrepa(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;

            listeOrdrePrepa = applicationController.getAllOrdrePreparation();
            ArrayList<Integer> valuesOrdrePrepa = new ArrayList<>();
            for(OrdrePreparation op : listeOrdrePrepa)
            {
                valuesOrdrePrepa.add(op.getNumeroSequentiel());
                System.out.println(valuesOrdrePrepa.add(op.getNumeroSequentiel()));
            }
        }
        catch (ExceptionsBD ebd)
        {
            JOptionPane.showMessageDialog(this, ebd.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
        }
    }

}
