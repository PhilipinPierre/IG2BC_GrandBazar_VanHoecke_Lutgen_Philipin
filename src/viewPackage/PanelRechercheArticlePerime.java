package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.ArticlePerime;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelRechercheArticlePerime extends JPanel {
    private JTable table;
    private JLabel titre;
    private ListSelectionModel listSelect;
    private ApplicationController applicationController;
    private ModeleRechercheArticlePerime modeleRechercheArticlePerime;

    public PanelRechercheArticlePerime(ApplicationController applicationController, ArrayList<ArticlePerime> articlePerime)
    {
        this.applicationController = applicationController;
        setLayout(new BorderLayout());
        titre = new JLabel("Résultat recherche client");
        add(titre, BorderLayout.NORTH);
        modeleRechercheArticlePerime = new ModeleRechercheArticlePerime(articlePerime);
        table = new JTable(modeleRechercheArticlePerime);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane defilant = new JScrollPane(table);
        add(defilant, BorderLayout.CENTER);
        setVisible(true);
    }
}