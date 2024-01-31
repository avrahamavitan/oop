public class Pawn extends ConcretePiece {


    private int kills;

    Pawn(ConcretePlayer player, int number, int x, int y) {

        this.setOwner(player);
        this.setNumber(number);
        this.setStartPx(x);
        this.setStartPy(y);
        this.addSteps(new Position(x,y));
        if (player.isPlayerOne()) {
            this.setType("♙");
            this.setName("D" + this.getNumber());
        } else {
            this.setType("♟");
            this.setName("A" + this.getNumber());
        }
    }
    public void printkills() {
        if (this.kills > 0) {
            System.out.println(this.getName() + ": " + this.kills + " kills");
        }
    }
    public void setKills(int kills) {
        this.kills += kills;
    }

    public int getKills() {
        return kills;
    }
}
