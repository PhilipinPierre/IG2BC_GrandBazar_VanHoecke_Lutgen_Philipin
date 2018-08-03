package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

public class InsertionOrdrePrepa extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel dateCreationLabel, numeroSequentielLabel, quantitePrevueLabel, quantiteProduiteLabel,
            dateVenteLabel, datePrepaLabel, remarqueLabel, nomRecetteLabel,
            typeArticleLabel, matriculeCuisinierLabel, matriculeResponsableLabel;
    private JTextField numeroSequentiel, quantitePrevu, quantiteProduite, remarque;
    private JSpinner dateCreation, dateVente, datePrepa;
    private SpinnerDateModel dateCreationModel, datePrepaModel, dateVenteModel;
    private JRadioButton urgentTrue, urgentFalse;
    private ButtonGroup urgentButton;
    private JComboBox nomRecette, libelle, matriculeCuisinier, matriculeResponsable;
    //POUR LES BOUTONS
    private JButton retour, validation, reinitialiser;
    private ApplicationController applicationController;
    private OrdrePreparation ordrePreparation;
    private ArrayList<OrdrePreparation> listeOrdrePreparation;
    private ArrayList<Integer> listeNumSeq;
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

            listeOrdrePreparation = applicationController.getAllOrdrePreparation();
            listeNumSeq = applicationController.getNumSeqOrdrePreparation();
            Collections.sort(listeNumSeq);

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
            numeroSequentiel = new JTextField();
            numeroSequentiel.setToolTipText("Suite de 11 chiffres maximums");
            numeroSequentiel.addFocusListener(new NumeroSequentielListener());
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

            //DATE DE PREPARATION
            datePrepaLabel = new JLabel("Date de préparation : ");
            datePrepaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(datePrepaLabel);
            datePrepaModel = new SpinnerDateModel();
            datePrepa = new JSpinner(datePrepaModel);
            panneauInsertion.add(datePrepa);

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
            nomRecette.setEnabled(true);  // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauInsertion.add(nomRecette);

            //LIBELLE <- CODE BARRE (FK TYPEARTICLE)
            typeArticleLabel = new JLabel("Type article : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeArticleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(typeArticleLabel);
            listeTypeArticle = applicationController.getAllTypeArticle();
            ArrayList <String> valuesTypeArticle = new ArrayList<>();
            for(TypeArticle t : listeTypeArticle)
            {
                valuesTypeArticle.add(t.getLibelle());
            }
            libelle = new JComboBox(valuesTypeArticle.toArray(new String[0]));
            libelle.setEnabled(true);   // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauInsertion.add(libelle);

            //CUISINIER (FK CUISINIER)
            matriculeCuisinierLabel = new JLabel("Matricule cuisinier * : ");
            matriculeCuisinierLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(matriculeCuisinierLabel);
            listeCuisinier = applicationController.getAllCuisinier();
            ArrayList<String> valuesCuisinier = new ArrayList<>();
            for(Cuisinier c : listeCuisinier)
            {
                valuesCuisinier.add(c.getNom());
            }
            matriculeCuisinier = new JComboBox(valuesCuisinier.toArray(new String[0]));
            matriculeCuisinier.setEnabled(true);  // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
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

    private class NumeroSequentielListener implements java.awt.event.FocusListener{
        @Override
        public void focusGained(FocusEvent e) {}
        @Override
        public void focusLost(FocusEvent e) {
            if(numeroSequentiel.getText() != null) {
                boolean numSeqIncorrect = testNumeroSequentielIncorrect(numeroSequentiel.getText().trim());

                if (numSeqIncorrect) {
                    JOptionPane.showMessageDialog(panneauBoutons, "Le numéro séquentiel doit être compris entre 0 et 2.147.483.647");
                    numeroSequentiel.setText(null);
                } else if (NumeroSequentielDejaPresent()) {
                    JOptionPane.showMessageDialog(panneauBoutons, "Le numéro séquentiel est déjà utilisé.\nVeuillez vous référez au listing pour connaitre tout les numéros séquentiels déjà utilisés.");
                    numeroSequentiel.setText(null);
                }
            }
        }
    }

    private boolean NumeroSequentielDejaPresent(){
        for(OrdrePreparation ordre : listeOrdrePreparation)
            if(ordre.getNumeroSequentiel() == Integer.valueOf(numeroSequentiel.getText().trim()))
                return true;
        return false;
    }

    private boolean testNumeroSequentielIncorrect(String numSeq){
        try{
            //Permet de gere l'overflow
            Integer.valueOf(numSeq);

            char[] upper = numSeq.toCharArray();
            StringBuilder resultat = new StringBuilder();
            for(int i = 0; i<upper.length; i++) {
                switch (upper[i]) {
                    case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                        resultat.append(upper[i]);
                        break;
                    default:
                        return true;
                }
            }
            numeroSequentiel.setText(resultat.toString());

            return false;
        } catch (Exception e){
            return true;
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
            try
            {

                if(quantitePrevu.getText().isEmpty() ||
                        numeroSequentiel.getText().isEmpty() ||
                        urgentTrue.isSelected() == false && urgentFalse.isSelected() == false)
                    JOptionPane.showMessageDialog(panneauBoutons, "Tout les champs sont obligatoire sauf remarque !");

                if(!(   quantitePrevu.getText().isEmpty() ||
                        numeroSequentiel.getText().isEmpty() ||
                        (urgentTrue.isSelected() == false && urgentFalse.isSelected() == false))){
                    StringBuilder affichage = new StringBuilder();
                    affichage.append("Voici un récapitulatif de l'insertion:\n\n");
                    affichage.append("Date :" + dateCreation.getValue()+"\n");
                    affichage.append("Numéro séquentiel :" + numeroSequentiel.getText()+"\n");
                    affichage.append("Quantité prévue :" + quantitePrevu.getText()+"\n");
                    affichage.append("Quantité produite :");
                    if(quantiteProduite.getText().isEmpty())
                        affichage.append("0\n");
                    else
                        affichage.append(quantiteProduite.getText()+"\n");
                    affichage.append("Date de vente :" + dateVente.getValue()+"\n");
                    affichage.append("Date de préparation :" + datePrepa.getValue() + "\n");
                    affichage.append("Remarque :");
                    if(!remarque.getText().isEmpty())
                        affichage.append(remarque.getText());
                    affichage.append("\n");
                    if(urgentTrue.isSelected())
                        affichage.append("Ordre urgent\n");
                    else
                        affichage.append("Ordre non urgent\n");
                    affichage.append("Nom de la recette :" + nomRecette.getSelectedItem().toString() + "\n");
                    affichage.append("Code barre :" + libelle.getSelectedItem().toString() + "\n");
                    affichage.append("Matricule du cuisinier :" + matriculeCuisinier.getSelectedItem().toString()+"\n");
                    affichage.append("Matricule du responsable des ventes :" + matriculeResponsable.getSelectedItem().toString()+"\n");

                    JOptionPane.showMessageDialog(panneauBoutons, affichage.toString());


                    ordrePreparation.setQuantitePrevue(Integer.parseInt(quantitePrevu.getText()));
                    ordrePreparation.setQuantiteProduite(Integer.parseInt(quantiteProduite.getText()==null?"0":quantiteProduite.getText()));
                    ordrePreparation.setNumeroSequentiel(Integer.parseInt(numeroSequentiel.getText()));
                    ordrePreparation.setRemarque(remarque.getText()==null?"":remarque.getText());
                    ordrePreparation.setNom(listeRecette.get(nomRecette.getSelectedIndex()));
                    ordrePreparation.setCodeBarre(listeTypeArticle.get(libelle.getSelectedIndex()));
                    ordrePreparation.setMatriculeCui(listeCuisinier.get(matriculeCuisinier.getSelectedIndex()));
                    ordrePreparation.setMatriculeRes(listeResponsableVente.get(matriculeResponsable.getSelectedIndex()));


                    GregorianCalendar dateC = new GregorianCalendar();
                    dateC.setTime(dateCreationModel.getDate());
                    ordrePreparation.setDate(dateC);

                    GregorianCalendar dateP = new GregorianCalendar();
                    dateP.setTime(datePrepaModel.getDate());
                    ordrePreparation.setDatePreparation(dateP);

                    GregorianCalendar dateV = new GregorianCalendar();
                    dateV.setTime(dateVenteModel.getDate());
                    ordrePreparation.setDateVente(dateV);

                    ordrePreparation.setEstUrgent(urgentTrue.isSelected());

                    OrdrePreparation ordre = ordrePreparation;
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  hh:mm");
                    java.util.Date dateD = dateC.getGregorianChange();

                    /*StringBuilder affiche = new StringBuilder();
                    affiche.append("date :"+ dateFormat.format(dateD) + "\n");
                    affiche.append("Num seq :"+ordre.getNumeroSequentiel()+"\n");
                    affiche.append("Q prévue :"+ ordre.getQuantitePrevue()+"\n");
                    affiche.append("Q prod :" + ordre.getQuantiteProduite()+"\n");


                    dateD = dateV.getGregorianChange();
                    affiche.append("date v :" + dateFormat.format(dateD) + "\n");
                    dateD = dateV.getGregorianChange();
                    affiche.append("Date p :" + dateFormat.format(dateD) + "\n");
                    affiche.append("Remarque :"+ ordre.getRemarque() + "\n");
                    affiche.append("Urgent :" + ordre.getEstUrgent() + "\n");
                    affiche.append("recette :" + ordre.getNom().getNom() + "\n");
                    affiche.append("Code barre :" + ordre.getCodeBarre().getCodeBarre() + "\n");
                    affiche.append("matr cui :" + ordre.getMatriculeCui().getMatricule() + "\n");
                    affiche.append("matr ven :" + ordre.getMatriculeRes().getMatricule() + "\n");

                    JOptionPane.showMessageDialog(panneauInsertion, affiche.toString());*/



                        //Il y a une erreur à la ligne suivante.
                    applicationController.SetOrdrePreparation(applicationController, ordrePreparation);

                    JOptionPane.showMessageDialog(panneauBoutons, "L'ordre à bien été créé.");

                    dateCreation = new JSpinner(dateCreationModel);
                    numeroSequentiel.setText(null);
                    quantitePrevu.setText(null);
                    quantiteProduite.setText(null);
                    dateVente = new JSpinner(dateVenteModel);
                    datePrepa = new JSpinner(datePrepaModel);
                    remarque.setText(null);
                    urgentButton.clearSelection();

                    listeOrdrePreparation.add(ordrePreparation);
                    Collections.sort(listeOrdrePreparation);
                }

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBoutons, e.getMessage(), "Erreur lors de l'ajout d'un ordre de préparation", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            dateCreation = new JSpinner(dateCreationModel);
            numeroSequentiel.setText(null);
            quantitePrevu.setText(null);
            quantiteProduite.setText(null);
            dateVente = new JSpinner(dateVenteModel);
            datePrepa = new JSpinner(datePrepaModel);
            remarque.setText(null);
            urgentButton.clearSelection();

        }
    }
}