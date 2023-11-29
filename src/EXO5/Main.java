package EXO5;

import EXO5.Vue.Menu.Accueil;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame fra = new JFrame("Jeu d'image");
    public static void AllerA(JPanel pane){
        fra.setContentPane(pane);
        fra.pack();
    }

    public static void main(String[] args) {
        fra.setContentPane(new Accueil());

        //JeuOnline jeuOnline = new JeuOnline("bems");
        //ServeurPret serveurPret = new ServeurPret(jeuOnline);
        //jeuOnline.lancerClient();

        fra.setSize(new Dimension(600,400));
        fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fra.show();
    }
}