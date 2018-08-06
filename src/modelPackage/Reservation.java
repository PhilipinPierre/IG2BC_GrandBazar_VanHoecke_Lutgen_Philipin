package modelPackage;

import java.util.GregorianCalendar;

public class Reservation {
    private OrdrePreparation ordrePreparation;
    private TypeArticle codeBarre;
    private Integer quantiteReservee;
    private Integer numeroSequentiel;
    private GregorianCalendar date;

    public OrdrePreparation getOrdrePreparation() { return ordrePreparation; }
    public TypeArticle getCodeBarre() { return codeBarre; }
    public Integer getQuantiteReservee() { return quantiteReservee; }
    public Integer getNumeroSequentiel() { return numeroSequentiel; }
    public GregorianCalendar getDate() { return date; }

    public void setOrdrePreparation(OrdrePreparation ordrePreparation) { this.ordrePreparation = ordrePreparation; }
    public void setCodeBarre(TypeArticle codeBarre) { this.codeBarre = codeBarre; }
    public void setQuantiteReservee(Integer quantiteReservee) { this.quantiteReservee = quantiteReservee; }
    public void setNumeroSequentiel(Integer numeroSequentiel) {this.numeroSequentiel = numeroSequentiel;}
    public void setDate(GregorianCalendar date) {this.date = date;}
}
