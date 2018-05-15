package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertionOrdrePrepa extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel dateCreationLabel, numeroSequentielLabel, quantitePrevueLabel, quantiteProduiteLabel,
            dateVenteLabel, datePrepaLabel, remarqueLabel, nomRecetteLabel,
            codeBarreLabel, matriculeCuisinierLabel, matriculeResponsableLabel;
    private JTextField numeroSequentiel, quantitePrevu, quantiteProduite, remarque;
    private JSpinner dateCreation, dateVente, datePrepa;
    private JRadioButton urgentTrue, urgentFalse;
    private ButtonGroup urgentButton;
    private JComboBox nomRecette, codeBarre, matriculeCuisinier, matriculeResponsable;
    //POUR LES BOUTONS
    private JButton retour, validation, reinitialiser;
    private ApplicationController applicationController;
    private OrdrePreparation ordrePreparation;
    private ArrayList<Recette> listeRecette;
    private ArrayList<TypeArticle> listeTypeArticle;
    private ArrayList<Cuisinier> listeCuisinier;
    private ArrayList<ResponsableDesVentes> listeResponsableVente;


    public InsertionOrdrePrepa(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;
            //FORMULAIRE
            panneauInsertion = new JPanel();

            panneauInsertion.setLayout(new GridLayout(12, 2, 5, 5));

            //DATE DE CREATION DE L'ORDRE DE PREPA OBLIGATOIRE
            dateCreationLabel = new JLabel("Date : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            dateCreationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateCreationLabel);
            dateCreation = new JSpinner();
            dateCreation.setModel(new SpinnerDateModel());
            //BULLES D'AIDE
            dateCreation.setToolTipText("Date de la création de l'ordre de préparation");
            panneauInsertion.add(dateCreation);

            //NUMERO SEQUENTIEL AUTO INCREMENTE OBLIGATOIRE
            numeroSequentielLabel = new JLabel("Numéro Séquentiel : ");
            numeroSequentielLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(numeroSequentielLabel);
            numeroSequentiel = new JTextField();
            panneauInsertion.add(numeroSequentiel);

            //QUANTITE PREVUE A LA CREATION DE L'ORDRE OBLIGATOIRE
            quantitePrevueLabel = new JLabel("Quantité prévue : ");
            quantitePrevueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(quantitePrevueLabel);
            quantitePrevu = new JTextField();
            panneauInsertion.add(quantitePrevu);

            //QUANTITE PRODUITE
            quantiteProduiteLabel = new JLabel("Quantité produite : ");
            quantiteProduiteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(quantiteProduiteLabel);
            quantiteProduite = new JTextField();
            panneauInsertion.add(quantiteProduite);

            //DATE DE VENTE
            dateVenteLabel = new JLabel("Date de vente : ");
            dateVenteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateVenteLabel);
            dateVente = new JSpinner();
            dateVente.setModel(new SpinnerDateModel());
            panneauInsertion.add(dateVente);

            //DATE DE PREPARATION
            datePrepaLabel = new JLabel("Date de préparation : ");
            datePrepaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(datePrepaLabel);
            datePrepa = new JSpinner();
            datePrepa.setModel(new SpinnerDateModel());
            panneauInsertion.add(datePrepa);

            //REMARQUE
            remarqueLabel = new JLabel("Remarque : ");
            remarqueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(remarqueLabel);
            remarque = new JTextField();
            panneauInsertion.add(remarque);

            //URGENT ? OBLIGATOIRE
            urgentTrue = new JRadioButton("Urgent", false);
            panneauInsertion.add(urgentTrue);
            urgentFalse = new JRadioButton("Pas urgent", false);
            panneauInsertion.add(urgentFalse);
            urgentButton = new ButtonGroup();
            urgentButton.add(urgentTrue);
            urgentButton.add(urgentFalse);

            //NOM DE LA RECETTE OBLIGATOIRE (FK RECETTE)
            nomRecetteLabel = new JLabel("Nom de la recette : ");
            nomRecetteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(nomRecetteLabel);
            listeRecette = applicationController.getAllRecette();
            ArrayList<String> valuesRecette = new ArrayList<>();
            for(Recette r : listeRecette)
            {
                valuesRecette.add(r.getNom());
            }
            nomRecette = new JComboBox(valuesRecette.toArray(new String[0]));
            nomRecette.setEnabled(true);  // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauInsertion.add(nomRecette);

            //CODE BARRE (FK TYPEARTICLE)
            codeBarreLabel = new JLabel("Code Barre : ");
            codeBarreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(codeBarreLabel);
            listeTypeArticle = applicationController.getAllTypeArticle();
            ArrayList<String> valuesTypeArticle = new ArrayList<>();
            for(TypeArticle t : listeTypeArticle)
            {
                valuesTypeArticle.add(t.getLibelle());
            }
            codeBarre = new JComboBox(valuesTypeArticle.toArray(new String[0]));
            codeBarre.setEnabled(true);   // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauInsertion.add(codeBarre);

            //MATRICULE CUISINE (FK CUISINIER)
            matriculeCuisinierLabel = new JLabel("Matricule cuisinier : ");
            matriculeCuisinierLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(matriculeCuisinierLabel);
            listeCuisinier = applicationController.getAllCuisinier();
            ArrayList<Integer> valuesCuisinier = new ArrayList<>();
            for(Cuisinier c : listeCuisinier)
            {
                valuesCuisinier.add(c.getMatricule());
            }
            matriculeCuisinier = new JComboBox(valuesCuisinier.toArray(new Integer[0]));
            matriculeCuisinier.setEnabled(true);  // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauInsertion.add(matriculeCuisinier);

            //MATRICULE RESPONSABLE OBLIGATOIRE (FK RESPONSABLE VENTE)
            matriculeResponsableLabel = new JLabel("Matricule responsable vente : ");
            matriculeResponsableLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(matriculeResponsableLabel);
            listeResponsableVente = applicationController.getAllResponsableDesVentes();
            ArrayList<Integer> valuesResponsableVente = new ArrayList<>();
            for(ResponsableDesVentes rv : listeResponsableVente)
            {
                valuesResponsableVente.add(rv.getMatricule());
            }
            matriculeResponsable = new JComboBox(valuesResponsableVente.toArray(new Integer[0]));
            matriculeResponsable.setEnabled(true);
            panneauInsertion.add(matriculeResponsable);

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
            /*insertionBD(dateCreation.getValue(), numeroSequentiel.getText(), quantitePrevu.getText(),
                    quantiteProduite.getText(), dateVente.getValue(), datePrepa.getValue(), remarque.getText());*/
        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            dateCreation.setModel(new SpinnerDateModel());
            numeroSequentiel.setText(null);
            quantitePrevu.setText(null);
            quantiteProduite.setText(null);
            dateVente.setModel(new SpinnerDateModel());
            datePrepa.setModel(new SpinnerDateModel());
            remarque.setText(null);
            urgentButton.clearSelection();

        }
    }
}