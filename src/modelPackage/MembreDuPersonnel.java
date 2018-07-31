package modelPackage;

import java.util.GregorianCalendar;

public class MembreDuPersonnel {
    public static final String EMPLOYE = "Employe";
    public static final String RESP_DES_VENTES = "Responsable des ventes";
    public static final String CUISINIER = "Cuisinier";

    private String nom, prenom, localite, rue;
    private Integer codePostal, matricule;
    private GregorianCalendar dateNaissance, dateEntree, dateSortie;
//GETTORS
    public String getNom() { return nom;}
    public String getPrenom() { return prenom;}
    public String getLocalite() { return localite; }
    public String getRue() { return rue;}
    public Integer getCodePostal() { return codePostal; }
    public Integer getMatricule() { return matricule; }
    public GregorianCalendar getDateNaissance() { return dateNaissance;}
    public GregorianCalendar getDateEntree() { return dateEntree;}
    public GregorianCalendar getDateSortie() { return dateSortie;}
//SETTORS
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom;}
    public void setLocalite(String localite) { this.localite = localite;}
    public void setRue(String rue) { this.rue = rue;}
    public void setCodePostal(Integer codePostal) { this.codePostal = codePostal;}
    public void setMatricule(Integer matricule) { this.matricule = matricule;}
    public void setDateNaissance(GregorianCalendar dateNaissance) { this.dateNaissance = dateNaissance;}
    public void setDateEntree(GregorianCalendar dateEntree) { this.dateEntree = dateEntree;}
    public void setDateSortie(GregorianCalendar dateSortie) { this.dateSortie = dateSortie;}
}
