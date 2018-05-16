package viewPackage;

public class ThreadBienvenue extends Thread {
    private MessageAccueil messageAccueil;

    public ThreadBienvenue(MessageAccueil messageAccueil){this.messageAccueil = messageAccueil;}
    public void run(){
        while(true){
            try{
                Thread.sleep(100);
                messageAccueil.repaint();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
