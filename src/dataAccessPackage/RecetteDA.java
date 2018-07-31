package dataAccessPackage;

import java.util.*;
import exceptionsPackage.*;
import modelPackage.*;

public interface RecetteDA {
    ArrayList<Recette> getAllRecette()throws ExceptionsBD;
    Recette getRecette(String nom)throws ExceptionsBD;
}
