package viewPackage;

import java.util.*;
import modelPackage.*;
import javax.swing.table.AbstractTableModel;

public class ModeleRechercheArticleLocalite extends AbstractTableModel {
    private ArrayList<String> nomColonnes = new ArrayList<>();
    private ArrayList<Lot> contenu = new ArrayList<>();

    public ModeleRechercheArticleLocalite(ArrayList<Lot> lot)
    {
        contenu = lot;
        nomColonnes.add("Catégorie");
        nomColonnes.add("Type");
        nomColonnes.add("Quantité");
        nomColonnes.add("Fournisseur");
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
        Lot ligneLot = contenu.get(row);
        switch(col)
        {
            case 0 : {if((ligneLot).getCodeLot() != null)
                        return ligneLot.getCodeLot();
                      else
                        return null;
                      }
            case 1 : {if((ligneLot).getCodeBarre() != null)
                        return ligneLot.getCodeBarre().getLibelle();
                     else
                         return null;
                     }
            case 2 : return ligneLot.getQuantite();
            case 3 : return ligneLot.getNumeroTVA().getNom();
            default : return null;
        }
    }
    public Class getColumnClass(int col)
    {
        switch(col)
        {
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return Integer.class;
            case 3 : return String.class;
            default : return String.class;
        }
    }
}
