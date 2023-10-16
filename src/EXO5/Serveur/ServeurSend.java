package EXO5.Serveur;

import java.io.IOException;
import java.net.SocketException;

import static EXO5.Serveur.Serveur.clients;

public class ServeurSend extends Thread{
    Message message;

    public ServeurSend (Message message) {
        this.message = message;
        start();
    }

    public void run() {
            try{
                for(int i=0,c=clients.size();i<c;i++){
                    if(i == Serveur.jeton) message.setaToi(true);
                    else message.setaToi(false);

                    clients.get(i).getWriter().writeObject(message);
                    clients.get(i).getWriter().flush();
                    System.out.println("serveur redistribution : "+i);
                }

            }catch (SocketException se){
                System.out.println("serveur ferme");
            }
            catch(IOException ioe) {
                System.out.println("erreur d'entree/sortie");
                ioe.printStackTrace();
            }catch (IndexOutOfBoundsException ioobe){}
    }

}
