package viewPackage;

import javax.swing.*;
import java.awt.*;

public class ModificationOrdrePrepa extends JPanel {
    private JPanel panneauInsertion;
    //POUR LE FORMULAIRE
    private JLabel dateCreationLabel, numeroSequentielLabel, quantitePrevueLabel, quantiteProduiteLabel;
    private JLabel dateVenteLabel, datePrepaLabel, remarqueLabel, urgentLabel, nomRecetteLabel;
    private JLabel codeBarreLabel, matriculeCuisinierLabel, matriculeResponsableLabel;
    private JTextField dateCreation, numeroSequentiel, quantitePrevu, quantiteProduite;
    private JTextField dateVente, datePrepa, remarque, urgent, nomRecette;
    private JTextField codeBarre, matriculeCuisinier, matriculeResponsable;


    public ModificationOrdrePrepa() {
        //FORMULAIRE D'ENCODAGE
        panneauInsertion = new JPanel();

        panneauInsertion.setLayout(new GridLayout(12, 2, 10, 10));

        //DATE DE CREATION DE L'ORDRE DE PREPA OBLIGATOIRE
        dateCreationLabel = new JLabel("Date : ");
        //ALIGNEMENT A DROITE DU JLABEL PAR DEFAUT A GAUCHE
        dateCreationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(dateCreationLabel);
        dateCreation = new JTextField();
        //BULLES D'AIDE
        dateCreation.setToolTipText("Date de la création de l'ordre de préparation");
        panneauInsertion.add(dateCreation);

        //NUMERO SEQUENTIEL AUTO INCREMENTE OBLIGATOIRE
        numeroSequentielLabel = new JLabel("Numéro Séquentiel : ");
        numeroSequentielLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(numeroSequentielLabel);
        numeroSequentiel = new JTextField();
        panneauInsertion.add(numeroSequentiel);

        //QUANTITE PREVUE A LA CREATION DE L'ORDRE OBLIGATOIRE
        quantitePrevueLabel = new JLabel("Quantité prévue : ");
        quantitePrevueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(quantitePrevueLabel);
        quantitePrevu = new JTextField();
        panneauInsertion.add(quantitePrevu);

        //QUANTITE PRODUITE
        quantiteProduiteLabel = new JLabel("Quantité produite : ");
        quantiteProduiteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(quantiteProduiteLabel);
        quantiteProduite = new JTextField();
        panneauInsertion.add(quantiteProduite);

        //DATE DE VENTE
        dateVenteLabel = new JLabel("Date de vente : ");
        dateVenteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(dateVenteLabel);
        dateVente = new JTextField();
        panneauInsertion.add(dateVente);

        //DATE DE PREPARATION
        datePrepaLabel = new JLabel("Date de préparation : ");
        datePrepaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(datePrepaLabel);
        datePrepa = new JTextField();
        panneauInsertion.add(datePrepa);

        //REMARQUE
        remarqueLabel = new JLabel("Remarque : ");
        remarqueLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(remarqueLabel);
        remarque = new JTextField();
        panneauInsertion.add(remarque);

        //URGENT ? OBLIGATOIRE
        urgentLabel = new JLabel("Urgent ? ");
        urgentLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(urgentLabel);
        urgent = new JTextField();
        panneauInsertion.add(urgent);

        //NOM DE LA RECETTE OBLIGATOIRE (FK RECETTE)
        nomRecetteLabel = new JLabel("Nom de la recette : ");
        nomRecetteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(nomRecetteLabel);
        nomRecette = new JTextField();
        panneauInsertion.add(nomRecette);

        //CODE BARRE (FK TYPEARTICLE)
        codeBarreLabel = new JLabel("Code Barre : ");
        codeBarreLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(codeBarreLabel);
        codeBarre = new JTextField();
        panneauInsertion.add(codeBarre);

        //MATRICULE CUISINE (FK CUISINIER)
        matriculeCuisinierLabel = new JLabel("Matricule cuisinier : ");
        matriculeCuisinierLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(matriculeCuisinierLabel);
        matriculeCuisinier = new JTextField();
        panneauInsertion.add(matriculeCuisinier);

        //MATRICULE RESPONSABLE OBLIGATOIRE (FK RESPONSABLE VENTE)
        matriculeResponsableLabel = new JLabel("Matricule responsable vente : ");
        matriculeResponsableLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panneauInsertion.add(matriculeResponsableLabel);
        matriculeResponsable = new JTextField();
        panneauInsertion.add(matriculeResponsable);

        add(panneauInsertion, BorderLayout.CENTER);

    }
}
