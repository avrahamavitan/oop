public class ConcretePlayer  implements Player{
    private  boolean isplayer1;
    private int wins=0;
    private ConcretePiece[] team;
    private boolean winNow;
    ConcretePlayer(boolean b,ConcretePiece[] team){
        this.team=team;
        this.isplayer1=b;
    }
    @Override
    public boolean isPlayerOne() {
        return this.isplayer1;
    }
    public void setWins(int win) {
        this.wins=win;
    }
    @Override
    public int getWins() {
        return this.wins;
    }
    public void setWinNow(boolean b){ this.winNow=b;}
    public boolean getWinnow(){return this.winNow;}
    public void set_team(int i, ConcretePiece piece){this.team[i]=piece;}
    public ConcretePiece[] get_team(){return this.team;}
}
