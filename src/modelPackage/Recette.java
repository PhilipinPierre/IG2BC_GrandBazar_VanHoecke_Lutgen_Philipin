package modelPackage;

public class Recette {
    private String nom, descriptif;
    private Integer DLC;

    public String getNom() { return nom; }
    public String getDescriptif() { return descriptif; }
    public Integer getDLC() { return DLC; }

    public void setNom(String nom) { this.nom = nom; }
    public void setDescriptif(String descriptif) { this.descriptif = descriptif; }
    public void setDLC(Integer DLC) { this.DLC = DLC; }
}
