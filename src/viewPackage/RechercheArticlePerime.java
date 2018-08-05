package viewPackage;

import controllerPackage.ApplicationController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class RechercheArticlePerime extends JPanel {
    private JPanel panneauRecherche;
    private JPanel panneauBouton;
    private JLabel dateDebutLabel, dateFinLabel;
    private JSpinner dateDebut, dateFin;
    private SpinnerDateModel dateDebutModel, dateFinModel;
    private JButton validation;
    private ApplicationController applicationController;

    public RechercheArticlePerime(ApplicationController applicationController)
    {
        try
        {
            this.applicationController = applicationController;

            setLayout(new BorderLayout());

            //ARTICLE PERIME ENTRE DEUX DATES
            panneauRecherche = new JPanel();

            //DEBUT
            dateDebutLabel = new JLabel("Date début : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            dateDebutLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauRecherche.add(dateDebutLabel);
            dateDebutModel = new SpinnerDateModel();
            dateDebut = new JSpinner(dateDebutModel);
            //BULLES D'AIDE
            panneauRecherche.add(dateDebut);

            //FIN
            dateFinLabel = new JLabel("Date fin : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            dateFinLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            panneauRecherche.add(dateFinLabel);
            dateFinModel = new SpinnerDateModel();
            dateFin = new JSpinner(dateFinModel);
            //BULLES D'AIDE
            panneauRecherche.add(dateFin);

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
                GregorianCalendar dateD = new GregorianCalendar();
                dateD.setTime(dateDebutModel.getDate());

                GregorianCalendar dateF = new GregorianCalendar();
                dateF.setTime(dateFinModel.getDate());

                if(dateD.before(dateF)) {
                    removeAll();
                    validate();
                    PanelRechercheArticlePerime panelRechercheArticlePerime = new PanelRechercheArticlePerime(applicationController, applicationController.rechercheArticlePerimeEntre2Date(dateD, dateF));
                    add(panelRechercheArticlePerime, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                } else{
                    JOptionPane.showMessageDialog(panneauBouton,"La date de début de recherche doit être inférieur à la date de fin de recherche!");
                }

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBouton, e.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
