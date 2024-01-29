import java.util.*;

public class GameLogic implements PlayableLogic {
    ConcretePiece[][] board = new ConcretePiece[11][11];
    public ArrayList<Position> listP = new ArrayList<Position>();
    public ConcretePiece[] team1 = new ConcretePiece[13];
    public ConcretePiece[] team2 = new ConcretePiece[24];
    ConcretePlayer player1 = new ConcretePlayer(true, team1);
    ConcretePlayer player2 = new ConcretePlayer(false, team2);
    boolean turn = false;

    GameLogic() {
        this.player1.team[0] = new Pawn(this.player1, 1, 5, 3);
        this.player1.team[1] = new Pawn(this.player1, 2, 4, 4);
        this.player1.team[2] = new Pawn(this.player1, 3, 5, 4);
        this.player1.team[3] = new Pawn(this.player1, 4, 6, 4);
        this.player1.team[4] = new Pawn(this.player1, 5, 3, 5);
        this.player1.team[5] = new Pawn(this.player1, 6, 4, 5);
        this.player1.team[6] = new King(this.player1, 7, 5, 5);
        this.player1.team[7] = new Pawn(this.player1, 8, 6, 5);
        this.player1.team[8] = new Pawn(this.player1, 9, 7, 5);
        this.player1.team[9] = new Pawn(this.player1, 10, 4, 6);
        this.player1.team[10] = new Pawn(this.player1, 11, 5, 6);
        this.player1.team[11] = new Pawn(this.player1, 12, 6, 6);
        this.player1.team[12] = new Pawn(this.player1, 13, 5, 7);


        this.player2.team[0] = new Pawn(this.player2, 1, 3, 0);
        this.player2.team[1] = new Pawn(this.player2, 2, 4, 0);
        this.player2.team[2] = new Pawn(this.player2, 3, 5, 0);
        this.player2.team[3] = new Pawn(this.player2, 4, 6, 0);
        this.player2.team[4] = new Pawn(this.player2, 5, 7, 0);
        this.player2.team[5] = new Pawn(this.player2, 6, 5, 1);
        this.player2.team[6] = new Pawn(this.player2, 7, 0, 3);
        this.player2.team[7] = new Pawn(this.player2, 9, 0, 4);
        this.player2.team[8] = new Pawn(this.player2, 11, 0, 5);
        this.player2.team[9] = new Pawn(this.player2, 15, 0, 6);
        this.player2.team[10] = new Pawn(this.player2, 17, 0, 7);
        this.player2.team[11] = new Pawn(this.player2, 12, 1, 5);
        this.player2.team[12] = new Pawn(this.player2, 8, 10, 3);
        this.player2.team[13] = new Pawn(this.player2, 10, 10, 4);
        this.player2.team[14] = new Pawn(this.player2, 14, 10, 5);
        this.player2.team[15] = new Pawn(this.player2, 16, 10, 6);
        this.player2.team[16] = new Pawn(this.player2, 18, 10, 7);
        this.player2.team[17] = new Pawn(this.player2, 13, 9, 5);
        this.player2.team[18] = new Pawn(this.player2, 20, 3, 10);
        this.player2.team[19] = new Pawn(this.player2, 21, 4, 10);
        this.player2.team[20] = new Pawn(this.player2, 22, 5, 10);
        this.player2.team[21] = new Pawn(this.player2, 23, 6, 10);
        this.player2.team[22] = new Pawn(this.player2, 24, 7, 10);
        this.player2.team[23] = new Pawn(this.player2, 19, 5, 9);

        setBoard();
    }

    void setBoard() {
        for (int i = 0; i < this.player1.team.length; i++) {
            this.board[this.player1.team[i].startP.x][this.player1.team[i].startP.y] = this.player1.team[i];
            Position p =new Position(player1.team[i].startP.x,player1.team[i].startP.y);
            set_list(p);
        }
        for (int i = 0; i < this.player2.team.length; i++) {
            this.board[this.player2.team[i].startP.x][this.player2.team[i].startP.y] = this.player2.team[i];
            Position p =new Position(player2.team[i].startP.x,player2.team[i].startP.y);
            set_list(p);
        }
    }

    @Override
    public boolean move(Position a, Position b) {
        if (this.board[a.x][a.y].player.b1 == turn) {
            if (this.board[b.x][b.y] == null) {
                if (this.board[a.x][a.y].getType().equals("♔")) {
                    if (((a.x == b.x) && column(a.x, a.y, b.y)) || ((a.y == b.y) && row(a.y, a.x, b.x))) {
                        this.board[b.x][b.y] = this.board[a.x][a.y];
                        this.board[a.x][a.y] = null;
                        this.board[b.x][b.y].addSteps(b.x, b.y);
                        set_list(b);
                        turn = !(turn);
                        isGameFinished();
                        return true;
                    }
                } else if (!(b.x == 0 && b.y == 0) && !(b.x == 0 && b.y == 10) && !(b.x == 10 && b.y == 0) && !(b.x == 10 && b.y == 10)) {
                    if (((a.x == b.x) && column(a.x, a.y, b.y)) || ((a.y == b.y) && row(a.y, a.x, b.x))) {
                        this.board[b.x][b.y] = this.board[a.x][a.y];
                        this.board[a.x][a.y] = null;
                        this.board[b.x][b.y].addSteps(b.x, b.y);
                        this.board[b.x][b.y].kills += kill(b);
                        set_list(b);
                        turn = !(turn);
                        isGameFinished();
                        return true;
                    }

                }
            }

        }

        return false;
    }

