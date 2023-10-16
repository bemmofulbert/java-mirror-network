package EXO5.Vue.Menu.Mutil;

import EXO5.Main;
import EXO5.Vue.Button;
import EXO5.Vue.Menu.MenuMin;
import EXO5.Vue.Utiles;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Connexion extends MenuMin {

	private JLabel lab_ip,lab_port;
	private JSpinner[] spins_ip;
	private JSpinner spin_port;
	private JPanel[] cont;
	private JButton but_connex,but_retour;
	private String titre = "Connexion";
	private String string_ip = "Adresse IP du serveur :";
	private String string_port = "Numero de Port du serveur :";
	private Mode mode_precedent;
	private ConnexionAttente connexion_suivant;
	public Connexion(Mode mode_precedent){
			super();
			entete.setTitre(titre);
			this.mode_precedent = mode_precedent;

			this.cont = new JPanel[4];
			lab_ip = new JLabel(string_ip);lab_ip.setFont(Utiles.font);
			lab_port = new JLabel(string_port);lab_port.setFont(Utiles.font);
			spins_ip = new JSpinner[4];
			spin_port = new JSpinner(new SpinnerNumberModel(23000,1025,65535,1));spin_port.setFont(Utiles.font);

			//Creation de la ligne 1
			this.cont[0] = new JPanel();
			this.cont[0].setLayout(new GridLayout(1, 2));
			this.cont[0].add(lab_ip);
				JPanel pane_ip = new JPanel(new GridLayout(1,7),true);
				for(int i=0;i<4;i++) {spins_ip[i] = new JSpinner(new SpinnerNumberModel(0,0,255,1));spins_ip[i].setFont(Utiles.font);}
				spins_ip[0].setValue(127);spins_ip[3].setValue(1);
				pane_ip.add(spins_ip[0]);
				for (int j=1;j<4;j++) {pane_ip.add(new JPanel());pane_ip.add(spins_ip[j]);}
			this.cont[0].add(pane_ip);

			//Creation de la ligne 2
			this.cont[1] = new JPanel();
			this.cont[1].setLayout(new GridLayout(1, 2));
			this.cont[1].add(lab_port);
			this.cont[1].add(spin_port);

			center.setLayout(new GridLayout(9,1));

			//Ajouts
			center.add(new JPanel());
			center.add(new JPanel());

			center.add(this.cont[0]);

			center.add(new JPanel());
			center.add(new JPanel());

			center.add(this.cont[1]);

			center.add(new JPanel());
			center.add(new JPanel());

			JPanel panelEnd =  new JPanel(new GridLayout(1,2));
			but_connex = new Button("Connexion");
			but_retour = new Button("<< retour");
			panelEnd.add(but_retour);
			panelEnd.add(but_connex);

			center.add(panelEnd);
			initEvent();
	}
	private void initEvent() {
		but_retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				but_retour.setBackground(Utiles.green);
				Button.deZoomMenu(but_retour);
				Main.AllerA(mode_precedent);
			}
		});
		but_connex.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				but_connex.setBackground(Utiles.green);
				Button.deZoomMenu(but_connex);
				connexion_suivant = new ConnexionAttente(Connexion.this,getAdresse(),getPort());
				Main.AllerA(connexion_suivant);
			}
		});
	}

	public String getAdresse(){
		String adr ;
		adr = spins_ip[0].getValue().toString();
		for (int i=1;i<4;i++){
			adr+="."+spins_ip[i].getValue().toString();
		}
		//System.out.println("get ============== "+ adr);
		return adr;
	}
	public int getPort(){
		return Integer.parseInt(spin_port.getValue().toString());
	}

}