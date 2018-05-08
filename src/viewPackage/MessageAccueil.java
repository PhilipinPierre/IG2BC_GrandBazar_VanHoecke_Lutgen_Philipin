package viewPackage;

import javax.swing.*;
import java.awt.*;

public class MessageAccueil extends JPanel {

    private JLabel messageBienvenue1;

    public MessageAccueil()
    {
        messageBienvenue1 = new JLabel("Bienvenue");

        this.setLayout(new FlowLayout());

        this.add(messageBienvenue1);
    }
}
