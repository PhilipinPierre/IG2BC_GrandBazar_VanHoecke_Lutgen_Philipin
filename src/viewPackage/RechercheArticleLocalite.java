package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.Fournisseur;
import modelPackage.Lot;
import modelPackage.OrdrePreparation;
import modelPackage.TypeArticle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RechercheArticleLocalite extends JPanel{
    private JPanel panneauRecherche;
    private JPanel panneauBouton;
    private JLabel typeFournisseurLabel;
    private JComboBox codeBarre;
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

            //RECHERCHE FOurnisseur
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

            codeBarre = new JComboBox(valuesFournisseur.toArray(new String[0]));
            codeBarre.setEnabled(true);   // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauRecherche.add(codeBarre);

            //BOUTONS
            panneauBouton = new JPanel();

            validation = new JButton("Validation");
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
                listeLot = applicationController.RechercheLotViaLocaliteFournisseur(valueFournisseur.get(codeBarre.getSelectedIndex()));

                Object [][] data = new Object[listeLot.size()][4];
                int i = 0;
                for(Lot lot : listeLot){
                    data[0][i] = lot.getNumeroTVA();
                    data[1][i] = lot.getQuantite();
                    data[2][i] = "";
                    data[3][i] = lot.getCodeBarre().getCodeBarre();
                }

                String titre[] = {"Numéro de tva du fournisseur", "Quantité du lot",  "Catégorie d'article", "Code barre d'article"};
                //JTable tableau = new JTable(data, titre);
                //add(tableau);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBouton, e.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
