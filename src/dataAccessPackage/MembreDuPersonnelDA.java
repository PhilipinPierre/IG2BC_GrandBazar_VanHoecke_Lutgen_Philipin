package dataAccessPackage;

import exceptionsPackage.ExceptionsBD;
import modelPackage.MembreDuPersonnel;

import java.util.ArrayList;

public interface MembreDuPersonnelDA {
    ArrayList<MembreDuPersonnel> getAllCuisiniers() throws ExceptionsBD;
    ArrayList<MembreDuPersonnel> getAllResponsableDesVentes() throws  ExceptionsBD;
    ArrayList<MembreDuPersonnel> getAllEmployees() throws ExceptionsBD;
    ArrayList<MembreDuPersonnel> getAllMDP() throws ExceptionsBD;
    MembreDuPersonnel getUser(int matricule, String mdp) throws ExceptionsBD;
}
