package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import modelPackage.*;
import controllerPackage.*;

public class PanelSuppModifOrdrePrepa extends JPanel {
    private JTable table;
    private JLabel titre;
    private ListSelectionModel listSelect;
    private ApplicationController applicationController;
    private ArrayList<OrdrePreparation> ordrePreparations;
    private ModeleSuppModifOrdrePrepa modeleSuppModifOrdrePrepa;
    private JButton suppression;
    private JPanel panneauJList;
    private JPanel panneauBoutons;
    private ArrayList<Reservation> listeReservation;

    public PanelSuppModifOrdrePrepa(ApplicationController applicationController, ArrayList<OrdrePreparation> op, ArrayList<Reservation> listeReservation)
    {
        this.applicationController = applicationController;
        this.ordrePreparations = op;
        this.listeReservation = listeReservation;
        panneauJList = new JPanel();
        panneauBoutons = new JPanel();
        setLayout(new BorderLayout());
        titre = new JLabel("Résultat du listing");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
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

    private class ButtonListenerSuppression implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int numeroSequentiel = listSelect.getMinSelectionIndex();
            OrdrePreparation ordrePreparation = new OrdrePreparation();
            try
            {
                Integer numSeq = (Integer) table.getValueAt(numeroSequentiel, 0);
                GregorianCalendar date = new GregorianCalendar();

                for(OrdrePreparation o : ordrePreparations)
                {
                    if (numSeq.equals(o.getNumeroSequentiel()))
                        date = o.getDate();
                }

                boolean estReserve = false;
                for(Reservation r : listeReservation)
                {
                    if(numSeq.equals(r.getNumeroSequentiel()) && (date.compareTo(r.getDate())) == 0)
                    {
                        int reponse = JOptionPane.showConfirmDialog(panneauJList, "L'ordre est lié à une réservation. Voulez-vous supprimer la réservation ?",
                                "Réservation détectée ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (reponse == 0)
                        {
                            applicationController.supprimerReservation(numSeq);
                            applicationController.supprimerOrdrePreparation(numSeq);
                            JOptionPane.showMessageDialog(panneauJList, "Ordre supprimé !");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panneauJList, "Ordre non supprimé !");
                        }
                        estReserve = true;
                    }
                }

                if(estReserve == false)
                {
                    applicationController.supprimerOrdrePreparation(numSeq);

                    JOptionPane.showMessageDialog(panneauJList, "Ordre supprimé !");
                }

                removeAll();
                validate();

                add(new ListingOrdrePrepa(applicationController, ordrePreparation));

                revalidate();
                repaint();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(panneauJList, "Aucun ordre de préparation selectionné ! ");
            }
        }
    }

}
