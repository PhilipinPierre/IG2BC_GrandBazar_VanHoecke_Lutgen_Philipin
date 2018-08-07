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

public class TypeArticleInsertion extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel typeArticleLabel,
            prixLabel, quantiteStockLabel, dateDebutLabel, dateFinLabel, quantiteMinimalLabel, idLabel,
            dateDebutCheckboxLabel, dateFinCheckboxLabel;
    private JTextField prix, quantiteStock, quantiteMinimal, libelle;
    private JComboBox id;
    private JRadioButton estPerissableTrue, estPerissableFalse;
    private ButtonGroup estPerissable;
    private SpinnerDateModel dateDebutModel, dateFinModel;
    private JSpinner dateDebutSpinner, dateFinSpinner;
    private ArrayList<CategorieArticle> listeId;
    private JCheckBox dateDebutCheckbox, dateFinCheckbox;
    //POUR LES BOUTONS
    private JButton retour, ajoutIngredient, reinitialiser, ajoutTypeArticle;
    private ApplicationController applicationController;

    public TypeArticleInsertion(ApplicationController applicationController)
    {
        try {
            this.applicationController = applicationController;

            setLayout(new BorderLayout());

            //FORMULAIRE
            panneauInsertion = new JPanel();

            panneauInsertion.setLayout(new GridLayout(11, 2, 5, 5));

            //TYPE ARTICLE
            //LIBELLE <- CODE BARRE (FK TYPEARTICLE)
            typeArticleLabel = new JLabel("Libellé article * : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeArticleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(typeArticleLabel);
            libelle = new JTextField();
            panneauInsertion.add(libelle);

            //PRIX
            prixLabel = new JLabel("Prix * : ");
            prixLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(prixLabel);
            prix = new JTextField();
            panneauInsertion.add(prix);

            //QUANTITE EN STOCK
            quantiteStockLabel = new JLabel("Quantité en stock * : ");
            quantiteStockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(quantiteStockLabel);
            quantiteStock = new JTextField();
            panneauInsertion.add(quantiteStock);

            //DATE PROMOTION DEBUT
            dateDebutLabel = new JLabel("Début promotion : ");
            dateDebutLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateDebutLabel);
            dateDebutModel = new SpinnerDateModel();
            dateDebutSpinner = new JSpinner(dateDebutModel);
            panneauInsertion.add(dateDebutSpinner);

            //DATE DE PROMOTION DEBUT CHECKBOX POUR DESACTIVER LA DATE
            dateDebutCheckboxLabel = new JLabel("Désactiver la date de promotion début : ");
            dateDebutCheckboxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateDebutCheckboxLabel);
            dateDebutCheckbox = new JCheckBox();
            TypeArticleInsertion.CheckBoxListenerDateDebut listenerDateDebut = new TypeArticleInsertion.CheckBoxListenerDateDebut();
            dateDebutCheckbox.addItemListener(listenerDateDebut);
            panneauInsertion.add(dateDebutCheckbox);

            //DATE PROMOTION FIN
            dateFinLabel = new JLabel("Fin promotion : ");
            dateFinLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateFinLabel);
            dateFinModel = new SpinnerDateModel();
            dateFinSpinner = new JSpinner(dateFinModel);
            panneauInsertion.add(dateFinSpinner);

            //DATE DE PROMOTION FIN CHECKBOX POUR DESACTIVER LA DATE
            dateFinCheckboxLabel = new JLabel("Désactiver la date de promotion début : ");
            dateFinCheckboxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateFinCheckboxLabel);
            dateFinCheckbox = new JCheckBox();
            TypeArticleInsertion.CheckBoxListenerDateFin listenerDateFin = new TypeArticleInsertion.CheckBoxListenerDateFin();
            dateFinCheckbox.addItemListener(listenerDateFin);
            panneauInsertion.add(dateFinCheckbox);

            //EST PERISSABLE
            estPerissableTrue = new JRadioButton("* Périssable", false);
            panneauInsertion.add(estPerissableTrue);
            estPerissableFalse = new JRadioButton("Non périssable", false);
            panneauInsertion.add(estPerissableFalse);
            estPerissable = new ButtonGroup();
            estPerissable.add(estPerissableTrue);
            estPerissable.add(estPerissableFalse);

            //QUANTITE MINIMAL
            quantiteMinimalLabel = new JLabel("Quantité minimale : ");
            quantiteMinimalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(quantiteMinimalLabel);
            quantiteMinimal = new JTextField();
            panneauInsertion.add(quantiteMinimal);

            //ID DE CATEGORIE ARTICLE
            idLabel = new JLabel("Catégorie article * : ");
            idLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(idLabel);
            listeId = applicationController.getAllCategorieArticle();
            ArrayList<String> valuesId = new ArrayList<>();
            for (CategorieArticle c : listeId) {
                valuesId.add(c.getId());
            }
            id = new JComboBox(valuesId.toArray(new String[0]));
            id.setEnabled(true);
            panneauInsertion.add(id);

            //BOUTONS
            panneauBoutons = new JPanel();

            panneauBoutons.setLayout(new FlowLayout());

            retour = new JButton("<- Retour");
            panneauBoutons.add(retour);
            TypeArticleInsertion.ButtonListenerRetour listenerRetour = new TypeArticleInsertion.ButtonListenerRetour();
            retour.addActionListener(listenerRetour);

            reinitialiser = new JButton("Réinitialiser");
            panneauBoutons.add(reinitialiser);
            TypeArticleInsertion.ButtonListenerReinitialiser listenerReinitialiser = new TypeArticleInsertion.ButtonListenerReinitialiser();
            reinitialiser.addActionListener(listenerReinitialiser);

            ajoutIngredient = new JButton("Ajouter article");
            panneauBoutons.add(ajoutIngredient);
            TypeArticleInsertion.ButtonListenerAjouterTypeArticle listenerAjouterTypeArticle = new TypeArticleInsertion.ButtonListenerAjouterTypeArticle();
            ajoutIngredient.addActionListener(listenerAjouterTypeArticle);

            ajoutTypeArticle = new JButton("Créer un lot ->");
            panneauBoutons.add(ajoutTypeArticle);
            TypeArticleInsertion.ButtonListenerAjouterLot listenerAjouterLot = new TypeArticleInsertion.ButtonListenerAjouterLot();
            ajoutTypeArticle.addActionListener(listenerAjouterLot);

            add(panneauInsertion, BorderLayout.NORTH);

            add(panneauBoutons, BorderLayout.SOUTH);

            setVisible(true);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(panneauBoutons, e.getMessage(), "Erreur lors de l'ajout d'un type d'article", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CheckBoxListenerDateDebut implements ItemListener
    {
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange() == ItemEvent.SELECTED)
                dateDebutSpinner.setEnabled(false);
            else
                dateDebutSpinner.setEnabled(true);
        }
    }

    private class CheckBoxListenerDateFin implements ItemListener
    {
        public void itemStateChanged(ItemEvent event)
        {
            if (event.getStateChange() == ItemEvent.SELECTED)
                dateFinSpinner.setEnabled(false);
            else
                dateFinSpinner.setEnabled(true);
        }
    }

    //CLASSES PRIVEES POUR LES BOUTONS
    private class ButtonListenerRetour implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAll();
            validate();

            RecetteInsertion recetteInsertion = new RecetteInsertion(applicationController);
            add(recetteInsertion);

            revalidate();
            repaint();
        }
    }

    private class ButtonListenerAjouterTypeArticle implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int nbErreurs = 0;
            try
            {
                if(libelle.getText().isEmpty() || prix.getText().isEmpty() || quantiteStock.getText().isEmpty()  || !estPerissableFalse.isSelected() && !estPerissableTrue.isSelected())
                {
                    JOptionPane.showMessageDialog(panneauInsertion, "Tout les champs sont obligatoire sauf la quantité minimale, la date de promotion début et de fin ! ");
                }
                else {
                    try {
                        double prixTypeArticle = Double.parseDouble(prix.getText());
                        if (prixTypeArticle < 0) {
                            JOptionPane.showMessageDialog(panneauInsertion, "Le prix doit être un nombre réel ou entier positif !");
                            nbErreurs++;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(panneauInsertion, "La prix doit être un nombre réel ou entier (exemple : 4.5) ");
                        nbErreurs++;
                    }

                    try {
                        int quantStock = Integer.parseInt(quantiteStock.getText());
                        if (quantStock < 0) {
                            JOptionPane.showMessageDialog(panneauInsertion, "La quantité en stock doit être un nombre entier positif !");
                            nbErreurs++;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(panneauInsertion, "La quantité en stock doit être un nombre entier !");
                        nbErreurs++;
                    }

                    try {
                        if (!quantiteMinimal.getText().isEmpty() && Integer.parseInt(quantiteMinimal.getText()) < 0) {
                            JOptionPane.showMessageDialog(panneauInsertion, "La quantité minimal doit être un nombre entier positif !");
                            nbErreurs++;
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(panneauInsertion, "La quantité minimal doit être un nombre entier !");
                        nbErreurs++;
                    }

                    if (nbErreurs == 0)
                    {
                        //AJOUT TYPE ARTICLE
                        TypeArticle typeArticle = new TypeArticle();
                        typeArticle.setLibelle(libelle.getText());
                        typeArticle.setPrix(Double.valueOf(prix.getText()));
                        typeArticle.setQuantiteeEnStock(Integer.valueOf(quantiteStock.getText()));

                        GregorianCalendar date = new GregorianCalendar();
                        date.setTime(dateDebutModel.getDate());
                        if(dateDebutCheckbox.isSelected())
                            typeArticle.setDatePromotionDebut(null);
                        else
                            typeArticle.setDatePromotionDebut(date);

                        date.setTime(dateFinModel.getDate());
                        if(dateFinCheckbox.isSelected())
                            typeArticle.setDatePromotionFin(null);
                        else
                            typeArticle.setDatePromotionFin(date);

                        typeArticle.setEstPerissable(estPerissableTrue.isSelected());

                        if(quantiteMinimal.getText().isEmpty())
                            typeArticle.setQuantiteeMinimal(null);
                        else
                            typeArticle.setQuantiteeMinimal(Integer.valueOf(quantiteMinimal.getText()));

                        typeArticle.setID(listeId.get(id.getSelectedIndex()));

                        applicationController.ajouterTypeArticle(applicationController, typeArticle);
                    }
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBoutons, e.getMessage(), "Erreur lors de l'ajout d'un type d'article", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            libelle.setText(null);
            prix.setText(null);
            quantiteMinimal.setText(null);
            quantiteStock.setText(null);
            estPerissable.clearSelection();
        }
    }

    private class ButtonListenerAjouterLot implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            removeAll();
            validate();
            LotInsertion lotInsertion = new LotInsertion(applicationController);
            add(lotInsertion);
            revalidate();
            repaint();
        }
    }
}
