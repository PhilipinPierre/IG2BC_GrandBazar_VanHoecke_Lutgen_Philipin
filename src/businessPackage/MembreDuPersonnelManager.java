package businessPackage;

import dataAccessPackage.MembreDuPersonnelBDA;
import dataAccessPackage.MembreDuPersonnelDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembreDuPersonnelManager {
    private MembreDuPersonnelDA membreDuPersonnelBDA;

    public MembreDuPersonnelManager() {membreDuPersonnelBDA = new MembreDuPersonnelBDA();}

    public ArrayList<MembreDuPersonnel> getAllMembreDuPersonnel() throws ExceptionsBD, SQLException
    {
        ArrayList<MembreDuPersonnel> membreDuPersonnelList = membreDuPersonnelBDA.getAllMembreDuPersonnel();
        return membreDuPersonnelList;
    }

    /*public MembreDuPersonnel getUtilisateur(String matricule, String motDePasse) throws ExceptionsBD
    {
        try
        {
            return membreDuPersonnelBDA.getUtilisateur(Integer.parseInt(matricule), motDePasse);
        }
        catch (Exception e)
        {
            return null;
        }
    }*/
}