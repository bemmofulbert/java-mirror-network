package EXO5.Vue;

import EXO5.Cli;
import EXO5.Serveur.Serveur;
import EXO5.Vue.Menu.Entete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tchat extends JPanel {
    private TextArea textArea;
    private Button but_send;
    private JTextField textField;
    private Entete entete;

    private GridLayout centerLay;
    private BorderLayout mainLayout;
    private GridLayout sudLay;
    private JPanel sud;
    private JPanel center;
    private Cli clientReseau;

    public Tchat(Cli clientReseau){
        super();
        this.clientReseau = clientReseau;

        mainLayout = new BorderLayout();
        centerLay = new GridLayout(2,1);
        sudLay = new GridLayout(2,1);
        setLayout(mainLayout);

        textArea = new TextArea(); textArea.setEditable(false);
        but_send = new Button("Envoyer");
        textField = new JTextField("Ecrire les messages a envoyes ici...");
        entete = new Entete("Tchat");
        sud = new JPanel(sudLay);

        //nord
        add(entete,BorderLayout.NORTH);

        //sud
        add(sud,BorderLayout.SOUTH);
        sud.add(textField);
        sud.add(but_send);

        //center
        center = new JPanel(new GridLayout(1,1));
        center.add(textArea);
        add(center);

        textArea.setPreferredSize(new Dimension(300,10));
        but_send.setMinimumSize(new Dimension(150,10));

        but_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (textField.getText().isEmpty()) return;
                String message = clientReseau.getPseudo()+ " : " + textField.getText();
                clientReseau.envoiMessage(message);
                textField.setText("");
            }
        });
    }
    public void addLine(String texte){
        textArea.append(texte+"\n");
    }
}
