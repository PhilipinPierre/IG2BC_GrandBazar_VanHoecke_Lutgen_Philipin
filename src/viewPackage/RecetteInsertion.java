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
    private JTextField dlc, descriptif, quantitePortion;
    private JComboBox nomRecette, nomIngredients;
    //POUR LES BOUTONS
    private JButton retour, ajoutRecette, reinitialiser, ajoutIngredients;
    private ApplicationController applicationController;

    private ArrayList<Recette> listeRecette;
    private ArrayList<TypeArticle> listeIngredients;

    public RecetteInsertion(ApplicationController applicationController)
    {
        try
        {
            this.applicationController = applicationController;

            //FORMULAIRE
            panneauInsertion = new JPanel();

            panneauInsertion.setLayout(new GridLayout(6, 2, 5, 5));

            //NOUVELLE RECETTE
            /*recetteLabel = new JLabel("Nouvelle recette ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            recetteLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panneauInsertion.add(recetteLabel);*/

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
            nomRecette.setEnabled(true);
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

            //LISTE DES INGREDIENTS
            /*ingredientLabel = new JLabel("Liste des ingrédients ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            ingredientLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panneauInsertion.add(ingredientLabel);*/

            //NOM INGREDIENTS
            nomIngredientLabel = new JLabel("Nom ingrédients : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            nomIngredientLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(nomIngredientLabel);
            listeIngredients = applicationController.getAllTypeArticle();
            ArrayList <String> valuesIngredients = new ArrayList<>();
            for(TypeArticle t : listeIngredients)
            {
                valuesIngredients.add(t.getLibelle());
            }
            nomIngredients = new JComboBox(valuesIngredients.toArray(new String[0]));
            nomIngredients.setEnabled(true);
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

            retour = new JButton("Retour");
            panneauBoutons.add(retour);
            RecetteInsertion.ButtonListenerRetour listenerRetour = new RecetteInsertion.ButtonListenerRetour();
            retour.addActionListener(listenerRetour);
            ajoutRecette = new JButton("Ajouter recette");
            panneauBoutons.add(ajoutRecette);
            RecetteInsertion.ButtonListenerAjouterRecette buttonListenerAjouterRecette = new RecetteInsertion.ButtonListenerAjouterRecette();
            ajoutRecette.addActionListener(buttonListenerAjouterRecette);
            reinitialiser = new JButton("Réinitialiser");
            panneauBoutons.add(reinitialiser);
            RecetteInsertion.ButtonListenerReinitialiser listenerReinitialiser = new RecetteInsertion.ButtonListenerReinitialiser();
            reinitialiser.addActionListener(listenerReinitialiser);

            add(panneauInsertion);

            ajoutIngredients = new JButton("Ajouter ingrédients");
            add(ajoutIngredients);
            RecetteInsertion.ButtonListenerAjouterIngredients listenerAjouterIngredients = new RecetteInsertion.ButtonListenerAjouterIngredients();
            ajoutIngredients.addActionListener(listenerAjouterIngredients);

            add(panneauBoutons);

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

            add(new MessageAccueil(), BorderLayout.CENTER);

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
            dlc.setText(null);
            descriptif.setText(null);
            quantitePortion.setText(null);
        }
    }

    private class ButtonListenerAjouterIngredients implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            ;
        }
    }
}