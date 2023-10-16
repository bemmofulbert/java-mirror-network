package EXO5;

import EXO5.Serveur.Serveur;
import EXO5.Vue.Button;
import EXO5.Vue.Menu.ChoixColRow;
import EXO5.Vue.ServeurPret;
import EXO5.Vue.Utiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeuOffline extends JPanel{
    private static GridLayout gridLayout;
    private static JButton[] buts;
    private static ImageIcon img = new ImageIcon("images/be.png");
    public JPanel zoneJeu;
    public BorderLayout borderLay_fenetre;
    public JButton but_retour;
    private ChoixColRow precedent_panel;

    int row=1,col=1;
    public JeuOffline(ChoixColRow precedent_panel, int row, int col) {
        this.col = col;
        this.row = row;
        this.precedent_panel = precedent_panel;

        gridLayout = new GridLayout(row,col);

        zoneJeu = new JPanel(gridLayout);
        zoneJeu.setLayout(gridLayout);

        but_retour = new Button("<< retour a la personnalisation ");
        but_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Button.deZoomMenu(but_retour);
                but_retour.setBackground(Utiles.green);
                Main.AllerA(precedent_panel);
            }
        });

        borderLay_fenetre = new BorderLayout();

        setLayout(borderLay_fenetre);
        add(zoneJeu,BorderLayout.CENTER);
        add(but_retour,BorderLayout.SOUTH);
        buts = new JButton[col*row];

        for(int i=0;i<buts.length;i++) {
            buts[i] = new JButton();
            JLabel lab = new JLabel();
            buts[i].setBackground(new Color(255,255,255));
            zoneJeu.add(buts[i]);
        }
        buts[0].setIcon(img);
        for(int i=0;i<buts.length;i++) {
            buts[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    onClick((JButton) actionEvent.getSource());
                }
            });
        }

    }

    public void creer_Grid() {
        for(int i=0;i<buts.length;i++) {
            zoneJeu.remove(buts[i]);
        }

        gridLayout = new GridLayout(row,col);
        zoneJeu.setLayout(gridLayout);
        buts = new JButton[col*row];

        for(int i=0;i<buts.length;i++) {
            buts[i] = new JButton();
            JLabel lab = new JLabel();
            buts[i].setBackground(new Color(255,255,255));
            zoneJeu.add(buts[i]);
        }
        buts[0].setIcon(img);
        for(int i=0;i<buts.length;i++) {
            buts[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    onClick((JButton) actionEvent.getSource());
                }
            });
        }
    }
    //mettre l'image a une position precise
    public static void setPos(int pos_in_buts_table){
        for(int i=0; i< buts.length;i++) {
            buts[i].setIcon(null);
        }
        buts[pos_in_buts_table].setIcon(JeuOnline.getImg());
    }
    public static ImageIcon getImg() { return img; }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
        creer_Grid();
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
        creer_Grid();
    }
    private void onClick(JButton but_elu){
        for(int i=0; i< buts.length;i++) {
            buts[i].setIcon(null);
            if (but_elu == buts[i])setPos(i);//but[i].setIcon(EXO5.getImg());
        }
        //but_elu.setIcon(EXO5.getImg());
    }


}
