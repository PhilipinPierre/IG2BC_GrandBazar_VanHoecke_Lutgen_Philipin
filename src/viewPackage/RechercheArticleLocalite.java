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
    private JLabel typeFournisseur;
    private JComboBox codeBarre;
    private JButton validation;
    private ApplicationController applicationController;
    private ArrayList<Fournisseur> listeFournisseur;

    public RechercheArticleLocalite(ApplicationController applicationController)
    {
        try
        {
            this.applicationController = applicationController;

            panneauRecherche = new JPanel();

            panneauRecherche.setLayout(new GridLayout(2, 2, 5, 5));

            //RECHERCHE TYPE ARTICLE
            typeFournisseur = new JLabel("Type article : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeFournisseur.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauRecherche.add(typeFournisseur);


            listeFournisseur = applicationController.getAllFournisseur();
            ArrayList<String> valuesFournisseur = new ArrayList<>();
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
                ArrayList<Lot> listeLot;

                Object[][] data; /*= new Object[listeLot.size()][3];
                int i = 0;
                for(Lot lot : listeLot){
                    data[0][i] = lot.getNumeroTVA().getNumeroTVA();
                    data[1][i] = lot.getMatricule().getMatricule();
                    data[2][i] = lot.getId();
                    i++;
                }
                */
                String titre[] = {"Numéro de tvé du fournisseur", "Matricule du memebre du personnel", "Identifiant du lot"};
                //JTable tableau = new JTable(data, titre);
                //add(tableau);
            }
            catch (Exception e)
            {

            }

        }
    }
}
