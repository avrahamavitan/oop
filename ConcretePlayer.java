public class ConcretePlayer  implements Player{

    public boolean b1;
    public int wins=0;
    public ConcretePiece[] team;
    public boolean win;
    ConcretePlayer(boolean b,ConcretePiece[] team){
        this.team=team;
        this.b1=b;
    }
    @Override
    public boolean isPlayerOne() {
        return this.b1;
    }
    public void setWins(int win) {
        wins+=win;
    }
    @Override
    public int getWins() {
        return wins;
    }
    public ConcretePiece[]  getTeam() {
        return team;
    }
    public void winNow(boolean b){
        this.win=b;
    }
}
