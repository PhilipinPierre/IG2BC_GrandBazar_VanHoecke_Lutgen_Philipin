package modelPackage;

public class Ingredient {
    private Recette nom;
    private TypeArticle codeBarre;
    private Integer quantitePortion;

    public Recette getNom() { return nom; }
    public TypeArticle getCodeBarre() { return codeBarre; }
    public Integer getQuantitePortion() { return quantitePortion; }

    public void setNom(Recette nom) { this.nom = nom; }
    public void setCodeBarre(TypeArticle codeBarre) { this.codeBarre = codeBarre; }
    public void setQuantitePortion(Integer quantitePortion) { this.quantitePortion = quantitePortion; }
}
