package EXO5.Vue.Animation;

import javax.swing.*;

public class AnimLabel extends Thread {
    JLabel label;
    String[] messages;
    int millis;
    public boolean run = true;
    public AnimLabel(JLabel label,int millis,String[] messages){
        this.label = label;
        this.messages = messages;
        this.millis = millis;
    }

    @Override
    public void run() {
        super.run();
        while (run) {
            for (int i =0;i<messages.length;i++) {
                label.setText(messages[i]);
                try {
                    sleep(millis);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public int getMillis() {
        return millis;
    }

    public void setMillis(int millis) {
        this.millis = millis;
    }

}
