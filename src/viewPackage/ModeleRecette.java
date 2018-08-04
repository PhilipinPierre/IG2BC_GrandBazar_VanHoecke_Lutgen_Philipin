package viewPackage;

import modelPackage.Recette;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeleRecette extends AbstractTableModel {
    private ArrayList<String> nomColonnes = new ArrayList<>();
    private ArrayList<Recette> contenu = new ArrayList<>();

    public ModeleRecette(ArrayList<Recette> recette)
    {
        contenu = recette;
        nomColonnes.add("Nom");
        nomColonnes.add("DLC");
        nomColonnes.add("Descriptif");
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
        Recette ligneRecette = contenu.get(row);
        switch(col)
        {
            case 0 : return ligneRecette.getNom();
            case 1 : return ligneRecette.getDLC();
            case 2 : return ligneRecette.getDescriptif();
            default : return null;
        }
    }
    public Class getColumnClass(int col)
    {
        switch(col)
        {
            case 0 : return String.class;
            case 1 : return String.class;
            case 2 : return String.class;
            default : return String.class;
        }
    }
}
