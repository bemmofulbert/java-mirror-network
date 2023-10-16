package EXO5.Vue.Menu;

import EXO5.Vue.Animation.AnimLabel;
import EXO5.Vue.Utiles;

import javax.swing.*;
import java.awt.*;

public class Entete extends JPanel {
    private String titre;
    private FlowLayout flowLayout;
    JLabel lab_titre;
    private AnimLabel animLabel;

    public Entete(String titre) {
        super();

        setDoubleBuffered(true);
        this.titre = titre;
        lab_titre = new JLabel(titre);lab_titre.setDoubleBuffered(true);
        flowLayout = new FlowLayout();
        setLayout(flowLayout);

        setBackground(Utiles.blue);
        add(lab_titre);
        animLabel = new AnimLabel(lab_titre,600,new String[]{"."+titre,titre+"."});
        animLabel.start();
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
        lab_titre.setText(titre);
        animLabel.run = false;
        animLabel = new AnimLabel(lab_titre,600,new String[]{"."+titre,titre+"."});
        animLabel.start();
    }
}
