package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecetteInsertion extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel dlcLabel, nomRecetteLabel, descriptifLabel,
             quantitePortionLabel, nomIngredientLabel;
    private JTextField dlc, descriptif, quantitePortion, nomRecette;
    private JList nomIngredients;
    //POUR LES BOUTONS
    private JButton retour, ajoutRecette, reinitialiser, ajoutIngredients, listingRecette;
    private ApplicationController applicationController;
    private ArrayList<Recette> listeRecette;
    private ArrayList<TypeArticle> listeIngredients;
    private DefaultListModel defaultListModel = new DefaultListModel();

    public RecetteInsertion(ApplicationController applicationController)
    {
        try
        {
            this.applicationController = applicationController;

            setLayout(new BorderLayout());

            //FORMULAIRE
            panneauInsertion = new JPanel();

            panneauInsertion.setLayout(new GridLayout(6, 2, 5, 5));

            //RECETTE
            //NOM DE LA RECETTE OBLIGATOIRE (FK RECETTE)
            nomRecetteLabel = new JLabel("Nom de la recette : ");
            nomRecetteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(nomRecetteLabel);
            nomRecette = new JTextField();
            panneauInsertion.add(nomRecette);

            //DLC
            dlcLabel = new JLabel("Durée limite de consommation : ");
            dlcLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dlcLabel);
            dlc = new JTextField();
            panneauInsertion.add(dlc);

            //DESCRIPTIF
            descriptifLabel = new JLabel("Descriptf : ");
            descriptifLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(descriptifLabel);
            descriptif = new JTextField();
            panneauInsertion.add(descriptif);

            //INGREDIENTS
            //LIBELLE -> CODE BARRE
            nomIngredientLabel = new JLabel("Type article : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            nomIngredientLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(nomIngredientLabel);
            listeIngredients = applicationController.getAllTypeArticle();
            int i = 0;
            for(TypeArticle t : listeIngredients)
            {
                defaultListModel.add(i, t.getLibelle());
                i++;
            }
            nomIngredients = new JList(defaultListModel);
            nomIngredients.setVisibleRowCount(3);
            nomIngredients.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            panneauInsertion.add(nomIngredients);

            //QUANTITE PORTION
            quantitePortionLabel = new JLabel("Quantité portion : ");
            quantitePortionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(quantitePortionLabel);
            quantitePortion = new JTextField();
            panneauInsertion.add(quantitePortion);

            //BOUTONS
            panneauBoutons = new JPanel();

            panneauBoutons.setLayout(new FlowLayout());

            retour = new JButton("<- Retour");
            panneauBoutons.add(retour);
            RecetteInsertion.ButtonListenerRetour listenerRetour = new RecetteInsertion.ButtonListenerRetour();
            retour.addActionListener(listenerRetour);

            listingRecette = new JButton("Listing");
            panneauBoutons.add(listingRecette);
            RecetteInsertion.ButtonListenerListingRecette listenerListingRecette = new RecetteInsertion.ButtonListenerListingRecette();
            listingRecette.addActionListener(listenerListingRecette);

            ajoutRecette = new JButton("Ajouter recette");
            panneauBoutons.add(ajoutRecette);
            RecetteInsertion.ButtonListenerAjouterRecette listenerAjouterRecette = new RecetteInsertion.ButtonListenerAjouterRecette();
            ajoutRecette.addActionListener(listenerAjouterRecette);

            reinitialiser = new JButton("Réinitialiser");
            panneauBoutons.add(reinitialiser);
            RecetteInsertion.ButtonListenerReinitialiser listenerReinitialiser = new RecetteInsertion.ButtonListenerReinitialiser();
            reinitialiser.addActionListener(listenerReinitialiser);

            ajoutIngredients = new JButton("Créer article ->");
            panneauBoutons.add(ajoutIngredients);
            RecetteInsertion.ButtonListenerAjouterIngredients listenerAjouterIngredients = new RecetteInsertion.ButtonListenerAjouterIngredients();
            ajoutIngredients.addActionListener(listenerAjouterIngredients);

            add(panneauInsertion, BorderLayout.NORTH);

            add(panneauBoutons, BorderLayout.SOUTH);

            setVisible(true);
        }
        catch (ExceptionsBD ebd)
        {
            JOptionPane.showMessageDialog(this, ebd.getMessage(), "Erreur lors de l'ajout d'une nouvelle recette", JOptionPane.ERROR_MESSAGE);
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
            add(messageAccueil);

            revalidate();
            repaint();
        }
    }

    private class ButtonListenerAjouterRecette implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                //AJOUT RECETTE
                Recette recette = new Recette();
                recette.setNom(nomRecette.getText());
                recette.setDLC(Integer.valueOf(dlc.getText()));
                recette.setDescriptif(descriptif.getText());
                applicationController.ajouterRecette(applicationController, recette);

                //AJOUT INGREDIENTS
                //POUR LES MULTIPLES INGREDIENTS !!!
                int[] tab = nomIngredients.getSelectedIndices();
                for(int j=0 ; j<=tab.length-1 ; j++)
                {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setNom(recette);
                    ingredient.setCodeBarre(listeIngredients.get(tab[j]));
                    ingredient.setQuantitePortion(Integer.valueOf(quantitePortion.getText()));
                    applicationController.ajouterIngredient(applicationController, ingredient);
                }
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
            dlc.setText(null);
            descriptif.setText(null);
            quantitePortion.setText(null);
        }
    }

    private class ButtonListenerAjouterIngredients implements ActionListener
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

    private class ButtonListenerListingRecette implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try {
                listeRecette = applicationController.getAllRecette();

                removeAll();
                validate();

                PanelRecette panelRecette = new PanelRecette(applicationController, listeRecette);
                add(panelRecette);

                revalidate();
                repaint();
            }
            catch (ExceptionsBD e)
            {
                JOptionPane.showMessageDialog(panneauInsertion, "Erreur lors du listing des recettes");
            }
        }
    }
}