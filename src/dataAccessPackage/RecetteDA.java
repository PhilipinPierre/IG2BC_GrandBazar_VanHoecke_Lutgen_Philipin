package dataAccessPackage;

import java.util.*;
import controllerPackage.ApplicationController;
import exceptionsPackage.*;
import modelPackage.*;

public interface RecetteDA {
    ArrayList<Recette> getAllRecette()throws ExceptionsBD;
    void ajouterRecette(ApplicationController applicationController, Recette r) throws ExceptionsBD;
}
