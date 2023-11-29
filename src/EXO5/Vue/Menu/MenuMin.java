package EXO5.Vue.Menu;

import EXO5.Vue.Utiles;

import javax.swing.*;
import java.awt.*;

public class MenuMin extends JPanel {
    protected JPanel center;
    private BorderLayout borderLayout;
    protected Entete entete;
    protected A_Propos footer;

    public MenuMin() {
        footer = new A_Propos();
        entete = new Entete("titre");
        borderLayout = new BorderLayout();
        setLayout(borderLayout);
        center = new JPanel();
        //center.setBackground(Utiles.white);

        add(center,BorderLayout.CENTER);
        add(new JPanel(new FlowLayout()),BorderLayout.EAST);
        add(new JPanel(new FlowLayout()),BorderLayout.WEST);
        add(entete,BorderLayout.NORTH);
        add(footer,BorderLayout.SOUTH);
        setDoubleBuffered(true);
    }

    public A_Propos getFooter() {
        return footer;
    }

    public void setFooter(A_Propos footer) {
        this.footer = footer;
    }

    public Entete getEntete() {
        return entete;
    }

    public void setEntete(Entete entete) {
        this.entete = entete;
    }

}
