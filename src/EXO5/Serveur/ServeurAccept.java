package EXO5.Serveur;

import EXO5.Vue.ServeurPret;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import static EXO5.Serveur.Serveur.clients;

public class ServeurAccept extends Thread{
    ServerSocket serverSocket;
    Socket socketTemp;
    Serveur serveur;
    ServeurPret serveurPret;
    ServeurAccept (Serveur serveur, ServeurPret serveurPret) {
        this.serveur = serveur;
        this.serverSocket = serveur.getServerSocket();
        this.serveurPret = serveurPret;
        start();
    }
    @Override
    public void run() {

        while (serveur.run) {
            try{
                socketTemp = serverSocket.accept();

                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socketTemp.getInputStream()));
                ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socketTemp.getOutputStream()));
                oos.flush();

                    Client nouveauClient= new Client(socketTemp
                            ,ois
                            ,oos);

                System.out.println("connecte.");

                //envoyer les parametres
                new ServeurSend(new Message(serveur.row,serveur.col));
                    Serveur.clients.add(nouveauClient);
                new ServeurReceiv(serveurPret,Serveur.clients.size()-1);


            }catch (SocketException se){
                System.out.println("serveur ferme");
            }
            catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
