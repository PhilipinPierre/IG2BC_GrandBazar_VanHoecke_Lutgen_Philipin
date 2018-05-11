package exceptionsPackage;

public class ExceptionsBD extends  Exception {
    private String typeDeProbleme;

    public ExceptionsBD(String typeDeProbleme)
    {
        this.typeDeProbleme = typeDeProbleme;
    }

    public String getMessage()
    {
        return "Erreur lors de l'acces " + typeDeProbleme;
    }
}