package EXO5;

import EXO5.Serveur.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cli extends Thread{
    private InetAddress adrServeur;
    private Socket sock;
    private ObjectInputStream bufReader;
    private ObjectOutputStream bufWriter;
    private int port;
    private String nomServeur;
    private String pseudo;
    private JeuOnline jeuOnline;
    private boolean run = true;
    public Cli (JeuOnline jeuOnline) {
        this.jeuOnline = jeuOnline;

        try {
            pseudo = InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException uhe){
            pseudo = "pseudo";
        }
        nomServeur  = "localhost";
        port = 23000;
        lancerClient();
    }
    public Cli (JeuOnline jeuOnline,String nomServeur,int port) {
        this.jeuOnline = jeuOnline;
        this.nomServeur = nomServeur;
        this.port = port;

        try {
            pseudo = InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException uhe){
            pseudo = "pseudo";
        }
        lancerClient();
    }

    public void lancerClient () {
        try {

            adrServeur = InetAddress.getByName(nomServeur);
            System.out.println("Connexion a : "+nomServeur);
            //adrServeur = InetAddress.getByName("192.168.60.217");
            //adrServeur = InetAddress.getByName("kengne-dynabook-B45-D");
            sock = new Socket(adrServeur, port);

            bufWriter = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            bufWriter.flush();
            bufReader = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));

            //presentation
            Message message = new Message(Message.TYPE_PRESENTATION,pseudo);
            bufWriter.writeObject(message);
            bufWriter.flush();

        }catch (NullPointerException npe){
            if(jeuOnline.fen_connexion != null) jeuOnline.fen_connexion.Echec();
        }
        catch (UnknownHostException uhe) {
            System.out.println("hote inconnu");
            if(jeuOnline.fen_connexion != null) jeuOnline.fen_connexion.Echec();
        }catch (SocketException se) {
            System.out.println("erreur de socket");
            if(jeuOnline.fen_connexion != null) jeuOnline.fen_connexion.Echec();
        }catch (IOException ioe) {
            System.out.println("probleme d'envoie ou de reception");
            if(jeuOnline.fen_connexion != null) jeuOnline.fen_connexion.Echec();
        }
        start();
    }

    //recevoir coordonnees
    @Override
    public void run() {

            try {
                while (run) {
                    Message message = (Message) bufReader.readObject();
                    //sleep(5);
                    System.out.println("Client: reception... " +message.isaToi());
                    switch (message.getType()) {
                        case (Message.TYPE_COORD):
                            jeuOnline.setPos(message.getCoord());
                            if(message.isaToi()) jeuOnline.show_aToi();
                            else jeuOnline.show_pasToi();
                            System.out.println("Recu :" + message.getCoord());
                            break;
                        case (Message.TYPE_TEXT) :
                            System.out.println("Message : ====" + message.getText() + "===");
                            break;
                        case (Message.TYPE_PRESENTATION) :
                            break;
                        case (Message.TYPE_PARAMETER) :
                            jeuOnline.setCol(message.getCol());
                            jeuOnline.setRow(message.getRow());
                            if(message.isaToi()) jeuOnline.show_aToi();
                            else jeuOnline.show_pasToi();
                            break;
                        case (Message.TYPE_END):
                            close();
                            break;
                        case (Message.TYPE_START):
                            if (jeuOnline.fen_connexion != null) {Main.fra.setContentPane(jeuOnline.getContentPane());Main.fra.pack();}
                            break;
                        default:
                            System.out.println("Message : ====" + message.getText() + "===");
                            break;
                    }
                }
            }
            catch (IOException ioe) {
                System.out.println("flux ferme");
            }catch (ClassNotFoundException cnfe){
                cnfe.printStackTrace();
            }
    }

    // envoyer des coordonnees
    public void envoiCoord (int pos) {
        try {
            bufWriter.writeObject(new Message(pos, false));
            bufWriter.flush();
            System.out.println("client envoie");
        }catch (IOException ie){
            System.out.println("Client i/o problem");
            ie.printStackTrace();
        }
    }
    public void envoiBye () {
        try {
            bufWriter.writeObject(new Message(Message.TYPE_BYE));
            bufWriter.flush();
            System.out.println("client envoie bye");
        }catch (IOException ie){
            System.out.println("Client i/o problem");
            ie.printStackTrace();
        }
    }

    public void setPort(int port) {
        try{
            this.port = port;
            sock = new Socket(adrServeur, port);
        }catch (SocketException se) {
            System.out.println("erreur de socket");
        }catch (IOException ioe) {
            System.out.println("probleme d'envoie ou de reception");
        }
    }
    public void setAdresse(String nomServeur) {
        try{
            this.nomServeur = nomServeur;
            adrServeur = InetAddress.getByName(nomServeur);
            sock = new Socket(adrServeur, port);
        }catch (SocketException se) {
            System.out.println("erreur de socket");
        }catch (IOException ioe) {
            System.out.println("probleme d'envoie ou de reception");
        }
    }

    public void close(){
        try {
            run =false;
            bufReader.close();
            bufWriter.close();
            sock.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }
}
