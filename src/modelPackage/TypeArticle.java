package modelPackage;

import java.util.GregorianCalendar;

public class TypeArticle {
    private Integer codeBarre, quantiteeEnStock, quantiteeMinimal;
    private Double prix;
    private String libelle;
    private Boolean estPerissable;
    private GregorianCalendar datePromotionDebut, datePromotionFin;
//GETTORS
    public Integer getCodeBarre() { return codeBarre; }
    public Integer getQuantiteeEnStock() { return quantiteeEnStock; }
    public Integer getQuantiteeMinimal() { return quantiteeMinimal; }
    public Double getPrix() { return prix; }
    public String getLibelle() { return libelle; }
    public Boolean getEstPerissable() { return estPerissable; }
    public GregorianCalendar getDatePromotionDebut() { return datePromotionDebut; }
    public GregorianCalendar getDatePromotionFin() { return datePromotionFin; }
//SETTORS
    public void setCodeBarre(Integer codeBarre) { this.codeBarre = codeBarre; }
    public void setQuantiteeEnStock(Integer quantiteeEnStock) { this.quantiteeEnStock = quantiteeEnStock; }
    public void setQuantiteeMinimal(Integer quantiteeMinimal) { this.quantiteeMinimal = quantiteeMinimal; }
    public void setPrix(Double prix) { this.prix = prix; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public void setEstPerissable(Boolean estPerissable) { this.estPerissable = estPerissable; }
    public void setDatePromotionDebut(GregorianCalendar datePromotionDebut) { this.datePromotionDebut = datePromotionDebut; }
    public void setDatePromotionFin(GregorianCalendar datePromotionFin) { this.datePromotionFin = datePromotionFin; }
}
