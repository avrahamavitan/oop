public class Pawn extends ConcretePiece{

    ConcretePlayer player;
    Pawn(ConcretePlayer player,int number,int x,int y){
        super.setOwner(player);
        this.player=player;
        this.number=number;
        this.startP.x=x;
        this.startP.y=y;
        this.addSteps(x,y);
if(player.isPlayerOne()){
    super.setType("♙");
    this.name="D"+this.number;
}
else {super.setType("♟");
    this.name="A"+this.number;
}
    }

}
