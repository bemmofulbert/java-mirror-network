package EXO5.Vue.Menu.Mutil;

import EXO5.Main;
import EXO5.Serveur.Client;
import EXO5.Serveur.Serveur;
import EXO5.Vue.Button;
import EXO5.Vue.Menu.Accueil;
import EXO5.Vue.Menu.ChoixColRow;
import EXO5.Vue.Menu.MenuMin;
import EXO5.Vue.ServeurPret;
import EXO5.Vue.Utiles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mode extends MenuMin {
	private JPanel  zoneButs,zoneControl;
	private Button but_serveur;
	private Button but_client;
	private Button but_retour;

	private BorderLayout borderLayout;
	private GridLayout gl, gl2;
	private GridBagConstraints gs;

	private String titre = "Choix du Mode";
	private String nomBut_client = "Client";
	private String nomBut_serveur = "Serveur";

	private Accueil accueil;
	private Connexion client_suivant;
	private ChoixColRow serveur_suivant;
	public Mode(Accueil accueil){
		super();
		this.accueil = accueil;
		client_suivant = new Connexion(this);
		serveur_suivant = new ChoixColRow(this,ChoixColRow.ONLINE);

		entete.setTitre(titre);
		borderLayout = new BorderLayout();
		center.setLayout(borderLayout);

		//bouton-retour
		zoneControl = new JPanel(new GridLayout(2,1));
		but_retour = new Button("<< retour au menu principale");
		zoneControl.add(new JPanel());
		zoneControl.add(but_retour);

		//client-serveur
		zoneButs = new JPanel();

		this.gl2 = new GridLayout(1,2);

		zoneButs.setLayout(gl2);
		but_serveur = new Button(nomBut_serveur,new ImageIcon("images/serveur.png"));
		but_client = new Button(nomBut_client, new ImageIcon("images/client.png"));
		zoneButs.add(but_serveur);
		zoneButs.add(but_client);

		center.add(zoneControl,BorderLayout.SOUTH);
		center.add(zoneButs,BorderLayout.CENTER);
		initEvent();
	}
	private void initEvent(){
		but_retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				but_retour.setBackground(Utiles.green);
				Button.deZoomMenu(but_retour);
				Main.AllerA(accueil);
			}
		});
		but_client.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				but_client.setBackground(Utiles.green);
				Button.deZoomMenu(but_client);
				Main.AllerA(client_suivant);
			}
		});
		but_serveur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				but_serveur.setBackground(Utiles.green);
				Button.deZoomMenu(but_serveur);
				Main.AllerA(serveur_suivant);
			}
		});
	}

}