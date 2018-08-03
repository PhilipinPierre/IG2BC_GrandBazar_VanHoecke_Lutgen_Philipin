package viewPackage;

import modelPackage.OrdrePreparation;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class ModeleSuppModifOrdrePrepa extends AbstractTableModel {
    private ArrayList<String> nomColonnes = new ArrayList<>();
    private ArrayList<OrdrePreparation> contenu = new ArrayList<>();

    public ModeleSuppModifOrdrePrepa(ArrayList<OrdrePreparation> op)
    {
        contenu = op;
        nomColonnes.add("Date");
        nomColonnes.add("Numéro Séquentiel");
        nomColonnes.add("Quantité Prévue");
        nomColonnes.add("Quantité Produite");
        nomColonnes.add("Date Vente");
        nomColonnes.add("Date Préparation");
        nomColonnes.add("Remarque");
        nomColonnes.add("Urgent ?");
        nomColonnes.add("Recette");
        nomColonnes.add("Type");
        nomColonnes.add("Cuisinier");
        nomColonnes.add("Responsable Vente");
    }
    public int getColumnCount()
    {
        return nomColonnes.size();
    }

    public int getRowCount()
    {
        return contenu.size();
    }

    public String getColumnName(int col)
    {
        return nomColonnes.get(col);
    }

    public Object getValueAt(int row, int col)
    {
        OrdrePreparation ligneop = contenu.get(row);
        switch(col)
        {
            case 0 : return ligneop.getDate().getTime();
            case 1 : return ligneop.getNumeroSequentiel();
            case 2 : return ligneop.getQuantitePrevue();
            case 3 : return ligneop.getQuantiteProduite();
            case 4 : {if((ligneop).getDateVente() != null)
                        return ligneop.getDateVente().getTime();
                      else
                        return null;
                      }
            case 5 : {if((ligneop).getDatePreparation() != null)
                        return ligneop.getDatePreparation().getTime();
                      else
                        return null;
                      }
            case 6 : {if((ligneop).getRemarque() != null)
                        return ligneop.getRemarque();
                      else
                        return null;
                      }
            case 7 : return ligneop.getEstUrgent();
            case 8 : return ligneop.getNom().getNom();
            case 9 : return ligneop.getCodeBarre().getCodeBarre();
            case 10 : return ligneop.getMatriculeCui().getMatricule();
            case 11 : return ligneop.getMatriculeRes().getMatricule();
            default : return null;
        }
    }
    public Class getColumnClass(int col)
    {
        switch(col)
        {
            case 0 : return Date.class;
            case 1 : return Integer.class;
            case 2 : return Integer.class;
            case 3 : return Integer.class;
            case 4 : return Date.class;
            case 5 : return Date.class;
            case 6 : return String.class;
            case 7 : return Boolean.class;
            case 8 : return String.class;
            case 9 : return Integer.class;
            case 10 : return Integer.class;
            case 11 : return Integer.class;
            default : return String.class;
        }
    }
}
