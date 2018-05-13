package businessPackage;

import dataAccessPackage.MembreDuPersonnelBDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;
import java.util.ArrayList;

public class MembreDuPersonnelManager {
    private MembreDuPersonnelBDA membreDuPersonnelBDA;

    public MembreDuPersonnelManager() {membreDuPersonnelBDA = new MembreDuPersonnelBDA();}

    public ArrayList<MembreDuPersonnel> getAllMembreDuPersonnel() throws ExceptionsBD
    {
        ArrayList<MembreDuPersonnel> membreDuPersonnelList = membreDuPersonnelBDA.getAllMembreDuPersonnel();
        return membreDuPersonnelList;
    }

}