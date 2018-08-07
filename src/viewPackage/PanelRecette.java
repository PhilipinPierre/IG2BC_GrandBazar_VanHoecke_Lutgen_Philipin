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
    private JButton totalDlc, divisionDlc;
    private ArrayList<Recette> listeRecette;

    public PanelRecette(ApplicationController applicationController, ArrayList<Recette> recette)
    {
        this.applicationController = applicationController;
        this.listeRecette = recette;

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

        totalDlc = new JButton("Nombre total de dlc");
        panneauBoutons.add(totalDlc);
        PanelRecette.ButtonListenerTotalDlc listenerCalculDlc = new PanelRecette.ButtonListenerTotalDlc();
        totalDlc.addActionListener(listenerCalculDlc);

        divisionDlc = new JButton("Division dlc max par min");
        panneauBoutons.add(divisionDlc);
        PanelRecette.ButtonListenerDivisionDlc listenerDivisionDlc = new PanelRecette.ButtonListenerDivisionDlc();
        divisionDlc.addActionListener(listenerDivisionDlc);

        add(panneauBoutons, BorderLayout.SOUTH);

        setVisible(true);
    }

    public Integer totalDlc(ArrayList<Recette> listeRecette)
    {
        int total = 0;
        for(Recette r : listeRecette)
        {
            total += r.getDLC();
        }
        return total;
    }

    public Double divisionDlc(ArrayList<Recette> listeRecette)
    {
        double max = 0;
        double min = 2147483647;
        for(Recette r : listeRecette)
        {
            if(max < r.getDLC())
                max = r.getDLC();
            if(min > r.getDLC())
                min = r.getDLC();
        }

        return max / min;
    }

    private class ButtonListenerTotalDlc implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Integer total = totalDlc(listeRecette);
            JOptionPane.showMessageDialog(panneauBoutons, "Le nombre total de dlc est de " + total);
        }
    }

    private class ButtonListenerDivisionDlc implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            JOptionPane.showMessageDialog(panneauBoutons, "Le nombre total de dlc est de " + divisionDlc(listeRecette));
        }
    }
}
