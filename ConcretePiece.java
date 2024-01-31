import java.util.ArrayList;

public abstract class ConcretePiece implements Piece {


    private int number;
    private String name;
    private String type;
    //private int kills;
    private ConcretePlayer owner;
    // private Position p;
    private Position startP = new Position(0, 0);
    private ArrayList<Position> steps = new ArrayList<Position>();



    public void setOwner(ConcretePlayer player) {
        this.owner = player;
    }

    @Override
    public ConcretePlayer getOwner() {
        return owner;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    @Override
    public String getType() {
        return type;
    }
    //public Position getP() { return p;}

    //public void setP(Position p) { this.p = p;}


    public void setStartPx(int x) {
        this.startP.setX(x);
    }
    public void setStartPy(int y) {
        this.startP.setY(y);
    }

    public Position getStartP() {
        return startP;
    }
    public int getstartPx(){return this.startP.getX();}
    public int getstartPy(){return this.startP.getY();}
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Position> geSteps() {
       return this.steps;
    }

    public void addSteps(Position s) {
        this.steps.add(s);
    }

    public void printSteps() {
        if (steps.size() > 1) {
            System.out.print(this.name + ": [");
            for (int i = 0; i < steps.size() - 1; i++) {
                System.out.print("(" + steps.get(i).getX() + ", " + steps.get(i).getY() + "), ");
            }
            System.out.print("(" + steps.get(steps.size() - 1).getX() + ", " + steps.get(steps.size() - 1).getY() + ")");
            System.out.println("]");
        }
    }

//    public void printkills() {
//        if (this.kills > 0) {
//            System.out.println(this.name + ": " + this.kills + " kills");
//        }
//    }

    public int distance() {
        int squars = 0;
        for (int i = 1; i < this.steps.size(); i++) {
            if (this.steps.get(i - 1).getX() == this.steps.get(i).getX()) {
                squars += Math.abs(this.steps.get(i - 1).getY() - this.steps.get(i).getY());
            } else {
                squars += Math.abs(this.steps.get(i - 1).getX() - this.steps.get(i).getX());
            }
        }
        return squars;
    }

    public void printDistance() {
        if (this.distance() >= 1) {
            System.out.println(this.name + ": " + this.distance() + " squares");
        }

    }

}
