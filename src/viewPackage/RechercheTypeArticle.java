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

            setLayout(new BorderLayout());

            panneauRecherche = new JPanel();

            //RECHERCHE TYPE ARTICLE
            typeArticleLabel = new JLabel("Information sur le type article : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeArticleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panneauRecherche.add(typeArticleLabel);
            listeTypeArticle = applicationController.getAllTypeArticle();
            ArrayList <String> valuesTypeArticle = new ArrayList<>();
            for(TypeArticle t : listeTypeArticle)
            {
                valuesTypeArticle.add(t.getLibelle());
            }
            libelle = new JComboBox(valuesTypeArticle.toArray(new String[0]));
            libelle.setEnabled(true);
            panneauRecherche.add(libelle);

            //BOUTONS
            panneauBouton = new JPanel();

            validation = new JButton("Rechercher");
            panneauBouton.add(validation);
            ButtonListenerValidation listenerValidation = new ButtonListenerValidation();
            validation.addActionListener(listenerValidation);


            add(panneauRecherche, BorderLayout.NORTH);
            add(panneauBouton, BorderLayout.CENTER);

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
                removeAll();
                validate();
                PanelRechercheTypeArticle panelRechercheTypeArticle = new PanelRechercheTypeArticle(applicationController, applicationController.rechercheLotViaTypeArticle(libelle.getSelectedItem().toString()));
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
