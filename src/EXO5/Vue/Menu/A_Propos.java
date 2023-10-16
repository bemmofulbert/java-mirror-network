package EXO5.Vue.Menu;

import javax.swing.*;
import java.awt.*;

public class A_Propos extends JPanel {
    FlowLayout flowLayout;
    JLabel label;
    JLabel labDeTravail;
    String text;

    public A_Propos(){
        text = "@copyright. Made by Bemmo Mbobda Fulbert Alexandre. Contact:bemmofulbert@gmail.com";
        label = new JLabel(text);
        labDeTravail = new JLabel();

        flowLayout = new FlowLayout(FlowLayout.LEFT);
        setLayout(flowLayout);

        add(labDeTravail);
        add(label);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        labDeTravail.setText(text);
    }
}
