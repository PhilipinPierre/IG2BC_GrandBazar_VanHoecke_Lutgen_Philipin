package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Cuisinier;
import modelPackage.Fournisseur;
import modelPackage.MembreDuPersonnel;
import modelPackage.TypeArticle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LotInsertion extends JPanel {
    private JPanel panneauInsertion;
    private JPanel panneauBoutons;
    //POUR LE FORMULAIRE
    private JLabel datePeremptionLabel, quantiteLabel, codeLotLabel, dateFourniturePrevueLabel, dateCommandeLabel,
                fournisseurLabel, typeArticleLabel, membreDuPersonnelLabel;
    private JTextField quantite, codeLot;
    private JComboBox fournisseur, typeArticle, membreDuPersonnel;
    private SpinnerDateModel datePeremptionModel, dateFourniturePrevueModel, dateCommandeModel;
    private JSpinner datePeremptionSpinner, dateFourniturePrevueSpinner, dateCommandeSpinner;
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

            panneauInsertion.setLayout(new GridLayout(9, 2, 5, 5));

            //LOT
            //DATE PEREMPTION
            datePeremptionLabel = new JLabel("Date de péremption : ");
            datePeremptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(datePeremptionLabel);
            datePeremptionModel = new SpinnerDateModel();
            datePeremptionSpinner = new JSpinner(datePeremptionModel);
            panneauInsertion.add(datePeremptionSpinner);

            //QUANTITE
            quantiteLabel = new JLabel("Quantité : ");
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

            //DATE COMMANDE
            dateCommandeLabel = new JLabel("Début de commande : ");
            dateCommandeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauInsertion.add(dateCommandeLabel);
            dateCommandeModel = new SpinnerDateModel();
            dateCommandeSpinner = new JSpinner(dateCommandeModel);
            panneauInsertion.add(dateCommandeSpinner);

            //FOURNISSEUR
            fournisseurLabel = new JLabel("Fournisseur");
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
            typeArticleLabel = new JLabel("Type article : ");
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
            membreDuPersonnelLabel = new JLabel("Memmbre du personnel : ");
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

            retour = new JButton("Retour");
            panneauBoutons.add(retour);
            LotInsertion.ButtonListenerRetour listenerRetour = new LotInsertion.ButtonListenerRetour();
            retour.addActionListener(listenerRetour);
            ajoutLot = new JButton("Ajouter lot");
            panneauBoutons.add(ajoutLot);
            LotInsertion.ButtonListenerAjouterLot listenerAjouterLot = new LotInsertion.ButtonListenerAjouterLot();
            ajoutLot.addActionListener(listenerAjouterLot);
            reinitialiser = new JButton("Réinitialiser");
            panneauBoutons.add(reinitialiser);
            LotInsertion.ButtonListenerReinitialiser listenerReinitialiser = new LotInsertion.ButtonListenerReinitialiser();
            reinitialiser.addActionListener(listenerReinitialiser);

            add(panneauInsertion, BorderLayout.NORTH);

            ajoutFournisseur = new JButton("Ajouter fournisseur");
            add(ajoutFournisseur);
            LotInsertion.ButtonListenerAjouterFournisseur listenerAjouterFournisseur = new LotInsertion.ButtonListenerAjouterFournisseur();
            ajoutFournisseur.addActionListener(listenerAjouterFournisseur);

            add(panneauBoutons, BorderLayout.SOUTH);

            setEnabled(true);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur lors de l'ajout d'un lot", JOptionPane.ERROR_MESSAGE);
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

    private class ButtonListenerAjouterLot implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                ;
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBoutons, e.getMessage(), "Erreur lors de l'ajout d'un lot", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            datePeremptionSpinner = new JSpinner(datePeremptionModel);
            quantite.setText(null);
            codeLot.setText(null);
            dateFourniturePrevueSpinner = new JSpinner(dateFourniturePrevueModel);
            dateCommandeSpinner = new JSpinner(dateCommandeModel);
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
