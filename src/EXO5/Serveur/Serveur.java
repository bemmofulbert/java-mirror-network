package EXO5.Serveur;


import EXO5.Vue.ServeurPret;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class Serveur{
    int port;
    public static ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket ;
    ServeurAccept serveurAccept;
    ServeurPret serveurPret;
    public static int jeton =0;
    int row,col;
    boolean run = true;
    public Serveur (int port,ServeurPret serveurPret,int row,int col) {
        this.port = port;
        this.serveurPret = serveurPret;
        this.row = row;
        this.col = col;

        try {
            serverSocket = new ServerSocket(port);
            serveurAccept = new ServeurAccept(this,serveurPret);

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void close(){
        run = false;
        try {
            serverSocket.close();
        }catch (NullPointerException npe){}
        catch (IOException ioe){
            System.err.println("probleme a la fermeture du serveur : ");
            ioe.printStackTrace();
        }
        for(int i=0,c=clients.size();i<c;i++){
            clients.get(i).close();
        }
        clients.clear();
    }
    public int getPort(){ return port;}
    public String getAdresse() {
        //String adr = new String();
        try {
            Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()){
                NetworkInterface n = e.nextElement();
                String nomInterface = n.getName();
                Enumeration<InetAddress> ee = n.getInetAddresses();
                //System.out.println(nomInterface);
                while (ee.hasMoreElements()){
                    InetAddress i = ee.nextElement();
                    String adr = i.getHostAddress();
                    String c = ""+adr.charAt(0);
                    try {
                        Integer.parseInt(c);

                        if (nomInterface.startsWith("wl"))
                            return i.getHostAddress();
                    }catch (NumberFormatException nfe){
                        //System.out.println("wl~");
                    }
                    //System.out.println("adr : " + i.getHostAddress());

                    }
            }
            return InetAddress.getLocalHost().getHostAddress();
        }catch (SocketException se){
            return "localhost";
        }catch (UnknownHostException uhe){
            return "localhost";
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}

