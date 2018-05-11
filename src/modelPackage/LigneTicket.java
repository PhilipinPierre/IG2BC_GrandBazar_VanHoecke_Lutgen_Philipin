package modelPackage;

public class LigneTicket {
    private TypeArticle typeArticle;
    private Ticket ticket;
    private Integer quantite;
    private Double prixReel;

    public Ticket getTicket() { return ticket;}
    public TypeArticle getTypeArticle() { return typeArticle; }
    public Integer getQuantite() { return quantite; }
    public Double getPrixReel() { return prixReel; }

    public void setTypeArticle(TypeArticle typeArticle) { this.typeArticle = typeArticle; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }
    public void setQuantite(Integer quantite) { this.quantite = quantite; }
    public void setPrixReel(Double prixReel) { this.prixReel = prixReel; }
}
