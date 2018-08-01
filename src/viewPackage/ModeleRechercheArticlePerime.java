package viewPackage;

import modelPackage.ArticlePerime;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModeleRechercheArticlePerime extends AbstractTableModel {
    private ArrayList<String> nomColonnes = new ArrayList<>();
    private ArrayList<ArticlePerime> contenu = new ArrayList<>();

    public ModeleRechercheArticlePerime(ArrayList<ArticlePerime> articlePerime)
    {
        contenu = articlePerime;
        nomColonnes.add("Nom");
        nomColonnes.add("Prénom");
        nomColonnes.add("Libellé");
        nomColonnes.add("Quantité");
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
        ArticlePerime ligneArticlePerime = contenu.get(row);
        switch(col)
        {
            case 0 : {if((ligneArticlePerime).getMatricule() != null)
                return ligneArticlePerime.getMatricule().getNom();
            else
                return null;
            }
            case 1 : {if((ligneArticlePerime).getMatricule() != null)
                return ligneArticlePerime.getMatricule().getPrenom();
            else
                return null;
            }
            case 2 : {if((ligneArticlePerime).getCodeBarre() != null)
                return ligneArticlePerime.getCodeBarre().getLibelle();
            else
                return null;
            }
            case 3 : return ligneArticlePerime.getQuantiteJetee();
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
            case 3 : return Integer.class;
            default : return String.class;
        }
    }
}