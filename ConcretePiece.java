import java.util.ArrayList;

public abstract class ConcretePiece implements Piece{

   public Player Owner;
  public int number;
   String name;
    String type;
    public int kills;
    public ConcretePlayer player;
    public Position p;
    public Position startP=new Position(0,0);
    public ArrayList<Position> steps=new ArrayList<Position>();


    //public ConcretePiece(){}
     public  void setOwner(ConcretePlayer player){
         this.player=player;
     }
     @Override
     public ConcretePlayer getOwner() {
         return player;
     }
     public  void setType(String Type){
        this.type=Type;
     }
     @Override
     public String getType() {
         return type;
     }

     public Position getP() {
         return p;
     }
     public void setP(Position p) {
         this.p = p;
     }
    public void addSteps(int x,int y){
    Position s=new Position(x,y);
    this.steps.add(s);
     }
    public void printSteps(){
        if (steps.size()>1) {
            System.out.print(this.name + ": [");
            for (int i = 0; i < steps.size()-1; i++) {
                System.out.print("(" + steps.get(i).x + ", " + steps.get(i).y + "), ");
            }
            System.out.print("(" + steps.get(steps.size()-1).x + ", " + steps.get(steps.size()-1).y + ")");
            System.out.println("]");
        }
    }
    public void printkills(){
         if (this.kills>0){
         System.out.println(this.name+": "+this.kills+" kills");}
    }
    public int distance(){
         int squars=0;
         for (int i=1;i<this.steps.size();i++){
             if(this.steps.get(i-1).x==this.steps.get(i).x){
                 squars+=Math.abs(this.steps.get(i-1).y-this.steps.get(i).y);}
             else { squars+=Math.abs(this.steps.get(i-1).x-this.steps.get(i).x);}
         }
         return squars;
    }
    public void printDistance(){
         if (this.distance()>=1){
        System.out.println(this.name+": "+this.distance()+" squares");}

    }
 }
