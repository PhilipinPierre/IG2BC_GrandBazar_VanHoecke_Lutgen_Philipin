package viewPackage;

import modelPackage.*;
import controllerPackage.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class PanelRechercheArticleLocalite extends JPanel {
    private JTable table;
    private JLabel titre;
    private ListSelectionModel listSelect;
    private ApplicationController applicationController;
    private ModeleRechercheArticleLocalite modeleRechercheArticleLocalite;

    public PanelRechercheArticleLocalite(ApplicationController applicationController, ArrayList<Lot> lot)
    {
        this.applicationController = applicationController;
        setLayout(new BorderLayout());
        titre = new JLabel("Résultat recherche client");
        add(titre, BorderLayout.NORTH);
        modeleRechercheArticleLocalite = new ModeleRechercheArticleLocalite(lot);
        table = new JTable(modeleRechercheArticleLocalite);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane defilant = new JScrollPane(table);
        add(defilant, BorderLayout.CENTER);
        setVisible(true);
    }
}