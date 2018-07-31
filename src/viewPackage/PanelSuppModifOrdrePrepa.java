package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import modelPackage.*;
import controllerPackage.*;

public class PanelSuppModifOrdrePrepa extends JPanel {
    private JTable table;
    private JLabel titre;
    private ListSelectionModel listSelect;
    private ApplicationController applicationController;
    private ModeleSuppModifOrdrePrepa modeleSuppModifOrdrePrepa;

    public PanelSuppModifOrdrePrepa(ApplicationController applicationController, ArrayList<OrdrePreparation> op)
    {
        this.applicationController = applicationController;
        setLayout(new BorderLayout());
        titre = new JLabel("Résultat du listing");
        add(titre, BorderLayout.NORTH);
        modeleSuppModifOrdrePrepa = new ModeleSuppModifOrdrePrepa(op);
        table = new JTable(modeleSuppModifOrdrePrepa);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane defilant = new JScrollPane(table);
        add(defilant, BorderLayout.CENTER);
        setVisible(true);
    }
}