    public void set_list(Position b) {
        for (Position position : listP) {
            if (b.x == position.x) {
                if (b.y == position.y) {
                    position.addP(this.board[b.x][b.y].name);
                    break;
                }
            }
        }
        listP.add(b);
        b.addP(this.board[b.x][b.y].name);
    }

    public boolean row(int y, int a, int b) {

        for (int i = Math.min(a, b) + 1; i < Math.max(a, b); i++) {
            if (this.board[i][y] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean column(int x, int a, int b) {

        for (int i = Math.min(a, b) + 1; i < Math.max(a, b); i++) {
            if (this.board[x][i] != null) {
                return false;
            }
        }
        return true;
    }


    public int kill(Position p) {
        int num = 0;
        if (p.y + 1 < 10 && this.board[p.x][p.y + 1] != null && !this.board[p.x][p.y + 1].getType().equals("♔")) {
            if (board[p.x][p.y + 2] != null) {
                Position p1 = new Position(p.x, p.y + 2);
                num += kill2(p, p1);
            }
        } else if (p.y + 1 == 10 && this.board[p.x][p.y + 1] != null && !this.board[p.x][p.y + 1].getType().equals("♔")) {
            if (this.board[p.x][p.y + 1] != null && this.board[p.x][p.y + 1].getOwner() != this.board[p.x][p.y].getOwner()) {
                this.board[p.x][p.y + 1] = null;
                num += 1;
            }
        }
        if (p.y - 1 > 0 && this.board[p.x][p.y - 1] != null && !this.board[p.x][p.y - 1].getType().equals("♔")) {
            if (board[p.x][p.y - 2] != null) {
                Position p2 = new Position(p.x, p.y - 2);
                num += kill2(p2, p);
            }
        } else if (p.y - 1 == 0 && this.board[p.x][p.y - 1] != null && !this.board[p.x][p.y - 1].getType().equals("♔")) {
            if (this.board[p.x][p.y - 1] != null && this.board[p.x][p.y - 1].getOwner() != this.board[p.x][p.y].getOwner()) {
                this.board[p.x][p.y - 1] = null;
                num += 1;
            }
        }
        if (p.x + 1 < 10 && this.board[p.x + 1][p.y] != null && !this.board[p.x + 1][p.y].getType().equals("♔")) {
            if (board[p.x + 2][p.y] != null) {
                Position p3 = new Position(p.x + 2, p.y);
                num += kill1(p, p3);
            }
        } else if (p.x + 1 == 10 && this.board[p.x + 1][p.y] != null && !this.board[p.x + 1][p.y].getType().equals("♔")) {
            if (this.board[p.x + 1][p.y] != null && this.board[p.x + 1][p.y].getOwner() != this.board[p.x][p.y].getOwner()) {
                this.board[p.x + 1][p.y] = null;
                num += 1;
            }
        }
        if (p.x - 1 > 0 && this.board[p.x - 1][p.y] != null && !this.board[p.x - 1][p.y].getType().equals("♔")) {
            if (board[p.x - 2][p.y] != null) {
                Position p4 = new Position(p.x - 2, p.y);
                num += kill1(p4, p);
            }
        } else if (p.x - 1 == 0 && this.board[p.x - 1][p.y] != null && !this.board[p.x - 1][p.y].getType().equals("♔")) {
            if (this.board[p.x - 1][p.y] != null && this.board[p.x - 1][p.y].getOwner() != this.board[p.x][p.y].getOwner()) {
                this.board[p.x - 1][p.y] = null;
                num += 1;
            }
        }
        return num;

    }

    public int kill1(Position p1, Position p2) {

        if (!(this.board[p1.x][p1.y].getType().equals("♔")) && !(this.board[p2.x][p2.y].getType().equals("♔"))) {
            if (this.board[p1.x][p1.y].getOwner() == board[p2.x][p2.y].getOwner()) {
                if (this.board[p1.x + 1][p1.y] != null) {
                    if (this.board[p1.x + 1][p1.y].getOwner() != this.board[p1.x][p1.y].getOwner()) {
                        this.board[p1.x + 1][p1.y] = null;
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    public int kill2(Position p1, Position p2) {

        if (!(this.board[p1.x][p1.y].getType().equals("♔")) && !(this.board[p2.x][p2.y].getType().equals("♔"))) {
            if (this.board[p1.x][p1.y].getOwner() == board[p2.x][p2.y].getOwner()) {
                if (this.board[p1.x][p1.y + 1] != null) {
                    if (this.board[p1.x][p1.y + 1].getOwner() != this.board[p1.x][p1.y].getOwner()) {
                        this.board[p1.x][p1.y + 1] = null;
                        return 1;
                    }
                }
            }
        }

        return 0;
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return board[position.x][position.y];
    }

    @Override
    public Player getFirstPlayer() {
        return player2;
    }

    @Override
    public Player getSecondPlayer() {
        return player1;
    }

    @Override
    public boolean isGameFinished() {


        int x=0;
        int y=0;
        for (int i = 0; i < 13; i++) {
            if (player1.team[i].number == 7) {
                x = player1.team[i].steps.get(player1.team[i].steps.size()-1).x;
                y = player1.team[i].steps.get(player1.team[i].steps.size()-1).y;
            }
        }
            if (this.board[x][y] != null && this.board[x][y].getType().equals("♔")) {
                if (x == 0 && y == 0 || x == 0 && y == 10 || x == 10 && y == 0 || x == 10 && y == 10) {
                    print(player1, player2);
                    player2.winNow(true);
                    player1.winNow(false);
                   // reset();
                    return true;
                }
                if (this.board[x][y] != null && this.board[x][y].getType().equals("♔")) {
                    if (x - 1 > 0 && (this.board[x - 1][y] == null || this.board[x - 1][y].getOwner() == this.board[x][y].getOwner())) {
                        return false;
                    }
                    if (y - 1 > 0 && (this.board[x][y - 1] == null || this.board[x][y - 1].getOwner() == this.board[x][y].getOwner())) {
                        return false;
                    }
                    if (x + 1 < 11 && (this.board[x + 1][y] == null || this.board[x + 1][y].getOwner() == this.board[x][y].getOwner())) {
                        return false;
                    }
                    if (y + 1 < 11 && (this.board[x][y + 1] == null || this.board[x][y + 1].getOwner() == this.board[x][y].getOwner())) {
                        return false;
                    }
                    print(player2, player1);
                    player1.winNow(true);
                    player2.winNow(false);
                  //  reset();
                    return true;
                }
            }
        return false;
    }

    public void print(ConcretePlayer win, ConcretePlayer lose) {

        Arrays.sort(win.team, historicStepsComparator);
        for (int m = 0; m < win.team.length; m++) {
            win.team[m].printSteps();
            ;
        }
        Arrays.sort(lose.team, historicStepsComparator);
        for (int n = 0; n < lose.team.length; n++) {
            lose.team[n].printSteps();
            ;
        }

        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();

        ConcretePiece[] merge = new ConcretePiece[win.team.length + lose.team.length];
        System.arraycopy(win.team, 0, merge, 0, win.team.length);
        System.arraycopy(lose.team, 0, merge, win.team.length, lose.team.length);
        Arrays.sort(merge, killComparator);
        for (ConcretePiece concretePiece : merge) {
            concretePiece.printkills();
            ;
        }

        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();

        Arrays.sort(merge, distanceComparator);
        for (ConcretePiece concretePiece : merge) {
            concretePiece.printDistance();
            ;
        }

        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();

        listP.sort(PosComparator);

        for (Position position : listP) {
            if (position.listS.size() > 1) {
                System.out.println("(" + position.x + ", " + position.y + ")" + position.listS.size() + " pieces");
            }
        }
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return !turn;
    }

    @Override
    public void reset() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                this.board[i][j] = null;
            }
        }
        setBoard();
    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return 11;
    }

    public Comparator<ConcretePiece> historicStepsComparator = new Comparator<ConcretePiece>() {
        @Override
        public int compare(ConcretePiece o1, ConcretePiece o2) {
            if (o2.steps.size() != o1.steps.size()) {return Integer.compare(o1.steps.size(), o2.steps.size());}
            else if (o2.getOwner()==o1.getOwner()){return Integer.compare(o1.number,o2.number);}
            else   return Boolean.compare(o1.getOwner().win,o2.getOwner().win);
        }
    };
    public Comparator<ConcretePiece> killComparator = new Comparator<ConcretePiece>() {
        @Override
        public int compare(ConcretePiece o1, ConcretePiece o2) {
            if (o2.kills != o1.kills) {return Integer.compare(o2.kills, o1.kills);}
            else if (o2.getOwner()==o1.getOwner()){return Integer.compare(o2.number,o1.number);}
                  else   return Boolean.compare(o2.getOwner().win,o1.getOwner().win);

        }
    };

    public Comparator<ConcretePiece> distanceComparator = new Comparator<ConcretePiece>() {
        @Override
        public int compare(ConcretePiece o1, ConcretePiece o2) {
            if (o1.distance() != o2.distance()) {return Integer.compare(o2.distance(), o1.distance());}
            else if (o1.number==o2.number){return Boolean.compare(o2.getOwner().win,o1.getOwner().win);}
               else return Integer.compare(o1.number,o2.number);
        }
    };
    public Comparator<Position> PosComparator = new Comparator<Position>() {
        public int compare(Position o1, Position o2) {
            if(o1.listS.size()!=o2.listS.size()){
            return Integer.compare(o2.listS.size(),o1.listS.size());}
            else if(o1.x!=o2.x){return Integer.compare(o1.x,o2.x);}
            else return Integer.compare(o1.y,o2.y);
        }

    };

}
