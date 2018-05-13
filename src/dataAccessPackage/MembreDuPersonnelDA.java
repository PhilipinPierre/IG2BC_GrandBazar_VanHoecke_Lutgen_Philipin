package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MembreDuPersonnelDA {
    ArrayList<MembreDuPersonnel> getAllMdp() throws ExceptionsBD;
}
