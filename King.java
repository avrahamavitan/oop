

public class King extends ConcretePiece{

    King(ConcretePlayer player,int number,int x,int y){
        this.setNumber(number);
        this.setName("K"+number);
        this.setStartPx(x);
        this.setStartPy(y);
        this.addSteps(new Position(x,y));
        super.setType("♔");
        super.setOwner(player);

    }


}
