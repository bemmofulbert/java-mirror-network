package EXO5.Vue.Menu;

import EXO5.Main;
import EXO5.Vue.Menu.Mutil.Mode;
import EXO5.Vue.Utiles;

import static EXO5.Main.AllerA;
import static EXO5.Main.fra;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import EXO5.Vue.Button;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Accueil extends MenuMin{

    private GridLayout layout;

    //button element
    Button but_single;
    Button but_multi;
    Button but_exit;
    //text
    private String string_solo = "Un Joueur";
    private String string_titre = "Menu";
    private String string_multi = "Multijoueurs";
    private String string_exit = "Quitter";
    public ChoixColRow single_suivant;
    public Mode multi_suivant;
    public Accueil() {
        super();
        single_suivant = new ChoixColRow(this,ChoixColRow.OFFLINE);
        multi_suivant = new Mode(this);

        layout = new GridLayout(7,1);
        center.setLayout(layout);
        JPanel center_sub = new JPanel();
        center_sub.setBackground(Utiles.white);
        center.add(center_sub);

        but_single =new Button(string_solo);
        but_single.setFont(Utiles.font_menu);
        //but_single.setBackground(Utiles.green);
        center.add(but_single);
        center.add(new JPanel());

        but_multi =new Button(string_multi);
        but_multi.setFont(Utiles.font_menu);
        //but_multi.setBackground(Utiles.green);
        center.add(but_multi);
        center.add(new JPanel());

        but_exit =new Button(string_exit);
        but_exit.setFont(Utiles.font_menu);
        //but_exit.setBackground(Utiles.green);
        center.add(but_exit);
        center.add(new JPanel());

        entete.setTitre(string_titre);
        add(center,BorderLayout.CENTER);
        //add(entete,BorderLayout.NORTH);
        //but_single.addActionListener(new GereClickButtonFirst(but_single,but_multi,but_exit));
        initEvent();
    }
    public void initEvent() {
        but_exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.exit(0);
            }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                zoomMenu(but_exit);
                but_exit.setBackground(Color.orange);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                deZoomMenu(but_exit);
                but_exit.setBackground(Utiles.green);
            }
        });
        but_single.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                AllerA(single_suivant);
                deZoomMenu(but_single);
                but_single.setBackground(Utiles.green);
            }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                zoomMenu(but_single);
                but_single.setBackground(Utiles.green_dark);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                deZoomMenu(but_single);
                but_single.setBackground(Utiles.green);
            }
        });
        but_multi.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                AllerA(multi_suivant);
                deZoomMenu(but_multi);
                but_multi.setBackground(Utiles.green);
            }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                zoomMenu(but_multi);
                but_multi.setBackground(Utiles.green_dark);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                deZoomMenu(but_multi);
                but_multi.setBackground(Utiles.green);
            }
        });
    }
    public void zoomMenu(JButton but){
        but.setFont(Utiles.font_menu_hover);
    }
    public void deZoomMenu(JButton but){
        but.setFont(Utiles.font_menu);
    }

}