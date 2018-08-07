package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MembreDuPersonnelDA {
    ArrayList<MembreDuPersonnel> getAllMembreDuPersonnel() throws ExceptionsBD, SQLException;
}
