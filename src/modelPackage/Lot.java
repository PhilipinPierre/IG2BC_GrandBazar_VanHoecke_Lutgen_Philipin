package modelPackage;

import java.util.GregorianCalendar;

public class Lot {
    private String id;
    private Integer quantite, codeLot;
    private GregorianCalendar datePeremption, dateFourniturePrevue, dateCommande;
    private TypeArticle codeBarre;
    private MembreDuPersonnel matricule;
    private Fournisseur numeroTVA;

    public String getId() { return id; }
    public Integer getQuantite() { return quantite; }
    public Integer getCodeLot() { return codeLot; }
    public GregorianCalendar getDateCommande() { return dateCommande; }
    public GregorianCalendar getDatePeremption() { return datePeremption;}
    public GregorianCalendar getDateFourniturePrevue() { return dateFourniturePrevue; }
    public TypeArticle getCodeBarre() { return codeBarre; }
    public MembreDuPersonnel getMatricule() { return matricule; }
    public Fournisseur getNumeroTVA() { return numeroTVA; }

    public void setId(String id) { this.id = id; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
    public void setCodeLot(Integer codeLot) { this.codeLot = codeLot; }
    public void setDatePeremption(GregorianCalendar datePeremption) { this.datePeremption = datePeremption; }
    public void setDateFourniturePrevue(GregorianCalendar dateFourniturePrevue) { this.dateFourniturePrevue = dateFourniturePrevue; }
    public void setDateCommande(GregorianCalendar dateCommande) { this.dateCommande = dateCommande;}
    public void setCodeBarre(TypeArticle codeBarre) { this.codeBarre = codeBarre; }
    public void setMatricule(MembreDuPersonnel matricule) { this.matricule = matricule; }
    public void setNumeroTVA(Fournisseur numeroTVA) { this.numeroTVA = numeroTVA; }
}
