package businessPackage;

import dataAccessPackage.CuisinierBDA;
import dataAccessPackage.CuisinierDA;
import exceptionsPackage.ExceptionsBD;
import modelPackage.Cuisinier;

import java.util.ArrayList;

public class CuisinierManager {
    private CuisinierDA cuisinier;

    public CuisinierManager(){
        cuisinier = new CuisinierBDA();
    }

    public ArrayList<Cuisinier> getAllCuisinier() throws ExceptionsBD{
        return cuisinier.getAllCuisinier();
    }
}
