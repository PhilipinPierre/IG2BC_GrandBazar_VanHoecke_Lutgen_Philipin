package modelPackage;

public class ListingFournisseurLocalite {
    private TypeArticle typeArticle;
    private Fournisseur fournisseur;
    private Lot lot;
    private MembreDuPersonnel membreDuPersonnel;

    public Fournisseur getFournisseur() { return fournisseur; }
    public Lot getLot() { return lot; }
    public TypeArticle getTypeArticle() { return typeArticle; }
    public MembreDuPersonnel getMembreDuPersonnel() { return membreDuPersonnel; }

    public void setTypeArticle(TypeArticle typeArticle) { this.typeArticle = typeArticle; }
    public void setFournisseur(Fournisseur fournisseur) { this.fournisseur = fournisseur; }
    public void setMembreDuPersonnel(MembreDuPersonnel membreDuPersonnel) { this.membreDuPersonnel = membreDuPersonnel; }
    public void setLot(Lot lot) { this.lot = lot; }
}
