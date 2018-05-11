package modelPackage;

import java.util.GregorianCalendar;

public class OrdrePreparation {
    private Integer numeroSequentiel, quantitePrevue, quantiteProduite;
    private GregorianCalendar date, dateVente, datePreparation;
    private String remarque;
    private Boolean estUrgent;
    private Recette nom;
    private TypeArticle codeBarre;
    private Cuisinier matriculeCui;
    private ResponsableDesVentes matriculeRes;

    public Integer getNumeroSequentiel() { return numeroSequentiel; }
    public Integer getQuantitePrevue() { return quantitePrevue; }
    public Integer getQuantiteProduite() { return quantiteProduite; }
    public GregorianCalendar getDate() { return date; }
    public GregorianCalendar getDatePreparation() { return datePreparation; }
    public GregorianCalendar getDateVente() { return dateVente; }
    public String getRemarque() { return remarque; }
    public Boolean getEstUrgent() { return estUrgent; }
    public Recette getNom() { return nom; }
    public TypeArticle getCodeBarre() { return codeBarre; }
    public Cuisinier getMatriculeCui() { return matriculeCui; }
    public ResponsableDesVentes getMatriculeRes() { return matriculeRes; }

    public void setNumeroSequentiel(Integer numeroSequentiel) { this.numeroSequentiel = numeroSequentiel; }
    public void setQuantitePrevue(Integer quantitePrevue) { this.quantitePrevue = quantitePrevue; }
    public void setQuantiteProduite(Integer quantiteProduite) { this.quantiteProduite = quantiteProduite; }
    public void setDate(GregorianCalendar date) { this.date = date; }
    public void setDatePreparation(GregorianCalendar datePreparation) { this.datePreparation = datePreparation; }
    public void setDateVente(GregorianCalendar dateVente) { this.dateVente = dateVente; }
    public void setRemarque(String remarque) { this.remarque = remarque; }
    public void setEstUrgent(Boolean estUrgent) { this.estUrgent = estUrgent; }
    public void setNom(Recette nom) { this.nom = nom; }
    public void setCodeBarre(TypeArticle codeBarre) { this.codeBarre = codeBarre; }
    public void setMatriculeCui(Cuisinier matriculeCui) { this.matriculeCui = matriculeCui; }
    public void setMatriculeRes(ResponsableDesVentes matriculeRes) { this.matriculeRes = matriculeRes; }
}
