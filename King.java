

public class King extends ConcretePiece{
    ConcretePlayer player;
    King(ConcretePlayer player,int number,int x,int y){
        this.player=player;
        this.number=number;
        this.name="K"+number;
        this.startP.x=x;
        this.startP.y=y;

        this.addSteps(x,y);
        super.setType("♔");
        super.setOwner(player);

    }


}
