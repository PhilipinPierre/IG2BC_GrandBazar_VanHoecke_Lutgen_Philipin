package modelPackage;

import java.util.GregorianCalendar;

public class Ticket {
    private Integer numTicket, heure;
    private GregorianCalendar date;
    private Client numClient;
    private MembreDuPersonnel matricule;

    public Integer getNumTicket() { return numTicket;}
    public Integer getHeure() { return heure;}
    public GregorianCalendar getDate() { return date;}
    public Client getNumClient() { return numClient;}
    public MembreDuPersonnel getMatricule() { return matricule;}

    public void setNumTicket(Integer numTicket) { this.numTicket = numTicket;}
    public void setHeure(Integer heure) { this.heure = heure;}
    public void setDate(GregorianCalendar date) { this.date = date;}
    public void setNumClient(Client numClient) { this.numClient = numClient;}
    public void setMatricule(MembreDuPersonnel matricule) { this.matricule = matricule;}
}
