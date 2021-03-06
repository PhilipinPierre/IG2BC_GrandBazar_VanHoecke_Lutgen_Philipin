package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ModificationOrdrePrepa extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel dateCreationLabel, numeroSequentielLabel, quantitePrevueLabel, quantiteProduiteLabel,
            dateVenteLabel, datePrepaLabel, remarqueLabel, nomRecetteLabel,
            typeArticleLabel, matriculeCuisinierLabel, matriculeResponsableLabel,
            dateVenteCheckboxLabel, datePrepaCheckboxLabel;
    private JTextField quantitePrevu, quantiteProduite, remarque;
    private JSpinner dateCreation, dateVente, datePrepa;
    private SpinnerDateModel dateCreationModel, datePrepaModel, dateVenteModel;
    private JRadioButton urgentTrue, urgentFalse;
    private ButtonGroup urgentButton;
    private JComboBox nomRecette, libelle, matriculeCuisinier, matriculeResponsable, numeroSequentiel;
    private JCheckBox dateVenteCheckbox, datePrepaCheckbox;
    //POUR LES BOUTONS
    private JButton retour, validation, reinitialiser;
    private ApplicationController applicationController;
    private OrdrePreparation ordrePreparation;
    private ArrayList<Recette> listeRecette;
    private ArrayList<TypeArticle> listeTypeArticle;
    private ArrayList<Cuisinier> listeCuisinier;
    private ArrayList<ResponsableDesVentes> listeResponsableVente;
    private ArrayList<OrdrePreparation> listeOrdrePreparation;
    private ArrayList<Integer> valuesNumeroSequentiel;


    public ModificationOrdrePrepa(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;

            setLayout(new BorderLayout());

            //FORMULAIRE
            panneauInsertion = new JPanel();

            panneauInsertion.setLayout(new GridLayout(14, 2, 5, 5));

            //DATE DE CREATION DE L'ORDRE DE PREPA OBLIGATOIRE
            dateCreationLabel = new JLabel("Date * : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            dateCreationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateCreationLabel);
            dateCreationModel = new SpinnerDateModel();
            dateCreation = new JSpinner(dateCreationModel);
            //BULLES D'AIDE
            dateCreation.setToolTipText("Date de la création de l'ordre de préparation");
            panneauInsertion.add(dateCreation);

            //NUMERO SEQUENTIEL AUTO INCREMENTE OBLIGATOIRE
            numeroSequentielLabel = new JLabel("Numéro Séquentiel * : ");
            numeroSequentielLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(numeroSequentielLabel);
            listeOrdrePreparation = applicationController.getAllOrdrePreparation();
            valuesNumeroSequentiel = new ArrayList<>();
            for(OrdrePreparation op : listeOrdrePreparation)
            {
                valuesNumeroSequentiel.add(op.getNumeroSequentiel());
            }
            numeroSequentiel = new JComboBox(valuesNumeroSequentiel.toArray(new Integer[0]));
            numeroSequentiel.setEnabled(true);
            panneauInsertion.add(numeroSequentiel);

            //QUANTITE PREVUE A LA CREATION DE L'ORDRE OBLIGATOIRE
            quantitePrevueLabel = new JLabel("Quantité prévue * : ");
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
            dateVenteModel = new SpinnerDateModel();
            dateVente = new JSpinner(dateVenteModel);
            panneauInsertion.add(dateVente);

            //DATE DE VENTE CHECKBOX POUR DESACTIVER LA DATE
            dateVenteCheckboxLabel = new JLabel("Désactiver la date de vente : ");
            dateVenteCheckboxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateVenteCheckboxLabel);
            dateVenteCheckbox = new JCheckBox();
            ModificationOrdrePrepa.CheckBoxListenerDateVente listenerDateVente = new ModificationOrdrePrepa.CheckBoxListenerDateVente();
            dateVenteCheckbox.addItemListener(listenerDateVente);
            panneauInsertion.add(dateVenteCheckbox);

            //DATE DE PREPARATION
            datePrepaLabel = new JLabel("Date de préparation : ");
            datePrepaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(datePrepaLabel);
            datePrepaModel = new SpinnerDateModel();
            datePrepa = new JSpinner(datePrepaModel);
            panneauInsertion.add(datePrepa);

            //DATE DE PREPARATION CHECKBOX POUR DESACTIVER LA DATE
            datePrepaCheckboxLabel = new JLabel("Désactiver la date de préparation : ");
            datePrepaCheckboxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(datePrepaCheckboxLabel);
            datePrepaCheckbox = new JCheckBox();
            ModificationOrdrePrepa.CheckBoxListenerDatePrepa listenerDatePrepa = new ModificationOrdrePrepa.CheckBoxListenerDatePrepa();
            datePrepaCheckbox.addItemListener(listenerDatePrepa);
            panneauInsertion.add(datePrepaCheckbox);

            //REMARQUE
            remarqueLabel = new JLabel("Remarque : ");
            remarqueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(remarqueLabel);
            remarque = new JTextField();
            panneauInsertion.add(remarque);

            //URGENT ? OBLIGATOIRE
            urgentTrue = new JRadioButton("* Urgent", false);
            panneauInsertion.add(urgentTrue);
            urgentFalse = new JRadioButton("Pas urgent", false);
            panneauInsertion.add(urgentFalse);
            urgentButton = new ButtonGroup();
            urgentButton.add(urgentTrue);
            urgentButton.add(urgentFalse);

            //NOM DE LA RECETTE OBLIGATOIRE (FK RECETTE)
            nomRecetteLabel = new JLabel("Nom de la recette * : ");
            nomRecetteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(nomRecetteLabel);
            listeRecette = applicationController.getAllRecette();
            ArrayList<String> valuesRecette = new ArrayList<>();
            for(Recette r : listeRecette)
            {
                valuesRecette.add(r.getNom());
            }
            nomRecette = new JComboBox(valuesRecette.toArray(new String[0]));
            nomRecette.setEnabled(true);
            panneauInsertion.add(nomRecette);

            //LIBELLE <- CODE BARRE (FK TYPEARTICLE)
            typeArticleLabel = new JLabel("Type article : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeArticleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(typeArticleLabel);
            listeTypeArticle = applicationController.getAllTypeArticle();
            ArrayList <String> valuesTypeArticle = new ArrayList<>();
            valuesTypeArticle.add(" ");
            for(TypeArticle t : listeTypeArticle)
            {
                valuesTypeArticle.add(t.getLibelle());
            }
            libelle = new JComboBox(valuesTypeArticle.toArray(new String[0]));
            libelle.setEnabled(true);
            panneauInsertion.add(libelle);

            //CUISINIER (FK CUISINIER)
            matriculeCuisinierLabel = new JLabel("Matricule cuisinier : ");
            matriculeCuisinierLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(matriculeCuisinierLabel);
            listeCuisinier = applicationController.getAllCuisinier();
            ArrayList<String> valuesCuisinier = new ArrayList<>();
            valuesCuisinier.add(" ");
            for(Cuisinier c : listeCuisinier)
            {
                valuesCuisinier.add(c.getNom());
            }
            matriculeCuisinier = new JComboBox(valuesCuisinier.toArray(new String[0]));
            matriculeCuisinier.setEnabled(true);
            panneauInsertion.add(matriculeCuisinier);

            //RESPONSABLE DE VENTE OBLIGATOIRE (FK RESPONSABLE VENTE)
            matriculeResponsableLabel = new JLabel("Nom responsable vente * : ");
            matriculeResponsableLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(matriculeResponsableLabel);
            listeResponsableVente = applicationController.getAllResponsableDesVentes();
            ArrayList<String> valuesResponsableVente = new ArrayList<>();
            for(ResponsableDesVentes rv : listeResponsableVente)
            {
                valuesResponsableVente.add(rv.getNom());
            }
            matriculeResponsable = new JComboBox(valuesResponsableVente.toArray(new String[0]));
            matriculeResponsable.setEnabled(true);
            panneauInsertion.add(matriculeResponsable);

            //BOUTONS
            panneauBoutons = new JPanel();

            panneauBoutons.setLayout(new FlowLayout());

            retour = new JButton("<- Retour");
            panneauBoutons.add(retour);
            ModificationOrdrePrepa.ButtonListenerRetour listenerRetour = new ModificationOrdrePrepa.ButtonListenerRetour();
            retour.addActionListener(listenerRetour);
            reinitialiser = new JButton("Réinitialiser");
            panneauBoutons.add(reinitialiser);
            ModificationOrdrePrepa.ButtonListenerReinitialiser listenerReinitialiser = new ModificationOrdrePrepa.ButtonListenerReinitialiser();
            reinitialiser.addActionListener(listenerReinitialiser);
            validation = new JButton("Modifier");
            panneauBoutons.add(validation);
            ModificationOrdrePrepa.ButtonListenerValidation listenerValidation = new ModificationOrdrePrepa.ButtonListenerValidation();
            validation.addActionListener(listenerValidation);

            add(panneauInsertion, BorderLayout.NORTH);
            add(panneauBoutons, BorderLayout.SOUTH);

            setVisible(true);
        }
        catch (Exception ebd)
        {
            JOptionPane.showMessageDialog(this, ebd.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CheckBoxListenerDateVente implements ItemListener
    {
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange() == ItemEvent.SELECTED)
                dateVente.setEnabled(false);
            else
                dateVente.setEnabled(true);
        }
    }

    private class CheckBoxListenerDatePrepa implements ItemListener
    {
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange() == ItemEvent.SELECTED)
                datePrepa.setEnabled(false);
            else
                datePrepa.setEnabled(true);
        }
    }

    //CLASSES PRIVEES POUR LES BOUTONS
    private class ButtonListenerRetour implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAll();
            validate();

            add(new MessageAccueil(), BorderLayout.CENTER);

            revalidate();
            repaint();
        }
    }

    private class ButtonListenerValidation implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int nbErreurs = 0;
            if(quantitePrevu.getText().isEmpty() || !urgentTrue.isSelected() && !urgentFalse.isSelected())
            {
                JOptionPane.showMessageDialog(panneauInsertion, "Les champs obligatoire sont : date, numéro séquentiel, quantité prévue, urgent, recette et responsable de vente !");
            }
            else
            {
                try
                {
                    int quantPrevu = Integer.parseInt(quantitePrevu.getText());
                    if(quantPrevu < 0)
                    {
                        JOptionPane.showMessageDialog(panneauInsertion, "La quantité prévue doit être un nombre positif entier !");
                        nbErreurs++;
                    }
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(panneauInsertion, "La quantité prévue doit être un nombre entier !");
                    nbErreurs++;
                }
                try
                {
                    if(!quantiteProduite.getText().isEmpty() && (Integer.parseInt(quantiteProduite.getText()) < 0))
                    {
                        JOptionPane.showMessageDialog(panneauInsertion, "La quantité produite doit être un nombre positif entier !");
                        nbErreurs++;
                    }
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(panneauInsertion, "La quantité produite doit être un nombre entier !");
                    nbErreurs++;
                }

                if(dateVenteModel.getDate().compareTo(datePrepaModel.getDate()) <= 0 && !datePrepaCheckbox.isSelected() && !dateVenteCheckbox.isSelected())
                {
                    JOptionPane.showMessageDialog(panneauInsertion, "La date de vente doit être plus grande à la date de préparation !");
                    nbErreurs++;
                }

                if(datePrepaModel.getDate().compareTo(dateCreationModel.getDate()) <= 0 && !datePrepaCheckbox.isSelected())
                {
                    JOptionPane.showMessageDialog(panneauInsertion, "La date de préparation doit être plus grande à la date de création de l'ordre !");
                    nbErreurs++;
                }

                if(dateVenteModel.getDate().compareTo(dateCreationModel.getDate()) <= 0 && !dateVenteCheckbox.isSelected())
                {
                    JOptionPane.showMessageDialog(panneauInsertion, "La date de vente doit être plus grande à la date de création de l'ordre !");
                    nbErreurs++;
                }

                if(nbErreurs == 0)
                {
                    try {
                        //TEST POUR SAVOIR S'IL L'ORDRE A UNE RESERVATION QUI EST LIE
                        Integer numSeq = Integer.valueOf(valuesNumeroSequentiel.get(numeroSequentiel.getSelectedIndex()));
                        Reservation reservation = new Reservation();
                        ArrayList<Reservation> listeReservation = applicationController.getAllReservation();
                        Boolean estReserve = false;
                        Boolean estSuppr = false;

                        for (Reservation r : listeReservation)
                        {
                            if (numSeq.equals(r.getNumeroSequentiel()))
                            {
                                GregorianCalendar dateC = new GregorianCalendar();
                                dateC.setTime(dateCreationModel.getDate());
                                reservation.setDate(dateC);

                                reservation.setNumeroSequentiel(numSeq);
                                reservation.setCodeBarre(r.getCodeBarre());
                                reservation.setQuantiteReservee(r.getQuantiteReservee());

                                int reponse = JOptionPane.showConfirmDialog(panneauInsertion, "L'ordre est lié à une réservation. Voulez-vous modifer la réservation ?",
                                        "Réservation détectée ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                                if (reponse == 0)
                                {
                                    applicationController.supprimerReservation(numSeq);
                                    estSuppr = true;
                                }
                                estReserve = true;
                            }
                        }

                        if((!estReserve && !estSuppr) || (estSuppr && estReserve)) {
                            ordrePreparation.setNumeroSequentiel(valuesNumeroSequentiel.get(numeroSequentiel.getSelectedIndex()));
                            ordrePreparation.setQuantitePrevue(Integer.valueOf(quantitePrevu.getText()));

                            if (quantiteProduite.getText().isEmpty())
                                ordrePreparation.setQuantiteProduite(null);
                            else
                                ordrePreparation.setQuantiteProduite(Integer.valueOf(quantiteProduite.getText()));

                            ordrePreparation.setRemarque(remarque.getText().isEmpty() ? null : remarque.getText());
                            ordrePreparation.setNom(listeRecette.get(nomRecette.getSelectedIndex()));

                            if (libelle.getSelectedIndex() == 0)
                                ordrePreparation.setCodeBarre(null);
                            else
                                ordrePreparation.setCodeBarre(listeTypeArticle.get(libelle.getSelectedIndex() - 1));

                            if (matriculeCuisinier.getSelectedIndex() == 0)
                                ordrePreparation.setMatriculeCui(null);
                            else
                                ordrePreparation.setMatriculeCui(listeCuisinier.get(matriculeCuisinier.getSelectedIndex() - 1));

                            ordrePreparation.setMatriculeRes(listeResponsableVente.get(matriculeResponsable.getSelectedIndex()));

                            GregorianCalendar dateC = new GregorianCalendar();
                            dateC.setTime(dateCreationModel.getDate());
                            ordrePreparation.setDate(dateC);

                            GregorianCalendar dateP = new GregorianCalendar();
                            dateP.setTime(datePrepaModel.getDate());
                            if (datePrepaCheckbox.isSelected())
                                ordrePreparation.setDatePreparation(null);
                            else
                                ordrePreparation.setDatePreparation(dateP);

                            GregorianCalendar dateV = new GregorianCalendar();
                            dateV.setTime(dateVenteModel.getDate());
                            if (dateVenteCheckbox.isSelected())
                                ordrePreparation.setDateVente(null);
                            else
                                ordrePreparation.setDateVente(dateV);

                            ordrePreparation.setEstUrgent(urgentTrue.isSelected());

                            applicationController.modifierOrdrePreparation(applicationController, ordrePreparation);

                            JOptionPane.showMessageDialog(panneauInsertion, "L'ordre a bien été modifié.");

                            numeroSequentiel.setSelectedIndex(0);
                            quantitePrevu.setText(null);
                            quantiteProduite.setText(null);
                            remarque.setText(null);
                            urgentButton.clearSelection();
                            nomRecette.setSelectedIndex(0);
                            libelle.setSelectedIndex(0);
                            matriculeCuisinier.setSelectedIndex(0);
                            matriculeResponsable.setSelectedIndex(0);
                        }

                        if(estReserve && estSuppr)
                            applicationController.modifierReservation(reservation);

                        if(estReserve && !estSuppr)
                            JOptionPane.showMessageDialog(panneauInsertion, "L'ordre n'a pas été modifié.");

                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(panneauInsertion, "Erreur lors de la modification d'un ordre de préparation !");
                    }
                }
            }
        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            numeroSequentiel.setSelectedIndex(0);
            quantitePrevu.setText(null);
            quantiteProduite.setText(null);
            remarque.setText(null);
            urgentButton.clearSelection();
            nomRecette.setSelectedIndex(0);
            libelle.setSelectedIndex(0);
            matriculeCuisinier.setSelectedIndex(0);
            matriculeResponsable.setSelectedIndex(0);
        }
    }
}