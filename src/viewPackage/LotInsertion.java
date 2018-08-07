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

public class LotInsertion extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel datePeremptionLabel, quantiteLabel, codeLotLabel, dateFourniturePrevueLabel, dateCommandeLabel,
                fournisseurLabel, typeArticleLabel, membreDuPersonnelLabel, datePeremptionCheckboxLabel,
            dateFourniturePrevueCheckboxLabel;
    private JTextField quantite, codeLot;
    private JComboBox fournisseur, typeArticle, membreDuPersonnel;
    private SpinnerDateModel datePeremptionModel, dateFourniturePrevueModel, dateCommandeModel;
    private JSpinner datePeremptionSpinner, dateFourniturePrevueSpinner, dateCommandeSpinner;
    private JCheckBox datePeremptionCheckbox, dateFourniturePrevueCheckbox;
    //POUR LES BOUTONS
    private JButton retour, ajoutFournisseur, reinitialiser, ajoutLot;
    private ApplicationController applicationController;
    private ArrayList<Fournisseur> listeFournisseur;
    private ArrayList<TypeArticle> listeTypeArticle;
    private ArrayList<MembreDuPersonnel> listeMembreDuPersonnel;

    public LotInsertion(ApplicationController applicationController)
    {
        try {
            this.applicationController = applicationController;

            setLayout(new BorderLayout());

            //FORMULAIRE
            panneauInsertion = new JPanel();

            panneauInsertion.setLayout(new GridLayout(11, 2, 5, 5));

            //LOT
            //DATE PEREMPTION
            datePeremptionLabel = new JLabel("Date de péremption : ");
            datePeremptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(datePeremptionLabel);
            datePeremptionModel = new SpinnerDateModel();
            datePeremptionSpinner = new JSpinner(datePeremptionModel);
            panneauInsertion.add(datePeremptionSpinner);

            //DATE DE PEREMPTION CHECKBOX POUR DESACTIVER LA DATE
            datePeremptionCheckboxLabel = new JLabel("Désactiver la date de péremption : ");
            datePeremptionCheckboxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(datePeremptionCheckboxLabel);
            datePeremptionCheckbox = new JCheckBox();
            LotInsertion.CheckBoxListenerDatePeremption listenerDatePeremption = new LotInsertion.CheckBoxListenerDatePeremption();
            datePeremptionCheckbox.addItemListener(listenerDatePeremption);
            panneauInsertion.add(datePeremptionCheckbox);

            //QUANTITE
            quantiteLabel = new JLabel("Quantité * : ");
            quantiteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(quantiteLabel);
            quantite = new JTextField();
            panneauInsertion.add(quantite);

            //CODE LOT
            codeLotLabel = new JLabel("Code lot : ");
            codeLotLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(codeLotLabel);
            codeLot = new JTextField();
            panneauInsertion.add(codeLot);

            //DATE FOURNITURE PREVUE
            dateFourniturePrevueLabel = new JLabel("Date de fourniture prévue : ");
            dateFourniturePrevueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateFourniturePrevueLabel);
            dateFourniturePrevueModel = new SpinnerDateModel();
            dateFourniturePrevueSpinner = new JSpinner(dateFourniturePrevueModel);
            panneauInsertion.add(dateFourniturePrevueSpinner);

            //DATE FOURNITURE PREVUE CHECKBOX POUR DESACTIVER LA DATE
            dateFourniturePrevueCheckboxLabel = new JLabel("Désactiver la date de fourniture prévue : ");
            dateFourniturePrevueCheckboxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateFourniturePrevueCheckboxLabel);
            dateFourniturePrevueCheckbox = new JCheckBox();
            LotInsertion.CheckBoxListenerDateFourniturePrevue listenerDateFourniturePrevue = new LotInsertion.CheckBoxListenerDateFourniturePrevue();
            dateFourniturePrevueCheckbox.addItemListener(listenerDateFourniturePrevue);
            panneauInsertion.add(dateFourniturePrevueCheckbox);

            //DATE COMMANDE
            dateCommandeLabel = new JLabel("Début de commande * : ");
            dateCommandeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateCommandeLabel);
            dateCommandeModel = new SpinnerDateModel();
            dateCommandeSpinner = new JSpinner(dateCommandeModel);
            panneauInsertion.add(dateCommandeSpinner);

            //FOURNISSEUR
            fournisseurLabel = new JLabel("Fournisseur * : ");
            fournisseurLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(fournisseurLabel);
            ArrayList<String> valuesFournisseur = new ArrayList<>();
            listeFournisseur = applicationController.getAllFournisseur();
            for(Fournisseur f : listeFournisseur)
            {
                valuesFournisseur.add(f.getNom());
            }
            fournisseur = new JComboBox(valuesFournisseur.toArray(new String[0]));
            fournisseur.setEnabled(true);
            panneauInsertion.add(fournisseur);

            //TYPE ARTICLE LIBELLE -> CODE BARRE
            typeArticleLabel = new JLabel("Type article * : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeArticleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(typeArticleLabel);
            ArrayList<String> valuesTypeArticle = new ArrayList<>();
            listeTypeArticle = applicationController.getAllTypeArticle();
            for(TypeArticle t : listeTypeArticle)
            {
                valuesTypeArticle.add(t.getLibelle());
            }
            typeArticle = new JComboBox(valuesTypeArticle.toArray(new String[0]));
            typeArticle.setEnabled(true);
            panneauInsertion.add(typeArticle);

            //MEMBRE DU PERSONNEL
            membreDuPersonnelLabel = new JLabel("Membre du personnel * : ");
            membreDuPersonnelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(membreDuPersonnelLabel);
            listeMembreDuPersonnel = applicationController.getAllMembreDuPersonnel();
            ArrayList<String> valuesCuisinier = new ArrayList<>();
            for(MembreDuPersonnel m : listeMembreDuPersonnel)
            {
                valuesCuisinier.add(m.getNom());
            }
            membreDuPersonnel = new JComboBox(valuesCuisinier.toArray(new String[0]));
            membreDuPersonnel.setEnabled(true);
            panneauInsertion.add(membreDuPersonnel);

            //BOUTONS
            panneauBoutons = new JPanel();

            panneauBoutons.setLayout(new FlowLayout());

            retour = new JButton("<- Retour");
            panneauBoutons.add(retour);
            LotInsertion.ButtonListenerRetour listenerRetour = new LotInsertion.ButtonListenerRetour();
            retour.addActionListener(listenerRetour);

            reinitialiser = new JButton("Réinitialiser");
            panneauBoutons.add(reinitialiser);
            LotInsertion.ButtonListenerReinitialiser listenerReinitialiser = new LotInsertion.ButtonListenerReinitialiser();
            reinitialiser.addActionListener(listenerReinitialiser);

            ajoutLot = new JButton("Ajouter lot");
            panneauBoutons.add(ajoutLot);
            LotInsertion.ButtonListenerAjouterLot listenerAjouterLot = new LotInsertion.ButtonListenerAjouterLot();
            ajoutLot.addActionListener(listenerAjouterLot);

            ajoutFournisseur = new JButton("Créer un fournisseur ->");
            panneauBoutons.add(ajoutFournisseur);
            LotInsertion.ButtonListenerAjouterFournisseur listenerAjouterFournisseur = new LotInsertion.ButtonListenerAjouterFournisseur();
            ajoutFournisseur.addActionListener(listenerAjouterFournisseur);

            add(panneauInsertion, BorderLayout.NORTH);

            add(panneauBoutons, BorderLayout.SOUTH);

            setEnabled(true);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur lors de l'ajout d'un lot", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CheckBoxListenerDatePeremption implements ItemListener
    {
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange() == ItemEvent.SELECTED)
                datePeremptionSpinner.setEnabled(false);
            else
                datePeremptionSpinner.setEnabled(true);
        }
    }
    private class CheckBoxListenerDateFourniturePrevue implements ItemListener
    {
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange() == ItemEvent.SELECTED)
                dateFourniturePrevueSpinner.setEnabled(false);
            else
                dateFourniturePrevueSpinner.setEnabled(true);
        }
    }

    //CLASSES PRIVEES POUR LES BOUTONS
    private class ButtonListenerRetour implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAll();
            validate();

            TypeArticleInsertion typeArticleInsertion = new TypeArticleInsertion(applicationController);
            add(typeArticleInsertion);

            revalidate();
            repaint();
        }
    }

    private class ButtonListenerAjouterLot implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int nbErreurs = 0;
            try
            {
                if(quantite.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(panneauInsertion, "Tous les champs sont obligatoire sauf le code lot, la date de peremption et la date de fourniture prévue");
                }
                else
                {
                    try
                    {
                        int quantLot = Integer.parseInt(quantite.getText());
                        if (quantLot < 0) {
                            JOptionPane.showMessageDialog(panneauInsertion, "La quantité doit être un nombre entier positif !");
                            nbErreurs++;
                        }
                    }
                    catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(panneauInsertion, "La quantité doit être un nombre entier !");
                        nbErreurs++;
                    }

                    try {
                        if (!codeLot.getText().isEmpty() && Integer.parseInt(codeLot.getText()) < 0) {
                            JOptionPane.showMessageDialog(panneauInsertion, "Le code lot doit être un nombre entier positif !");
                            nbErreurs++;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(panneauInsertion, "La code lot doit être un nombre entier !");
                        nbErreurs++;
                    }

                    if(datePeremptionModel.getDate().compareTo(dateFourniturePrevueModel.getDate()) <= 0 && !datePeremptionCheckbox.isSelected() && !dateFourniturePrevueCheckbox.isSelected())
                    {
                        JOptionPane.showMessageDialog(panneauInsertion, "La date de péremption doit être plus grande que la date de fourniture prévue !");
                        nbErreurs++;
                    }

                    if(datePeremptionModel.getDate().compareTo(dateCommandeModel.getDate()) <= 0 && !datePeremptionCheckbox.isSelected())
                    {
                        JOptionPane.showMessageDialog(panneauInsertion, "La date de péremption doit être plus grande que la date de commande !");
                        nbErreurs++;
                    }

                    if(dateFourniturePrevueModel.getDate().compareTo(dateCommandeModel.getDate()) <= 0 && !dateFourniturePrevueCheckbox.isSelected())
                    {
                        JOptionPane.showMessageDialog(panneauInsertion, "La date de fourniture prévue doit être plus grande que la date de commande !");
                        nbErreurs++;
                    }

                    if(nbErreurs == 0) {
                        //AJOUT LOT
                        Lot lot = new Lot();

                        GregorianCalendar dateP = new GregorianCalendar();
                        dateP.setTime(datePeremptionModel.getDate());
                        if(datePeremptionCheckbox.isSelected())
                            lot.setDatePeremption(null);
                        else
                            lot.setDatePeremption(dateP);

                        lot.setQuantite(Integer.valueOf(quantite.getText()));

                        if(codeLot.getText().isEmpty())
                            lot.setCodeLot(null);
                        else
                            lot.setCodeLot(Integer.valueOf(codeLot.getText()));

                        GregorianCalendar dateF = new GregorianCalendar();
                        dateF.setTime(dateFourniturePrevueModel.getDate());
                        if(dateFourniturePrevueCheckbox.isSelected())
                            lot.setDateFourniturePrevue(null);
                        else
                            lot.setDateFourniturePrevue(dateF);

                        GregorianCalendar dateC = new GregorianCalendar();
                        dateC.setTime(dateCommandeModel.getDate());
                        lot.setDateCommande(dateC);

                        lot.setCodeBarre(listeTypeArticle.get(typeArticle.getSelectedIndex()));
                        lot.setMatricule(listeMembreDuPersonnel.get(membreDuPersonnel.getSelectedIndex()));
                        lot.setNumeroTVA(listeFournisseur.get(fournisseur.getSelectedIndex()));

                        applicationController.ajouterLot(applicationController, lot);

                        JOptionPane.showMessageDialog(panneauInsertion, "Le lot a bien été ajouté !");
                    }
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauInsertion, "Erreur lors de l'ajout d'un nouveau lot");
            }
        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            quantite.setText(null);
            codeLot.setText(null);
            typeArticle.setSelectedIndex(0);
            membreDuPersonnel.setSelectedIndex(0);
            fournisseur.setSelectedIndex(0);
        }
    }

    private class ButtonListenerAjouterFournisseur implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAll();
            validate();
            FournisseurInsertion fournisseurInsertion = new FournisseurInsertion(applicationController);
            add(fournisseurInsertion);
            revalidate();
            repaint();
        }
    }
}
