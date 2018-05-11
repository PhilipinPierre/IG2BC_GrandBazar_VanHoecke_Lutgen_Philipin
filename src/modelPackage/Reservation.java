package modelPackage;

public class Reservation {
    private OrdrePreparation ordrePreparation;
    private TypeArticle codeBarre;
    private Integer quantiteReservee;

    public OrdrePreparation getOrdrePreparation() { return ordrePreparation; }
    public TypeArticle getCodeBarre() { return codeBarre; }
    public Integer getQuantiteReservee() { return quantiteReservee; }

    public void setOrdrePreparation(OrdrePreparation ordrePreparation) { this.ordrePreparation = ordrePreparation; }
    public void setCodeBarre(TypeArticle codeBarre) { this.codeBarre = codeBarre; }
    public void setQuantiteReservee(Integer quantiteReservee) { this.quantiteReservee = quantiteReservee; }
}
