package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RechercheArticlePerime extends JPanel {
    private JPanel panneauRecherche;
    private JPanel panneauBouton;

    private JLabel dateDebutLabel, dateFinLabel;
    private JSpinner dateDebut, dateFin;

    private JButton validation;

    public RechercheArticlePerime()
    {
        //ARTICLE PERIME ENTRE DEUX DATES
        panneauRecherche = new JPanel();

        //DEBUT
        dateDebutLabel = new JLabel("Date début : ");
        //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
        dateDebutLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauRecherche.add(dateDebutLabel);
        dateDebut = new JSpinner();
        dateDebut.setModel(new SpinnerDateModel());
        //BULLES D'AIDE
        panneauRecherche.add(dateDebut);

        //FIN
        dateFinLabel = new JLabel("Date début : ");
        //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
        dateFinLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauRecherche.add(dateFinLabel);
        dateFin = new JSpinner();
        dateFin.setModel(new SpinnerDateModel());
        //BULLES D'AIDE
        panneauRecherche.add(dateFin);

        //BOUTONS
        panneauBouton = new JPanel();

        validation = new JButton("Validation");
        panneauBouton.add(validation);
        ButtonListenerValidation listenerValidation = new ButtonListenerValidation();
        validation.addActionListener(listenerValidation);


        add(panneauRecherche, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class ButtonListenerValidation implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            /**/
        }
    }
}
