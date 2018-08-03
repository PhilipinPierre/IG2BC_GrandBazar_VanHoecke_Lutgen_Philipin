package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RechercheArticleLocalite extends JPanel{
    private JPanel panneauRecherche;
    private JPanel panneauBouton;
    private JLabel typeFournisseurLabel;
    private JComboBox localite;
    private JButton validation;
    private ApplicationController applicationController;
    private ArrayList<Lot> listeLot;
    private ArrayList<Fournisseur> listeFournisseur;
    private ArrayList<String> valueFournisseur;

    public RechercheArticleLocalite(ApplicationController applicationController)
    {
        try
        {
            this.applicationController = applicationController;

            panneauRecherche = new JPanel();

            panneauRecherche.setLayout(new GridLayout(2, 2, 5, 5));

            //RECHERCHE Fournisseur
            typeFournisseurLabel = new JLabel("Localité : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeFournisseurLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauRecherche.add(typeFournisseurLabel);


            listeFournisseur = applicationController.getAllFournisseur();
            ArrayList<String> valuesFournisseur = new ArrayList<>();
            valuesFournisseur = new ArrayList<>();
            for(Fournisseur f : listeFournisseur)
            {
                valuesFournisseur.add(f.getLocalite());
            }

            localite = new JComboBox(valuesFournisseur.toArray(new String[0]));
            localite.setEnabled(true);   // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauRecherche.add(localite);

            //BOUTONS
            panneauBouton = new JPanel();

            validation = new JButton("Rechercher");
            panneauBouton.add(validation);
            ButtonListenerValidation listenerValidation = new ButtonListenerValidation();
            validation.addActionListener(listenerValidation);


            add(panneauRecherche, BorderLayout.CENTER);
            add(panneauBouton, BorderLayout.SOUTH);

            setVisible(true);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
        }

    }

    private class ButtonListenerValidation implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                String localiteLot;
                localiteLot = localite.getSelectedItem().toString();

                removeAll();
                validate();
                PanelRechercheArticleLocalite panelRechercheArticleLocalite = new PanelRechercheArticleLocalite(applicationController, applicationController.RechercheLotViaLocaliteFournisseur(localiteLot));
                add(panelRechercheArticleLocalite, BorderLayout.CENTER);
                revalidate();
                repaint();

            }
            catch (Exception e)
            {
                JOptionPane.showInputDialog("Erreur d'accès lors de la recherche des lots par localité", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
