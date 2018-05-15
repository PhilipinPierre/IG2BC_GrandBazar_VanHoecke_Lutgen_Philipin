package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SuppressionOrdrePrepa extends JPanel {
    private JPanel panneau;
    //POUR LE FORMULAIRE
    private JLabel numeroSequentielLabel;
    private JComboBox numeroSequentiel;
    //POUR LES BOUTONS
    private JButton retour, validation;
    private ApplicationController applicationController;
    private OrdrePreparation ordrePreparation;
    private ArrayList<OrdrePreparation> listeOrdrePreparation;
    private ArrayList<Integer> valuesNumeroSequentiel;

    public SuppressionOrdrePrepa(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;
            //FORMULAIRE
            panneau = new JPanel();

            panneau.setLayout(new GridLayout(2, 2, 5, 5));

            //NUMERO SEQUENTIEL AUTO INCREMENTE OBLIGATOIRE
            numeroSequentielLabel = new JLabel("Numéro Séquentiel : ");
            numeroSequentielLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneau.add(numeroSequentielLabel);
            listeOrdrePreparation = applicationController.getNumSeqOrdrePreparation();
            valuesNumeroSequentiel = new ArrayList<>();
            for(OrdrePreparation op : listeOrdrePreparation)
            {
                valuesNumeroSequentiel.add(op.getNumeroSequentiel());
            }
            numeroSequentiel = new JComboBox(valuesNumeroSequentiel.toArray(new Integer[0]));
            numeroSequentiel.setEnabled(true);
            panneau.add(numeroSequentiel);

            //BOUTONS
            retour = new JButton("Retour");
            panneau.add(retour);
            ButtonListenerRetour listenerRetour = new ButtonListenerRetour();
            retour.addActionListener(listenerRetour);
            validation = new JButton("Suppression");
            panneau.add(validation);
            ButtonListenerValidation listenerValidation = new ButtonListenerValidation();
            validation.addActionListener(listenerValidation);

            add(panneau, BorderLayout.CENTER);

            setVisible(true);
        }
        catch (ExceptionsBD ebd)
        {
            JOptionPane.showMessageDialog(this, ebd.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
        }

    }

    //CLASSES PRIVEES POUR LES BOUTONS
    private class ButtonListenerRetour implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAll();
            validate();

            MessageAccueil messageAccueil = new MessageAccueil();
            add(messageAccueil, BorderLayout.CENTER);

            revalidate();
            repaint();
        }
    }

    private class ButtonListenerValidation implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                applicationController.SupprimerOrdrePreparation(valuesNumeroSequentiel.get(numeroSequentiel.getSelectedIndex()));
                removeAll();
                validate();

                add(new SuppressionOrdrePrepa(applicationController, ordrePreparation), BorderLayout.CENTER);

                revalidate();
                repaint();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneau, e.getMessage(), "Erreur d'accès aux données 3", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}