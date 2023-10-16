package EXO5.Vue.Animation;

import javax.swing.*;
import java.awt.*;

public class AnimColor extends Thread{
    public static final boolean BACKGROUND = false;
    public static final boolean FOREGROUND = true;
    JComponent component;
    boolean backOrFor;
    Color[] coleurs;
    int millis;
    public AnimColor(JComponent component, boolean backOrFor, int millis, Color[] couleurs){
        this.component = component;
        this.coleurs = couleurs;
        this.millis = millis;
        this.backOrFor = backOrFor;
        start();
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            for (int i =0;i<coleurs.length;i++) {
                if (!backOrFor) component.setBackground(coleurs[i]);
                else component.setForeground(coleurs[i]);
                try {
                    sleep(millis);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }

    public boolean isBackOrFor() {
        return backOrFor;
    }

    public void setBackOrFor(boolean backOrFor) {
        this.backOrFor = backOrFor;
    }

    public Color[] getColeurs() {
        return coleurs;
    }

    public void setColeurs(Color[] coleurs) {
        this.coleurs = coleurs;
    }

    public int getMillis() {
        return millis;
    }

    public void setMillis(int millis) {
        this.millis = millis;
    }
}
