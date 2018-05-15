package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.OrdrePreparation;
import modelPackage.TypeArticle;
import exceptionsPackage.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class rechercheTypeArticle {
    private JPanel panneauRecherche;
    private JPanel panneauBouton;
    private JLabel typeArticleLabel;
    private JButton validation;
    private ApplicationController applicationController;
    private OrdrePreparation ordrePreparation;
    private ArrayList<TypeArticle> listeTypeArticle;

    public rechercheTypeArticle(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;

            panneauRecherche = new JPanel();

            panneauRecherche.setLayout(new GridLayout(2, 2, 5, 5));

            //RECHERCHE TYPE ARTICLE
            typeArticleLabel = new JLabel("Type article : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeArticleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauRecherche.add(typeArticleLabel);


            listeTypeArticle = applicationController.getAllTypeArticle();
            ArrayList<Integer> valuesTypeArticle = new ArrayList<>();
            for(TypeArticle t : listeTypeArticle)
            {
                valuesTypeArticle.add(t.getCodeBarre());
            }
            codeBarre = new JComboBox(valuesTypeArticle.toArray(new Integer[0]));
            codeBarre.setEnabled(true);   // BOOLEAN ESTADMIN !!!!!!!!!!!!!!!!!
            panneauInsertion.add(codeBarre);
            }

            //BOUTONS
            panneauBouton = new JPanel();

            validation = new JButton("Validation");
            panneauBouton.add(validation);
            RechercheArticlePerime listenerValidation = new RechercheArticlePerime();
            validation.addActionListener(listenerValidation);


            add(panneauRecherche, BorderLayout.CENTER);
            add(panneauBouton, BorderLayout.NORTH);

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
            {}
            catch (Exception e)
            {}

        }
    }
}
