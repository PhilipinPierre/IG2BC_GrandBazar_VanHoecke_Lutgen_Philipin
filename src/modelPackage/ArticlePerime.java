package modelPackage;

import java.util.GregorianCalendar;

public class ArticlePerime {
    private String id;
    private Integer quantiteJetee;
    private GregorianCalendar date;
    private MembreDuPersonnel matricule;
    private TypeArticle codeBarre;

    public String getId() { return id; }
    public Integer getQuantiteJetee() { return quantiteJetee; }
    public GregorianCalendar getDate() { return date; }
    public MembreDuPersonnel getMatricule() { return matricule; }
    public TypeArticle getCodeBarre() { return codeBarre; }

    public void setId(String id) { this.id = id; }
    public void setQuantiteJetee(Integer quantiteJetee) { this.quantiteJetee = quantiteJetee; }
    public void setMatricule(MembreDuPersonnel matricule) { this.matricule = matricule; }
    public void setDate(GregorianCalendar date) { this.date = date; }
    public void setCodeBarre(TypeArticle codeBarre) { this.codeBarre = codeBarre; }

    @Override
    public String toString() {
        return "L'article périmé " + this.codeBarre.getLibelle() + " à été jeté par le membre du personnel "+ matricule.getMatricule() + " à la date du " + date.toString();
    }
}
