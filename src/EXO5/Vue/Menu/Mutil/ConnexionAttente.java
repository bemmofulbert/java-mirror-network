package EXO5.Vue.Menu.Mutil;

import EXO5.JeuOnline;
import EXO5.Main;
import EXO5.Vue.Animation.AnimLabel;
import EXO5.Vue.Menu.MenuMin;
import EXO5.Vue.Utiles;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import EXO5.Vue.Button;

public class ConnexionAttente extends MenuMin {
    private JLabel label;
    private AnimLabel animLabel;
    private GridLayout gridLayout;
    private String string_message_attente = "En attente du serveur";

    private String titre = "Connexion";
    private JPanel center_sub;
    private Button but_retour;
    JeuOnline jeuOnline;
    Connexion connexion_precedent;
    public String adresse;
    public int port;
    public ConnexionAttente(Connexion connexion_precedent,String adresse,int port) {
        super();
        this.connexion_precedent = connexion_precedent;
        this.adresse = adresse;
        this.port = port;

        center.setLayout(new BorderLayout());

        center_sub = new JPanel();
        but_retour = new Button("<< retour au parametre de connexion");
        gridLayout = new GridLayout(1,1);
        center_sub.setLayout(gridLayout);

        label = new JLabel(string_message_attente);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(Utiles.font_bold);
        animLabel = new AnimLabel(label,300,new String[]{string_message_attente,string_message_attente+".",string_message_attente+"..",string_message_attente+"...",
                                                                        string_message_attente+"....",string_message_attente+"....."});
        animLabel.start();
        center_sub.add(label);

        entete.setTitre(titre);
        center.add(center_sub,BorderLayout.CENTER);
        center.add(but_retour,BorderLayout.SOUTH);

        jeuOnline = new JeuOnline(this,adresse,port);

        initEvent();
    }
    public void initEvent(){
        but_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    jeuOnline.getClientReseau().envoiBye();
                }catch (NullPointerException npe){}
                Main.AllerA(connexion_precedent);
            }
        });
    }

    public AnimLabel getAnimLabel() {
        return animLabel;
    }

    public void setAnimLabel(AnimLabel animLabel) {
        this.animLabel = animLabel;
    }
    public void Echec(){
        animLabel.run = false;
        animLabel.setMessages(new String[]{"Echec de la connexion au serveur"});
        label.setText("Echec de la connexion au serveur");
        label.setForeground(Color.RED);
    }
    public void retour(){
        Main.AllerA(connexion_precedent);
    }
}
