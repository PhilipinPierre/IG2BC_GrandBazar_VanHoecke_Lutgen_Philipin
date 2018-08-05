package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.Fournisseur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FournisseurInsertion extends JPanel {
    private JPanel panneauInsertion, panneauBoutons;
    private ApplicationController applicationController;
    private JLabel numeroTvaLabel, nomLabel, localiteLabel, codePostalLabel, rueLabel;
    private JTextField numeroTva, nom, localite, codePostal, rue;
    private JButton retour, ajoutFournisseur, reinitialiser;


    public FournisseurInsertion(ApplicationController applicationController)
    {
        this.applicationController = applicationController;

        setLayout(new BorderLayout());

        //FORMULAIRE
        panneauInsertion = new JPanel();

        panneauInsertion.setLayout(new GridLayout(6, 2, 5, 5));

        //NUMERO DE TVA
        numeroTvaLabel = new JLabel("Numéro de TVA : ");
        numeroTvaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(numeroTvaLabel);
        numeroTva = new JTextField();
        panneauInsertion.add(numeroTva);

        //NOM
        nomLabel = new JLabel("Nom : ");
        nomLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(nomLabel);
        nom = new JTextField();
        panneauInsertion.add(nom);

        //LOCALITE
        localiteLabel = new JLabel("Localité : ");
        localiteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(localiteLabel);
        localite = new JTextField();
        panneauInsertion.add(localite);

        //CODE POSTAL
        codePostalLabel = new JLabel("Code postal : ");
        codePostalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(codePostalLabel);
        codePostal = new JTextField();
        panneauInsertion.add(codePostal);

        //RUE
        rueLabel = new JLabel("Rue : ");
        rueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(rueLabel);
        rue = new JTextField();
        panneauInsertion.add(rue);

        //BOUTONS
        panneauBoutons = new JPanel();

        panneauBoutons.setLayout(new FlowLayout());

        retour = new JButton("<- Retour");
        panneauBoutons.add(retour);
        FournisseurInsertion.ButtonListenerRetour listenerRetour = new FournisseurInsertion.ButtonListenerRetour();
        retour.addActionListener(listenerRetour);
        ajoutFournisseur = new JButton("Ajouter fournisseur");
        panneauBoutons.add(ajoutFournisseur);
        FournisseurInsertion.ButtonListenerAjouterFournisseur listenerAjouterFournisseur = new FournisseurInsertion.ButtonListenerAjouterFournisseur();
        ajoutFournisseur.addActionListener(listenerAjouterFournisseur);
        reinitialiser = new JButton("Réinitialiser");
        panneauBoutons.add(reinitialiser);
        FournisseurInsertion.ButtonListenerReinitialiser listenerReinitialiser = new FournisseurInsertion.ButtonListenerReinitialiser();
        reinitialiser.addActionListener(listenerReinitialiser);

        add(panneauInsertion, BorderLayout.NORTH);

        add(panneauBoutons, BorderLayout.SOUTH);

        setEnabled(true);
    }

    //CLASSES PRIVEES POUR LES BOUTONS
    private class ButtonListenerRetour implements ActionListener
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

    private class ButtonListenerAjouterFournisseur implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                //AJOUT FOURNISSEUR
                Fournisseur fournisseur = new Fournisseur();

                fournisseur.setNumeroTVA(Integer.valueOf(numeroTva.getText()));
                fournisseur.setNom(nom.getText());
                fournisseur.setLocalite(localite.getText());
                fournisseur.setCodePostal(Integer.valueOf(codePostal.getText()));
                fournisseur.setRue(rue.getText());

                applicationController.ajouterFournisseur(applicationController, fournisseur);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBoutons, e.getMessage(), "Erreur lors de l'ajout d'un nouveau fournisseur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ButtonListenerReinitialiser implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            numeroTva.setText(null);
            nom.setText(null);
            localite.setText(null);
            codePostal.setText(null);
            rue.setText(null);
        }
    }
}
