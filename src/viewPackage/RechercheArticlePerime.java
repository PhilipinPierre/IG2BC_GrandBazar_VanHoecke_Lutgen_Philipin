package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.ArticlePerime;
import modelPackage.OrdrePreparation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RechercheArticlePerime extends JPanel {
    private JPanel panneauRecherche;
    private JPanel panneauBouton;
    private JLabel dateDebutLabel, dateFinLabel;
    private JSpinner dateDebut, dateFin;
    private SpinnerDateModel dateDebutModel, dateFinModel;
    private JButton validation;
    private ApplicationController applicationController;
    private OrdrePreparation ordrePreparation;
    private JLabel articlePerimesLabel;
    private JLabel articlePerimeTable;

    public RechercheArticlePerime(ApplicationController applicationController, OrdrePreparation ordrePreparation)
    {
        try
        {
            this.applicationController = applicationController;
            this.ordrePreparation = ordrePreparation;
            //ARTICLE PERIME ENTRE DEUX DATES
            panneauRecherche = new JPanel();

            panneauRecherche.setLayout(new GridLayout(2, 2, 5, 5));

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
                GregorianCalendar dateD = new GregorianCalendar();
                dateD.setTime(dateDebutModel.getDate());

                GregorianCalendar dateF = new GregorianCalendar();
                dateF.setTime(dateFinModel.getDate());

                ArrayList<ArticlePerime> articlePerimes;

                articlePerimes = applicationController.RechercheArticlePerimeEntre2Date(dateD, dateF);

                articlePerimesLabel = new JLabel("Article périmé : ");
                articlePerimesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                panneauBouton.add(articlePerimesLabel);
                ArrayList<String> valuesArticlePerime = new ArrayList<>();
                for(ArticlePerime ap : articlePerimes)
                {
                    System.out.println(ap.getQuantiteJetee());
                    valuesArticlePerime.add(ap.getCodeBarre().getLibelle());
                    valuesArticlePerime.add(ap.getMatricule().getNom());
                    valuesArticlePerime.add(ap.getMatricule().getPrenom());
                    valuesArticlePerime.add(ap.getQuantiteJetee().toString());

                    articlePerimeTable = new JLabel(valuesArticlePerime.get(0));
                    articlePerimeTable.setEnabled(true);
                    panneauBouton.add(articlePerimeTable);
                }

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBouton, e.getMessage(), "Erreur d'accès", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
