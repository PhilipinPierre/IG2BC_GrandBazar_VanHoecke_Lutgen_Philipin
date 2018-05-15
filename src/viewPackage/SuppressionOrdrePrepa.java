package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class SuppressionOrdrePrepa extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel dateCreationLabel, numeroSequentielLabel;
    private JSpinner dateCreation;
    private SpinnerDateModel dateCreationModel;
    private JComboBox numeroSequentiel;
    //POUR LES BOUTONS
    private JButton retour, validation, reinitialiser;
    private ApplicationController applicationController;
    private OrdrePreparation ordrePreparation;
    private ArrayList<OrdrePreparation> listeOrdrePreparation;


    public SuppressionOrdrePrepa(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;
            //FORMULAIRE
            panneauInsertion = new JPanel();

            panneauInsertion.setLayout(new GridLayout(12, 2, 5, 5));

            //DATE DE CREATION DE L'ORDRE DE PREPA OBLIGATOIRE
            dateCreationLabel = new JLabel("Date du jour : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            dateCreationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateCreationLabel);
            dateCreationModel = new SpinnerDateModel();
            dateCreation = new JSpinner(dateCreationModel);
            panneauInsertion.add(dateCreation);

            //NUMERO SEQUENTIEL AUTO INCREMENTE OBLIGATOIRE
            numeroSequentielLabel = new JLabel("Numéro Séquentiel : ");
            numeroSequentielLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(numeroSequentielLabel);
            listeOrdrePreparation = applicationController.getAllOrdrePreparation();
            ArrayList<Integer> valuesNumeroSequentiel = new ArrayList<>();
            for(OrdrePreparation op : listeOrdrePreparation)
            {
                valuesNumeroSequentiel.add(op.getNumeroSequentiel());
            }
            numeroSequentiel = new JComboBox(valuesNumeroSequentiel.toArray(new Integer[0]));
            numeroSequentiel.setEnabled(true);
            panneauInsertion.add(numeroSequentiel);

            //BOUTONS
            panneauBoutons = new JPanel();

            panneauBoutons.setLayout(new FlowLayout());

            retour = new JButton("Retour");
            panneauBoutons.add(retour);
            ButtonListenerRetour listenerRetour = new ButtonListenerRetour();
            retour.addActionListener(listenerRetour);
            validation = new JButton("Validation");
            panneauBoutons.add(validation);
            ButtonListenerValidation listenerValidation = new ButtonListenerValidation();
            validation.addActionListener(listenerValidation);
            reinitialiser = new JButton("Réinitialiser");
            panneauBoutons.add(reinitialiser);
            ButtonListenerReinitialiser listenerReinitialiser = new ButtonListenerReinitialiser();
            reinitialiser.addActionListener(listenerReinitialiser);

            add(panneauInsertion, BorderLayout.CENTER);
            add(panneauBoutons, BorderLayout.SOUTH);

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

                ordrePreparation.setNumeroSequentiel(listeOrdrePreparation.get(numeroSequentiel.getSelectedIndex()));

                applicationController.SupprimerOrdrePreparation(ordrePreparation);

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBoutons, e.getMessage(), "Erreur d'accès aux données 3", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            dateCreation.setModel(new SpinnerDateModel());
        }
    }
}