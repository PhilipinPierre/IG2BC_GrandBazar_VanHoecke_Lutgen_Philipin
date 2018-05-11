package modelPackage;

import java.util.GregorianCalendar;

public class Client {
    private Integer numClient, codePostal;
    private String nom, prenom, localite, rue;
    private GregorianCalendar dateNaissance, dateCreationCompte;

//GETTORS
    public Integer getNumClient(){ return numClient; }
    public Integer getCodePostal(){ return codePostal;}
    public String getNom(){return nom;}
    public String getPrenom() { return prenom;}
    public String getLocalite() { return localite;}
    public String getRue(){return rue;}
    public GregorianCalendar getDateNaissance() { return dateNaissance;}
    public GregorianCalendar getDateCreationCompte() { return dateCreationCompte;}
//SETTOR
    public void setNumClient(Integer numClient) { this.numClient = numClient;}
    public void setCodePostal(Integer codePostal) { this.codePostal = codePostal; }
    public void setNom(String nom) { this.nom = nom;}
    public void setPrenom(String prenom) { this.prenom = prenom;}
    public void setLocalite(String localite) { this.localite = localite;}
    public void setRue(String rue) { this.rue = rue;}
    public void setDateNaissance(GregorianCalendar dateNaissance) { this.dateNaissance = dateNaissance;}
    public void setDateCreationCompte(GregorianCalendar dateCreationCompte) { this.dateCreationCompte = dateCreationCompte;}
}
