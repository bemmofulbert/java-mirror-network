package EXO5;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GererClick implements ActionListener {
    private JButton buts[];
    private JButton but_elu;
    private Cli clientReseau;
    public GererClick(JButton[] buts,JButton but_elu, Cli clientReseau) {
        this.buts = buts;
        this.but_elu = but_elu;
        this.clientReseau = clientReseau;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for(int i=0; i< buts.length;i++) {
            //but[i].setIcon(null);
            if (but_elu == buts[i]){
                clientReseau.envoiCoord(i);
                break;
                //EXO5.setPos(i);//but[i].setIcon(EXO5.getImg());
            }

        }
        //but_elu.setIcon(EXO5.getImg());
    }
}
