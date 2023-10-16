package EXO5.Serveur;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private String Pseudo;
    private JButton butDePresentation;

    public Client(Socket socket, ObjectInputStream reader, ObjectOutputStream writer) {
        this.socket = socket;
        this.reader = reader;
        this.writer = writer;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getReader() {
        return reader;
    }

    public void setReader(ObjectInputStream reader) {
        this.reader = reader;
    }

    public ObjectOutputStream getWriter() {
        return writer;
    }

    public void setWriter(ObjectOutputStream writer) {
        this.writer = writer;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public void setPseudo(String pseudo) {
        Pseudo = pseudo;
    }

    public void close() {
        try {
            reader.close();
            writer.close();
            socket.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    //peut retourner null
    public JButton getButDePresentation() {
        return butDePresentation;
    }

    public void setButDePresentation(JButton butDePresentation) {
        this.butDePresentation = butDePresentation;
    }
}
