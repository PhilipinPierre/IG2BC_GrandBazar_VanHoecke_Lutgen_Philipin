package viewPackage;

import controllerPackage.ApplicationController;

public class Principale
{
    private ApplicationController applicationController; //DANS FENETRE LOG IN PLUS TARD !!!!
    public static void main(String[] args)
    {
        ApplicationController applicationController = new ApplicationController(); //CA AUSSI
        FenetrePrincipale fenetrePrincipale = new FenetrePrincipale(applicationController);
    }
}
