import java.util.ArrayList;

public  class Position {
    public int x;
    public int y;
    public ArrayList<String> listS=new ArrayList<String>();
    Position(int x,int y){
        this.x=x;
        this.y=y;

    }

    public int getX() {return x;}

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
     public void addP(String st){
        if(!listS.contains(st)){
        this.listS.add(st);}
     }

}
