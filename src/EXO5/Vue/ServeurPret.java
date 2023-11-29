package EXO5.Vue;

import EXO5.JeuOnline;
import EXO5.Serveur.*;
import EXO5.Vue.Animation.AnimColor;
import EXO5.Vue.Animation.AnimLabel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



import static EXO5.Vue.Utiles.*;

public class ServeurPret extends JPanel{
    private BorderLayout borderLay_Principale;

    private JPanel panel_presentation,panel_presentation_ip,panel_presentation_port;
    private FlowLayout flowLay_presentation_ip,flowLay_presentation_port;
    private BoxLayout boxLay_presentation;
    private JLabel lab1_ip,lab_ip,lab1_port,lab_port;
    private JPanel scroll_clients;
    private JPanel scroll_clients_panel;
    private JLabel entete; String entete_string = "En Attente de Joueurs Distants";
    private JButton button_jouer;
    private JButton button_retour;
    private JLabel lab_nombreClient;
    int nombreClient = 0;
    private Serveur serveur;
    int port = 23000;
    JeuOnline jeuOnline;
    int row=1,col=1;
    JPanel panel_precedent;
    public  ServeurPret (JPanel panel_precedent,int row,int col){
        super();
        this.panel_precedent = panel_precedent;
        this.row = row;
        this.col = col;

        borderLay_Principale = new BorderLayout();
        setLayout(borderLay_Principale);

        lab_nombreClient = new JLabel("Aucun Client ! ");

        //Presentation du serveur
        lab1_ip = new JLabel("IP : ");lab1_ip.setFont(font);lab1_ip.setForeground(white);
        lab_ip = new JLabel("adresse_serveur");lab_ip.setFont(font_bold);lab_ip.setForeground(green);
        lab1_port = new JLabel(" Port :");lab1_port.setFont(font);lab1_port.setForeground(white);
        lab_port = new JLabel("23000");lab_port.setFont(font_bold);lab_port.setForeground(green);

        panel_presentation = new JPanel();
        panel_presentation_ip = new JPanel();panel_presentation_ip.setBackground(black);
        panel_presentation_port = new JPanel();panel_presentation_port.setBackground(black);

        boxLay_presentation = new BoxLayout(panel_presentation,BoxLayout.X_AXIS);
        flowLay_presentation_ip = new FlowLayout(FlowLayout.CENTER);
        flowLay_presentation_port = new FlowLayout(FlowLayout.CENTER);

        panel_presentation.setLayout(boxLay_presentation);
        panel_presentation_ip.setLayout(flowLay_presentation_ip);
        panel_presentation_port.setLayout(flowLay_presentation_port);

        panel_presentation.add(panel_presentation_ip);panel_presentation.add(panel_presentation_port);
        panel_presentation_ip.add(lab1_ip);
        panel_presentation_ip.add(lab_ip);
        panel_presentation_port.add(lab1_port);
        panel_presentation_port.add(lab_port);

        //Preaentation Client
        scroll_clients = new JPanel();
        BoxLayout box_entete = new BoxLayout(scroll_clients,BoxLayout.Y_AXIS);
        scroll_clients.setLayout(box_entete);
        scroll_clients_panel = new JPanel(new GridLayout(10,1));

            JPanel panel_entete = new JPanel();
            BoxLayout boxLayout = new BoxLayout(panel_entete,BoxLayout.X_AXIS);
            panel_entete.setLayout(boxLayout);
            entete = new JLabel(entete_string);new AnimLabel(entete,800,new String[]{entete_string,entete_string+".",entete_string+"..",entete_string+"...",entete_string+"....",entete_string+"....."}).start();
            panel_entete.setBackground(black);
            entete.setForeground(new Color(255,255,255));
            entete.setFont(font);
            panel_entete.add(entete);
            //JScrollPane scrollPane = new JScrollPane();
            //scrollPane.setViewport(entete);
        scroll_clients.add(panel_entete);
        scroll_clients.add(scroll_clients_panel);

        JPanel panel_jouer = new JPanel();
        GridLayout box_jouer = new GridLayout(4,1);
        panel_jouer.setLayout(box_jouer);
        //Nombre Clients
        lab_nombreClient = new JLabel("Nombre de Clients = "+nombreClient);
        panel_jouer.add(lab_nombreClient);

        //Bouton JOUER

        button_jouer = new JButton("Cliquez ici pour commencer le Jeu");button_jouer.setFont(font_button_bold);
        button_jouer.setIcon(new ImageIcon("images/manette.png"));
        button_jouer.setBackground(new Color(255,255,0));button_jouer.setForeground(black);
            Color[] colors_button_jouer = new Color[]{new Color(255,255,0),new Color(255,0,255),new Color(0,255,255)};
            AnimColor animColor_button_jouer = new AnimColor(button_jouer,AnimColor.BACKGROUND,600,colors_button_jouer);

            button_jouer.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    new ServeurSend(new Message(Message.TYPE_START));
                    jeuOnline.show();
                    jeuOnline.buts[0].doClick();
                }
                @Override
                public void mousePressed(MouseEvent mouseEvent) {}
                @Override
                public void mouseReleased(MouseEvent mouseEvent) {}
                @Override
                public void mouseEntered(MouseEvent mouseEvent) {
                    button_jouer.setFont(new Font("Ubuntu",Font.BOLD,30));
                    animColor_button_jouer.setMillis(1200);
                }
                @Override
                public void mouseExited(MouseEvent mouseEvent) {
                    button_jouer.setFont(font_button_bold);
                    animColor_button_jouer.setMillis(900);
                }
            });
        panel_jouer.add(button_jouer);

        //button retour
        button_retour = new Button("<< Retour a la configuration");
        //button_retour.setPreferredSize(new Dimension(70,70));
        button_retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ServeurSend(new Message(Message.TYPE_END));
                jeuOnline.setVisible(false);
                serveur.close();
                EXO5.Main.AllerA(panel_precedent);
            }
        });
        panel_jouer.add(new JPanel());
        panel_jouer.add(button_retour);

        add(panel_presentation,BorderLayout.NORTH);
        add(scroll_clients,BorderLayout.CENTER);
        add(panel_jouer,BorderLayout.SOUTH);

        //Serveur
        this.serveur = new Serveur(port,this,row,col);
        lab_ip.setText(serveur.getAdresse());

        //Client
        jeuOnline = new JeuOnline();
    }
    public void addClient(String nomClient, Client client) {
        JButton button = new JButton(new ImageIcon("images/user.png"));
        client.setButDePresentation(button);

        button.setFont(font_button);
        button.setText(nomClient);
        button.setBackground(new Color(189,189,189));
        nombreClient++;
        if(nombreClient % 2 ==0) button.setBackground(new Color(139,139,139));
        lab_nombreClient.setText("Nombre de Clients = "+nombreClient);

        scroll_clients_panel.add(button);
        client.setButDePresentation(button);
    }
    public void removeClient(Client client){
        scroll_clients_panel.remove(client.getButDePresentation());
        scroll_clients_panel.repaint();
        //if (client.getButDePresentation() == null) System.out.println("-------------------suppresion reussie");
        nombreClient--;
        lab_nombreClient.setText("Nombre de Clients = "+nombreClient);
    }

    public Serveur getServeur() {
        return serveur;
    }

    public JeuOnline getJeuOnline() {
        return jeuOnline;
    }
}
