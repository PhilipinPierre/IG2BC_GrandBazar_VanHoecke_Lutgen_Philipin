package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.TypeArticle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IngredientInsertion extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel quantitePortionLabel, nomLabel, typeArticleLabel,
                prixLabel, quantiteStockLabel, dateDebutLabel, dateFinLabel, quantiteMinimalLabel;
    private JTextField nomIngredient, quantitePortion, prix, quantiteStock, quantiteMinimal;
    private JRadioButton estPerissableTrue, estPerissableFalse;
    private ButtonGroup estPerissable;
    private JComboBox libelle;
    private SpinnerDateModel dateDebutModel, dateFinModel;
    private JSpinner dateDebutSpinner, dateFinSpinner;
    //POUR LES BOUTONS
    private JButton retour, ajoutIngredient, reinitialiser, ajoutTypeArticle;
    private ApplicationController applicationController;

    private ArrayList<TypeArticle> listeTypeArticle;

    public IngredientInsertion(ApplicationController applicationController)
    {
        try {
            this.applicationController = applicationController;

            setLayout(new BorderLayout());

            //FORMULAIRE
            panneauInsertion = new JPanel();

            panneauInsertion.setLayout(new GridLayout(10, 2, 5, 5));

            //INGREDIENTS
            //NOM INGREDIENT
            nomLabel = new JLabel("Nom ingrédient : ");
            nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(nomLabel);
            nomIngredient = new JTextField();
            panneauInsertion.add(nomIngredient);

            //CODE BARRE INGREDIENT
            //LIBELLE <- CODE BARRE (FK TYPEARTICLE)
            typeArticleLabel = new JLabel("Type article : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeArticleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(typeArticleLabel);
            listeTypeArticle = applicationController.getAllTypeArticle();
            ArrayList<String> valuesTypeArticle = new ArrayList<>();
            for (TypeArticle t : listeTypeArticle) {
                valuesTypeArticle.add(t.getLibelle());
            }
            libelle = new JComboBox(valuesTypeArticle.toArray(new String[0]));
            libelle.setEnabled(true);
            panneauInsertion.add(libelle);

            //QUANTITE PORTION
            quantitePortionLabel = new JLabel("Quantité portion : ");
            quantitePortionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(quantitePortionLabel);
            quantitePortion = new JTextField();
            panneauInsertion.add(quantitePortion);

            //TYPE ARTICLE
            //PRIX
            prixLabel = new JLabel("Prix : ");
            prixLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(prixLabel);
            prix = new JTextField();
            panneauInsertion.add(prix);

            //QUANTITE EN STOCK
            quantiteStockLabel = new JLabel("Quantité en stock : ");
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

            //DATE PROMOTION FIN
            dateFinLabel = new JLabel("Fin promotion : ");
            dateFinLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateFinLabel);
            dateFinModel = new SpinnerDateModel();
            dateFinSpinner = new JSpinner(dateFinModel);
            panneauInsertion.add(dateFinSpinner);

            //EST PERISSABLE
            estPerissableTrue = new JRadioButton("Périssable", false);
            panneauInsertion.add(estPerissableTrue);
            estPerissableFalse = new JRadioButton("Non périssable", false);
            panneauInsertion.add(estPerissableFalse);
            estPerissable = new ButtonGroup();
            estPerissable.add(estPerissableTrue);
            estPerissable.add(estPerissableFalse);

            //QUANTITE MINIMAL
            quantiteMinimalLabel = new JLabel("Quantité minimale: ");
            quantiteMinimalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(quantiteMinimalLabel);
            quantiteMinimal = new JTextField();
            panneauInsertion.add(quantiteMinimal);

            //BOUTONS
            panneauBoutons = new JPanel();

            panneauBoutons.setLayout(new FlowLayout());

            retour = new JButton("Retour");
            panneauBoutons.add(retour);
            IngredientInsertion.ButtonListenerRetour listenerRetour = new IngredientInsertion.ButtonListenerRetour();
            retour.addActionListener(listenerRetour);
            ajoutIngredient = new JButton("Ajouter ingredient");
            panneauBoutons.add(ajoutIngredient);
            IngredientInsertion.ButtonListenerAjouterIngredient listenerAjouterIngredient = new IngredientInsertion.ButtonListenerAjouterIngredient();
            ajoutIngredient.addActionListener(listenerAjouterIngredient);
            reinitialiser = new JButton("Réinitialiser");
            panneauBoutons.add(reinitialiser);
            IngredientInsertion.ButtonListenerReinitialiser listenerReinitialiser = new IngredientInsertion.ButtonListenerReinitialiser();
            reinitialiser.addActionListener(listenerReinitialiser);

            add(panneauInsertion, BorderLayout.NORTH);

            ajoutTypeArticle = new JButton("Ajouter type d'article");
            add(ajoutTypeArticle, BorderLayout.CENTER);
            IngredientInsertion.ButtonListenerAjouterTypeArticle listenerAjouterTypeArticle = new IngredientInsertion.ButtonListenerAjouterTypeArticle();
            ajoutTypeArticle.addActionListener(listenerAjouterTypeArticle);

            add(panneauBoutons, BorderLayout.SOUTH);

            setVisible(true);
        }
        catch (ExceptionsBD ebd)
        {
            JOptionPane.showMessageDialog(this, ebd.getMessage(), "Erreur lors de l'ajout d'un nouvel ingrédient", JOptionPane.ERROR_MESSAGE);
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

    private class ButtonListenerAjouterIngredient implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                ;
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBoutons, e.getMessage(), "Erreur lors de l'ajout d'une nouvelle recette", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            nomIngredient.setText(null);
            quantitePortion.setText(null);
            prix.setText(null);
            quantiteMinimal.setText(null);
            quantiteStock.setText(null);
            dateDebutSpinner = new JSpinner(dateDebutModel);
            dateFinSpinner = new JSpinner(dateFinModel);
            estPerissable.clearSelection();
        }
    }

    private class ButtonListenerAjouterTypeArticle implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            ;
        }
    }
}
