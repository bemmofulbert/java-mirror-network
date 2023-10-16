package EXO5.Serveur;

import java.io.Serializable;

public class Message implements Serializable {
    public static final int TYPE_COORD = 0;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_PRESENTATION = 2;
    public static final int TYPE_PARAMETER = 3;
    public static final int TYPE_END = 4;
    public static final int TYPE_START = 5;
    public static final int TYPE_BYE = 6;
    private int type;
    private String text;
    private int coord;
    private boolean aToi=false;
    int row=1,col=1;

    public Message(int type, String text) {
        this.text = text;
        this.type = type;
    }

    public Message(int coord, boolean aToi) {
        this.coord = coord;
        this.aToi = aToi;
        this.type = TYPE_COORD;
    }
    public Message(int col, int row){
        this.type = Message.TYPE_PARAMETER;
        this.col = col;
        this.row = row;
    }
    public Message(int type){
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCoord() {
        return coord;
    }

    public void setCoord(int coord_X) {
        this.coord = coord_X;
    }

    public boolean isaToi() {
        return aToi;
    }

    public void setaToi(boolean aToi) {
        this.aToi = aToi;
    }

    @Override
    public String toString() {
        if (type == TYPE_TEXT)
            return "Message{" +
                    "type=" + type +
                    ", text='" + text + '\'' +
                    '}';
        else return "Message{" +
                "type=" + type +
                ", coord=" + coord +
                ", aToi=" + aToi +
                '}';
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
