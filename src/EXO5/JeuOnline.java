package EXO5;

import EXO5.Vue.Menu.Mutil.ConnexionAttente;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import EXO5.Vue.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeuOnline extends JFrame{
    private GridLayout gridLayout;
    public JButton[] buts;
    private static ImageIcon img = new ImageIcon("images/be.png");

    public Cli clientReseau;
    private JPanel zoneJeu,zoneEnd;
    private BorderLayout borderLay_fenetre;

    private JLabel labelEnd;

    int row=1,col=1;
    public ConnexionAttente fen_connexion;
    public String nomServeur;
    public int port;
    private Button but_retour;
    public int pos=0;
    public JeuOnline() {
        this.clientReseau = new Cli(this);

        fenetre_firstConf();
        gridLayout = new GridLayout(row,col);
        zoneEnd = new JPanel(new FlowLayout());
        labelEnd =  new JLabel("up");
        zoneEnd.add(labelEnd);

        zoneJeu = new JPanel(gridLayout);
        zoneJeu.setLayout(gridLayout);

        borderLay_fenetre = new BorderLayout();

        setLayout(borderLay_fenetre);
        add(zoneJeu,BorderLayout.CENTER);
        add(zoneEnd,BorderLayout.SOUTH);
        buts = new JButton[this.col*this.row];

        for(int i=0;i<buts.length;i++) {
            buts[i] = new JButton();
            //buts[i].setBackground(new Color(255,255,255));
            //zoneJeu.add(buts[i]);
            //buts[i].addActionListener(new GererClick(buts, buts[i],clientReseau));
        }
        //buts[0].setIcon(img);
    }
    public JeuOnline(ConnexionAttente fen_connexion,String nomServeur,int port){
        this.fen_connexion = fen_connexion;
        this.nomServeur=nomServeur;
        this.port = port;

        this.clientReseau = new Cli(this,nomServeur,port);

        fenetre_firstConf();
        gridLayout = new GridLayout(row,col);
        zoneEnd = new JPanel(new FlowLayout());
        labelEnd =  new JLabel("up");
        zoneEnd.add(labelEnd);

        zoneJeu = new JPanel(gridLayout);
        zoneJeu.setLayout(gridLayout);

        borderLay_fenetre = new BorderLayout();

        setLayout(borderLay_fenetre);
        add(zoneJeu,BorderLayout.CENTER);
        add(zoneEnd,BorderLayout.SOUTH);
        buts = new JButton[this.col*this.row];

        for(int i=0;i<buts.length;i++) {
            buts[i] = new JButton();
            //buts[i].setBackground(new Color(255,255,255));
            //zoneJeu.add(buts[i]);
            //buts[i].addActionListener(new GererClick(buts, buts[i],clientReseau));
        }
        but_retour = new Button("<< quitter la partie");
        but_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clientReseau.envoiBye();
                clientReseau.close();
                fen_connexion.retour();
            }
        });
        add(but_retour,BorderLayout.NORTH);
    }

    public void creer_Grid() {
        for(int i=0;i<buts.length;i++) {
            if(buts[i] != null) zoneJeu.remove(buts[i]);
            //System.out.println("comp- "+i);
        }

        gridLayout = new GridLayout(row,col);
        zoneJeu.setLayout(gridLayout);
        buts = new JButton[col*row];

        for(int i=0;i<buts.length;i++) {
            buts[i] = new JButton();
            JLabel lab = new JLabel();
            buts[i].setBackground(new Color(255,255,255));
            zoneJeu.add(buts[i]);

            buts[i].addActionListener(new GererClick(buts, buts[i],clientReseau));
        }
        buts[0].setIcon(img);
    }
    //mettre l'image a une position precise
    public void setPos(int pos_in_buts_table){
        for(int i=0; i< buts.length;i++) {
            buts[i].setIcon(null);
        }
        buts[pos_in_buts_table].setIcon(JeuOnline.getImg());
        pos=pos_in_buts_table;
    }
    public static ImageIcon getImg() { return img; }

    public void fenetre_firstConf(){
        setTitle("Jeu d'image");
        //fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640,480);

        //fenetre.show();
    }

    public void lancerClient(){
        clientReseau.lancerClient();
    }

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

    public void show_aToi() {
        labelEnd.setText("C'est ton tour !");
        for (int i=0;i<buts.length;i++){
            buts[i].setEnabled(true);
        }
    }
    public void show_pasToi() {
        labelEnd.setText("pas ton tour !");
        for (int i=0;i<buts.length;i++){
            buts[i].setEnabled(false);
        }
    }

    public Cli getClientReseau() {
        return clientReseau;
    }
}
