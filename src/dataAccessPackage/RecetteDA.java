package dataAccessPackage;

import java.sql.SQLException;
import java.util.*;
import controllerPackage.ApplicationController;
import exceptionsPackage.*;
import modelPackage.*;

public interface RecetteDA {
    ArrayList<Recette> getAllRecette() throws ExceptionsBD, SQLException;
    Recette getRecette(String nom) throws ExceptionsBD, SQLException;
    void ajouterRecette(ApplicationController applicationController, Recette r) throws ExceptionsBD, SQLException;
}
