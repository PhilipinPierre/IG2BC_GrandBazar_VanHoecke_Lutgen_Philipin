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
    private ArrayList<Fournisseur> listeFournisseur;

    public RechercheArticleLocalite(ApplicationController applicationController)
    {
        try
        {
            this.applicationController = applicationController;

            setLayout(new BorderLayout());

            panneauRecherche = new JPanel();

            //RECHERCHE Fournisseur
            typeFournisseurLabel = new JLabel("Recherche d'article par localité du fournisseur : ");
            //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
            typeFournisseurLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panneauRecherche.add(typeFournisseurLabel);

            listeFournisseur = applicationController.getAllFournisseur();
            ArrayList<String> valuesLocalite = new ArrayList<>();
            String localiteCodePostal;
            Boolean estPresent = false;
            for(Fournisseur f : listeFournisseur)
            {
                for(int i = 0; i < valuesLocalite.size(); i++)
                {
                    localiteCodePostal = f.getLocalite() + " " + f.getCodePostal();
                    if(valuesLocalite.get(i).equals(localiteCodePostal))
                        estPresent = true;
                }
                if(!estPresent)
                    valuesLocalite.add(f.getLocalite() + " " + f.getCodePostal());
                estPresent = false;
            }

            localite = new JComboBox(valuesLocalite.toArray(new String[0]));
            localite.setEnabled(true);
            panneauRecherche.add(localite);

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
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur d'accès à la base de données (lot)", JOptionPane.ERROR_MESSAGE);
        }

    }

    private class ButtonListenerValidation implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                String localiteCodePostal = localite.getSelectedItem().toString();
                //SEPARER LA LOCALITE DU CODE POSTAL
                String [] loc = localiteCodePostal.split(" ");
                String localiteLot = loc[0];

                removeAll();
                validate();
                PanelRechercheArticleLocalite panelRechercheArticleLocalite = new PanelRechercheArticleLocalite(applicationController, applicationController.rechercheLotViaLocaliteFournisseur(localiteLot));
                add(panelRechercheArticleLocalite, BorderLayout.CENTER);
                revalidate();
                repaint();

            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauRecherche,"Erreur d'accès lors de la recherche des lots par localité");
            }

        }
    }
}
