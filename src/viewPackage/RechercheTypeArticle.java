package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.TypeArticle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RechercheTypeArticle extends JPanel{
    private JPanel panneauRecherche;
    private JPanel panneauBouton;
    private JLabel typeArticleLabel;
    private JComboBox libelle;
    private JButton validation;
    private ApplicationController applicationController;
    private ArrayList<TypeArticle> listeTypeArticle;

    public RechercheTypeArticle(ApplicationController applicationController)
    {
        try
        {
            this.applicationController = applicationController;

            panneauRecherche = new JPanel();

            panneauRecherche.setLayout(new GridLayout(2, 2, 5, 5));

            //RECHERCHE TYPE ARTICLE
            typeArticleLabel = new JLabel("Type article : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeArticleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauRecherche.add(typeArticleLabel);
            listeTypeArticle = applicationController.getAllTypeArticle();
            ArrayList <String> valuesTypeArticle = new ArrayList<>();
            for(TypeArticle t : listeTypeArticle)
            {
                valuesTypeArticle.add(t.getLibelle());
            }
            libelle = new JComboBox(valuesTypeArticle.toArray(new String[0]));
            libelle.setEnabled(true);   // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauRecherche.add(libelle);

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
                /*ArrayList<Lot> listeLot;
                listeLot = applicationController.RechercheLotViaTypeArticle(libelle.getSelectedIndex());

                Object[][] data = new Object[listeLot.size()][3];
                int i = 0;
                for(Lot lot : listeLot){
                    data[0][i] = lot.getNumeroTVA().getNumeroTVA();
                    data[1][i] = lot.getMatricule().getMatricule();
                    data[2][i] = lot.getId();
                    i++;
                }
                String titre[] = {"Numéro de TVA du fournisseur", "Matricule du membre du personnel", "Identifiant du lot"};
                JTable tableau = new JTable(data, titre);
                add(tableau);*/


                removeAll();
                validate();
                PanelRechercheTypeArticle panelRechercheTypeArticle = new PanelRechercheTypeArticle(applicationController, applicationController.RechercheLotViaTypeArticle(libelle.getSelectedItem().toString()));
                add(panelRechercheTypeArticle, BorderLayout.CENTER);
                revalidate();
                repaint();

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBouton, e.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
