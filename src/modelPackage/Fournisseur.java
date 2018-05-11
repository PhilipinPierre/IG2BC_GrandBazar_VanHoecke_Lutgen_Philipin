package modelPackage;

public class Fournisseur {
    private String nom, localite, rue;
    private Integer numeroTVA, codePostal;

    public String getNom() { return nom; }
    public String getLocalite() { return localite; }
    public String getRue() { return rue; }
    public Integer getNumeroTVA() { return numeroTVA; }
    public Integer getCodePostal() { return codePostal; }

    public void setNom(String nom) { this.nom = nom; }
    public void setLocalite(String localite) { this.localite = localite; }
    public void setRue(String rue) { this.rue = rue; }
    public void setCodePostal(Integer codePostal) { this.codePostal = codePostal; }
    public void setNumeroTVA(Integer numeroTVA) { this.numeroTVA = numeroTVA; }
}
