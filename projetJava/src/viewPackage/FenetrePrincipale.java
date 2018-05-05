package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetrePrincipale extends JFrame {
    private Container frameContainer;
    private MessageAccueil message;
    private JMenuBar menuBar;
    private JMenu applicationMenu, fonctMenu, infosMenu;
    private JMenuItem quitter, insertion, modification, suppression, listing;
    private JMenuItem rechercheArticlePerime, rechercheArticleInfos, rechercheArticleLocalite;
    private InsertionOrdrePrepa insertionOrdrePrepa;
    private ModificationOrdrePrepa modificationOrdrePrepa;

    public FenetrePrincipale()
    {
        super("Welcome !");
        setBounds(500, 100, 600, 600);

        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing (WindowEvent e)
            {
                System.exit(0);
            }
        });

        /////////////////////////////AJOUT DU MESSAGE D'ACCUEIL/////////////////////////////
        message = new MessageAccueil();
        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());
        frameContainer.add(message, BorderLayout.CENTER);
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

        //AJOUT DE FONCTIONNALITES DANS LE MENU
        fonctMenu = new JMenu("Fonctionnalités1");
        fonctMenu.setMnemonic('f');
        menuBar.add(fonctMenu);

        //AJOUT D'UNE INSERTION DANS LE MENU DANS FONCTIONNALITES
        insertion = new JMenuItem("Insertion");
        insertion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        fonctMenu.add(insertion);
        insertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameContainer.removeAll();
                insertionOrdrePrepa = new InsertionOrdrePrepa();
                frameContainer.add(insertionOrdrePrepa);
                setVisible(true);
            }
        });

        //AJOUT D'UNE MODIFICATION DANS LE MENU DANS FONCTIONNALITES
        modification = new JMenuItem("Modification");
        modification.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        fonctMenu.add(modification);
        modification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameContainer.removeAll();
                modificationOrdrePrepa = new ModificationOrdrePrepa();
                frameContainer.add(modificationOrdrePrepa);
                setVisible(true);
            }
        });

        //AJOUT D'UNE SUPPRESSION DANS LE MENU DANS FONCTIONNALITES
        suppression = new JMenuItem("Suppression");
        suppression.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        fonctMenu.add(suppression);

        //AJOUT D'UN LISING DANS LE MENU DANS FONCTIONNALITES
        listing = new JMenuItem("Listing");
        listing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
        fonctMenu.add(listing);

        //RECHERCHE ARTICLE PERIME ENTRE 2 DATES
        rechercheArticlePerime = new JMenuItem("Recherche Périmé");
        rechercheArticlePerime.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        fonctMenu.add(rechercheArticlePerime);

        //RECHERCHE INFOS SUR UN ARTICLE DONNE
        rechercheArticleInfos = new JMenuItem("Recherche Infos");
        rechercheArticleInfos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.SHIFT_MASK));
        fonctMenu.add(rechercheArticleInfos);

        //RECHERCHE INFOS SUR UN ARTICLE DONNE
        rechercheArticleLocalite = new JMenuItem("Recherche Localité");
        rechercheArticleLocalite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.SHIFT_MASK));
        fonctMenu.add(rechercheArticleLocalite);

        ////////////////////////////////////////////////////////////////////////////////////

        setVisible(true);
    }

    private class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.exit(0);
        }
    }

}
