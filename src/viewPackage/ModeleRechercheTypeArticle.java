package viewPackage;

import modelPackage.Lot;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeleRechercheTypeArticle extends AbstractTableModel {
    private ArrayList<String> nomColonnes = new ArrayList<>();
    private ArrayList<Lot> contenu = new ArrayList<>();

    public ModeleRechercheTypeArticle(ArrayList<Lot> lot)
    {
        contenu = lot;
        nomColonnes.add("Nom F");
        nomColonnes.add("Quantité");
        nomColonnes.add("Nom P");
        nomColonnes.add("Prénom P");
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
            case 0 : {if((ligneLot).getNumeroTVA() != null)
                return ligneLot.getNumeroTVA().getNom();
            else
                return null;
            }
            case 1 : return ligneLot.getQuantite();
            case 2 : {if((ligneLot).getMatricule() != null)
                return ligneLot.getMatricule().getNom();
            else
                return null;
            }
            case 3 : {if((ligneLot).getMatricule() != null)
                return ligneLot.getMatricule().getPrenom();
            else
                return null;
            }
            default : return null;
        }
    }
    public Class getColumnClass(int col)
    {
        switch(col)
        {
            case 0 : return String.class;
            case 1 : return Integer.class;
            case 2 : return String.class;
            case 3 : return String.class;
            default : return String.class;
        }
    }
}
