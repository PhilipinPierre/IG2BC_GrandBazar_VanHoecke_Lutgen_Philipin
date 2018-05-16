package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.Lot;
import modelPackage.OrdrePreparation;
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
    private JComboBox codeBarre;
    private JButton validation;
    private ApplicationController applicationController;
    private ArrayList<TypeArticle> listeTypeArticle;
    private ArrayList<Integer> valuesTypeArticle;

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
            valuesTypeArticle = new ArrayList<>();
            for(TypeArticle t : listeTypeArticle)
            {
                valuesTypeArticle.add(t.getCodeBarre());
            }
            codeBarre = new JComboBox(valuesTypeArticle.toArray(new Integer[0]));
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
                ArrayList<Lot> lot;
                lot = applicationController.RechercheLotViaTypeArticle(valuesTypeArticle.get(codeBarre.getSelectedIndex()));
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBouton, e.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
