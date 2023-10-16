package EXO5.Vue;

import EXO5.Vue.Animation.AnimLabel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton {
    AnimLabel animLabel;
    String text;
    public Button(String text) {
        super();
        setFont(Utiles.font_button);
        setBackground(Utiles.green);
        setText(text);
        this.text = text;

        initEvent();
    }
    public Button(ImageIcon icon) {
        super();
        setFont(Utiles.font_button);
        setBackground(Utiles.green);
        setIcon(icon);

        initEvent();
    }
    private void initEvent() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                deZoomMenu(Button.this);
                Button.this.setBackground(Utiles.green);
            }
            @Override
            public void mousePressed(MouseEvent mouseEvent) {}
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {}

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                zoomMenu(Button.this);
                Button.this.setBackground(Utiles.green_dark);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                deZoomMenu(Button.this);
                Button.this.setBackground(Utiles.green);
            }
        });
    }
    public static void zoomMenu(JButton but){
        but.setFont(Utiles.font_menu_hover);
    }
    public static void deZoomMenu(JButton but){
        but.setFont(Utiles.font_button);
    }
}

