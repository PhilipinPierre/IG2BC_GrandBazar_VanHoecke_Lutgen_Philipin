package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.Lot;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelRechercheTypeArticle extends JPanel{
    private JTable table;
    private JLabel titre;
    private ListSelectionModel listSelect;
    private ApplicationController applicationController;
    private ModeleRechercheTypeArticle modeleRechercheTypeArticle;

    public PanelRechercheTypeArticle(ApplicationController applicationController, ArrayList<Lot> lot)
    {
        this.applicationController = applicationController;
        setLayout(new BorderLayout());
        titre = new JLabel("Résultat recherche client");
        add(titre, BorderLayout.NORTH);
        modeleRechercheTypeArticle = new ModeleRechercheTypeArticle(lot);
        table = new JTable(modeleRechercheTypeArticle);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane defilant = new JScrollPane(table);
        add(defilant, BorderLayout.CENTER);
        setVisible(true);
    }
}