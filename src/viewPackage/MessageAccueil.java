package viewPackage;

import javax.swing.*;
import java.awt.*;

public class MessageAccueil extends JPanel {

    private int compteur;
    private ThreadBienvenue threadBienvenue;

    public MessageAccueil()
    {
        this.compteur = 0;

        if(threadBienvenue == null) {
            threadBienvenue = new ThreadBienvenue(this);
            threadBienvenue.start();
        }else {
            threadBienvenue.start();
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        int fontSize = 30;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        String message = "Bienvenue ";
        for(int i = 0; i<compteur; i++){
            message += " .";
        }
        compteur++;
        if(compteur>3)
            compteur = 0;
        g.drawString(message,200,200);
    }
}
