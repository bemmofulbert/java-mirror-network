package EXO5.Vue.Menu;

import EXO5.JeuOffline;
import EXO5.JeuOnline;
import EXO5.Main;
import EXO5.Vue.ServeurPret;
import EXO5.Vue.Utiles;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import EXO5.Vue.Button;

public class ChoixColRow extends MenuMin{
     GridLayout gridLayout;
    private String titre = "Personnalisez votre partie";
    //lignes
    JLabel lab_ligne;
    JSpinner spin_ligne;
    //colonnes
    JLabel lab_col;
    JSpinner spin_col;
    JButton but_retour,but_jouer;
    private MenuMin precedent_panel;
    private boolean online = false;
    public static boolean ONLINE = true;
    public static boolean OFFLINE = false;
    private ServeurPret serveur_suivant;
    public ChoixColRow(MenuMin precedent_panel,boolean online) {
        super();
        entete.setTitre(titre);
        this.precedent_panel = precedent_panel;
        this.online = online;

        gridLayout= new GridLayout(7,2);
        center.setLayout(gridLayout);creer2Espace();

        //lignes
        lab_ligne = new JLabel("Entrez le nombre de lignes :");
        lab_ligne.setFont(Utiles.font);
        spin_ligne = new JSpinner(new SpinnerNumberModel(1,1,99,1));
        spin_ligne.setFont(Utiles.font);
        center.add(lab_ligne);center.add(spin_ligne);

        creer2Espace();

        //colonnes
        lab_col = new JLabel("Entrez le nombre de colonnes :");
        lab_col.setFont(Utiles.font);
        spin_col = new JSpinner(new SpinnerNumberModel(1,1,99,1));
        spin_col.setFont(Utiles.font);
        center.add(lab_col);center.add(spin_col);

        creer2Espace();

        //buts retour-jouer
        but_jouer = new Button("Jouer >>");
        but_retour = new Button("<< retour au menu");
        center.add(but_retour);center.add(but_jouer);

        creer2Espace();
        initEvent();
    }
    private void initEvent(){
        but_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Button.deZoomMenu(but_retour);
                but_retour.setBackground(Utiles.green);
                Main.AllerA(precedent_panel);
            }
        });
        but_jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Button.deZoomMenu(but_jouer);
                but_jouer.setBackground(Utiles.green);

                int row = Integer.parseInt(spin_ligne.getValue().toString());
                int col = Integer.parseInt(spin_col.getValue().toString());
                if (!isOnline()){
                    Main.AllerA(new JeuOffline(ChoixColRow.this,row,col));
                }else {
                    serveur_suivant = new ServeurPret(ChoixColRow.this,row,col);
                    Main.AllerA(serveur_suivant);
                }
            }
        });
    }
    private void creerEspace(){
        center.add(new JPanel());
    }
    private void creer2Espace(){
        center.add(new JPanel());
        center.add(new JPanel());
    }

    public boolean isOnline() {
        return online;
    }
}
