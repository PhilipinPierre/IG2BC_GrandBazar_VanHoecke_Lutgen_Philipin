package viewPackage;

import controllerPackage.ApplicationController;
import modelPackage.Recette;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelRecette extends JPanel {
    private JPanel panneauBoutons;
    private JTable table;
    private JLabel titre;
    private ListSelectionModel listSelect;
    private ApplicationController applicationController;
    private ModeleRecette modeleRecette;
    private JButton calculDlc;

    public PanelRecette(ApplicationController applicationController, ArrayList<Recette> recette)
    {
        this.applicationController = applicationController;
        setLayout(new BorderLayout());
        titre = new JLabel("Résultat recherche client");
        add(titre, BorderLayout.NORTH);
        modeleRecette = new ModeleRecette(recette);
        table = new JTable(modeleRecette);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane defilant = new JScrollPane(table);
        add(defilant, BorderLayout.CENTER);

        panneauBoutons = new JPanel();

        panneauBoutons.setLayout(new FlowLayout());

        calculDlc = new JButton("Nombre total de dlc");
        panneauBoutons.add(calculDlc);
        PanelRecette.ButtonListenerCalculDlc listenerCalculDlc = new PanelRecette.ButtonListenerCalculDlc();
        calculDlc.addActionListener(listenerCalculDlc);

        add(panneauBoutons, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class ButtonListenerCalculDlc implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {

        }
    }
}
