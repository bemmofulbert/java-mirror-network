package EXO5.Serveur;

import EXO5.Vue.ServeurPret;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;

import static EXO5.Serveur.Serveur.clients;

public class ServeurReceiv extends Thread{
    final int index;
    ServeurPret serveurPret;
    ServeurReceiv (ServeurPret serveurPret,int index) {
        this.serveurPret = serveurPret;
        this.index = index;
        start();
    }

    public void run() {
        try{
            while (serveurPret.getServeur().run) {

                Message message = (Message) Serveur.clients.get(index).getReader().readObject();
                System.out.println("Serveur Reception de :" + index);

                if (message.getType() == Message.TYPE_PRESENTATION){
                    clients.get(index).setPseudo(message.getText());
                    serveurPret.addClient(message.getText(), clients.get(index));
                }
                if(message.getType() == Message.TYPE_BYE){
                    serveurPret.removeClient(clients.get(index));
                    clients.get(index).close();
                    clients.remove(index);
                    Serveur.jeton--;
                    if(Serveur.jeton >= clients.size() || Serveur.jeton <0) Serveur.jeton =0;
                    new ServeurSend(new Message(serveurPret.getJeuOnline().pos,false));
                    break;
                }

                onParameter();

                new ServeurSend(message);
            }

        }catch (SocketException se){
            System.out.println("socket ferme");
        }catch (EOFException eof){}
        catch(IOException ioe) {
            System.out.println("erreur d'entree/sortie");
            ioe.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("classe inconnu");
            cnfe.printStackTrace();
        }

    }
    public synchronized void onParameter() {
        System.out.println("donnees: "+index+" - "+Serveur.jeton);
        Serveur.jeton++;
        if(Serveur.jeton >= clients.size() || Serveur.jeton <0) Serveur.jeton =0;
    }
}
