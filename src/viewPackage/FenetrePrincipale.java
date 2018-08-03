package viewPackage;

import controllerPackage.ApplicationController;
import exceptionsPackage.ExceptionsBD;
import modelPackage.OrdrePreparation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame {
    private Container container;
    private MessageAccueil message;
    private JMenuBar menuBar;
    private JMenu applicationMenu, fonctionnalitesMenu, rechercheMenu;
    private JMenuItem quitter, insertion, modification, recette, listing;
    private JMenuItem rechercheArticlePerime, rechercheArticleInfos, rechercheArticleLocalite;
    private InsertionOrdrePrepa insertionOrdrePrepa;
    private ModificationOrdrePrepa modificationOrdrePrepa;
    private RechercheArticlePerime rechercheArtPerim;
    private RechercheTypeArticle rechercheTypeArticle;
    private RechercheArticleLocalite rechercheArticleLocal;
    private ListingOrdrePrepa listingOrdrePrepa;
    private RecetteInsertion recetteInsertion;
    private ApplicationController applicationController;
    private OrdrePreparation ordrePreparation;

    public FenetrePrincipale()
    {
        super("Welcome !");
        applicationController = new ApplicationController();
        ordrePreparation = new OrdrePreparation();
        setBounds(500, 100, 600, 600);

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing (WindowEvent e)
            {
                try
                {
                    applicationController.fermetureConnection();
                    System.exit(0);
                }
                catch (ExceptionsBD ebd)
                {
                    JOptionPane.showMessageDialog(FenetrePrincipale.this, ebd.getMessage(), "Erreur d'accès ", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /////////////////////////////AJOUT DU MESSAGE D'ACCUEIL/////////////////////////////
        message = new MessageAccueil();
        container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(message, BorderLayout.CENTER);
        ////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////BARRE D'OPTIONS MENU////////////////////////////////
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //AJOUT D'APPLICATION DANS LE MENU
        applicationMenu = new JMenu("Application");
        //RACCOURCI ALT + A
        applicationMenu.setMnemonic('a');

        menuBar.add(applicationMenu);

        //AJOUT DE QUITTER DANS LE MENU DANS APPLICATION
        quitter = new JMenuItem("Quitter");
        //RACCOURCI CTRL + Q
        quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        ExitListener exitListener = new ExitListener();
        quitter.addActionListener(exitListener);
        applicationMenu.add(quitter);

        //AJOUT DES FONCTIONNALITES DANS LE MENU
        fonctionnalitesMenu = new JMenu("Fonctionnalités");
        //RACCOURCI ALT + F
        fonctionnalitesMenu.setMnemonic('f');
        menuBar.add(fonctionnalitesMenu);

        //AJOUT D'UNE INSERTION DANS FONCTIONNALITES
        insertion = new JMenuItem("Insertion");
        insertion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        fonctionnalitesMenu.add(insertion);
        insertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                insertionOrdrePrepa = new InsertionOrdrePrepa(applicationController, ordrePreparation);
                container.add(insertionOrdrePrepa);
                setVisible(true);
            }
        });

        //AJOUT D'UNE MODIFICATION DANS FONCTIONNALITES
        modification = new JMenuItem("Modification");
        modification.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        fonctionnalitesMenu.add(modification);
        modification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                modificationOrdrePrepa = new ModificationOrdrePrepa(applicationController, ordrePreparation);
                container.add(modificationOrdrePrepa);
                setVisible(true);
            }
        });

        //AJOUT D'UN LISING DANS FONCTIONNALITES
        listing = new JMenuItem("Listing");
        listing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        fonctionnalitesMenu.add(listing);
        listing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                listingOrdrePrepa = new ListingOrdrePrepa(applicationController, ordrePreparation);
                container.add(listingOrdrePrepa);
                setVisible(true);
            }
        });

        //AJOUT D'UNE OPTION POUR UNE NOUVELLE RECETTE
        recette = new JMenuItem("Nouvelle recette");
        recette.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        fonctionnalitesMenu.add(recette);
        recette.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                recetteInsertion = new RecetteInsertion();
                container.add(recetteInsertion);
                setVisible(true);
            }
        });

        //AJOUT DE RECHERCHE DANS LE MENU
        rechercheMenu = new JMenu("Recherches");
        //RACCOURCI ALT + R
        rechercheMenu.setMnemonic('r');
        menuBar.add(rechercheMenu);


        //RECHERCHE ARTICLE PERIME ENTRE 2 DATES
        rechercheArticlePerime = new JMenuItem("Articles périmé");
        rechercheArticlePerime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        rechercheMenu.add(rechercheArticlePerime);
        rechercheArticlePerime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                rechercheArtPerim = new RechercheArticlePerime(applicationController);
                container.add(rechercheArtPerim);
                setVisible(true);
            }
        });

        //RECHERCHE INFORMATIONS SUR UN ARTICLE DONNE
        rechercheArticleInfos = new JMenuItem("Type article");
        rechercheArticleInfos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.SHIFT_MASK));
        rechercheMenu.add(rechercheArticleInfos);
        rechercheArticleInfos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                rechercheTypeArticle = new RechercheTypeArticle(applicationController);
                container.add(rechercheTypeArticle);
                setVisible(true);
            }
        });

        //RECHERCHE INFOS SUR UN ARTICLE DONNE
        rechercheArticleLocalite = new JMenuItem("Localité article");
        rechercheArticleLocalite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.SHIFT_MASK));
        rechercheMenu.add(rechercheArticleLocalite);
        rechercheArticleLocalite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.removeAll();
                rechercheArticleLocal = new RechercheArticleLocalite(applicationController);
                container.add(rechercheArticleLocal);
                setVisible(true);
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////

        setVisible(true);
    }

    private class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            try
            {
                applicationController.fermetureConnection();
                System.exit(0);
            }
            catch (ExceptionsBD ebd)
            {
                JOptionPane.showMessageDialog(FenetrePrincipale.this, ebd.getMessage(), "Erreur d'accès ", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
