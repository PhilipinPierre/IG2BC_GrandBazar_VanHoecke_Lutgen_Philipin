package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelPackage.*;
import controllerPackage.*;

public class PanelSuppModifOrdrePrepa extends JPanel {
    private JTable table;
    private JLabel titre;
    private ListSelectionModel listSelect;
    private ApplicationController applicationController;
    private ArrayList<OrdrePreparation> op;
    private ModeleSuppModifOrdrePrepa modeleSuppModifOrdrePrepa;
    private JButton suppression;
    private JPanel panneauJList;
    private JPanel panneauBoutons;

    public PanelSuppModifOrdrePrepa(ApplicationController applicationController, ArrayList<OrdrePreparation> op)
    {
        this.applicationController = applicationController;
        this.op = op;
        panneauJList = new JPanel();
        panneauBoutons = new JPanel();
        setLayout(new BorderLayout());
        titre = new JLabel("Résultat du listing");
        add(titre, BorderLayout.NORTH);
        modeleSuppModifOrdrePrepa = new ModeleSuppModifOrdrePrepa(op);
        table = new JTable(modeleSuppModifOrdrePrepa);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelect = table.getSelectionModel();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane defilant = new JScrollPane(table);
        panneauJList.add(defilant, BorderLayout.CENTER);

        suppression = new JButton("Suppression");
        panneauBoutons.add(suppression);
        PanelSuppModifOrdrePrepa.ButtonListenerSuppression listenerSuppression = new PanelSuppModifOrdrePrepa.ButtonListenerSuppression();
        suppression.addActionListener(listenerSuppression);

        add(panneauJList, BorderLayout.CENTER);
        add(panneauBoutons, BorderLayout.SOUTH);

        setVisible(true);
    }

    //CLASSES PRIVEES POUR LES BOUTONS
    private class ButtonListenerSuppression implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int numeroSequentiel = listSelect.getMinSelectionIndex();
            OrdrePreparation ordrePreparation = new OrdrePreparation();
            try
            {
                Integer numSeq = (Integer) table.getValueAt(numeroSequentiel, 0);

                applicationController.SupprimerOrdrePreparation(numSeq);
                removeAll();
                validate();

                add(new ListingOrdrePrepa(applicationController, ordrePreparation));

                revalidate();
                repaint();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauBoutons, e.getMessage(), "Erreur lors de la suppression d'un ordre de préparation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
