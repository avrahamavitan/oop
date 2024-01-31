import java.util.ArrayList;

public class Position {
    private int x;
    private int y;
    private ArrayList<String> list_Pieces = new ArrayList<String>();//רשימה של כל החיילים שדרכו על הפוזישן

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addP(String st) {
        if (!this.list_Pieces.contains(st)) {
            this.list_Pieces.add(st);
        }
    }

    public ArrayList<String> getList_Pieces() {
        return list_Pieces;
    }

    public void setList_Pieces(ArrayList<String> list_Pieces) {
        this.list_Pieces = list_Pieces;
    }
}
