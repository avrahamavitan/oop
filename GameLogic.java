import java.util.*;

public class GameLogic implements PlayableLogic {
    private ConcretePiece[][] board = new ConcretePiece[this.size][this.size];
    private ArrayList<Position> listPosition = new ArrayList<Position>();//רשימה של כל הפוזישן שדרכו עליהם במשחק נתון
    private ConcretePiece[] team1 = new ConcretePiece[13];//מערך של כל שחקני הקבוצה המגנה
    private ConcretePiece[] team2 = new ConcretePiece[24];//מערך של כל שחקני הקבוצה התוקפת
    private ConcretePlayer defens = new ConcretePlayer(true, team1);
    private ConcretePlayer attack = new ConcretePlayer(false, team2);
    private boolean turn = false;
    final int size = 11;

    GameLogic() {

        set_teams();//בניית מערכים המכילים את שתי הקבוצות
        setBoard();//סידור הקבוצות על הלוח
    }

    void set_teams() {//בניית מערכים המכילים את שתי הקבוצות
        this.defens.set_team(0, new Pawn(this.defens, 1, 5, 3));
        this.defens.set_team(1, new Pawn(this.defens, 2, 4, 4));
        this.defens.set_team(2, new Pawn(this.defens, 3, 5, 4));
        this.defens.set_team(3, new Pawn(this.defens, 4, 6, 4));
        this.defens.set_team(4, new Pawn(this.defens, 5, 3, 5));
        this.defens.set_team(5, new Pawn(this.defens, 6, 4, 5));
        this.defens.set_team(6, new King(this.defens, 7, 5, 5));
        this.defens.set_team(7, new Pawn(this.defens, 8, 6, 5));
        this.defens.set_team(8, new Pawn(this.defens, 9, 7, 5));
        this.defens.set_team(9, new Pawn(this.defens, 10, 4, 6));
        this.defens.set_team(10, new Pawn(this.defens, 11, 5, 6));
        this.defens.set_team(11, new Pawn(this.defens, 12, 6, 6));
        this.defens.set_team(12, new Pawn(this.defens, 13, 5, 7));


        this.attack.set_team(0, new Pawn(this.attack, 1, 3, 0));
        this.attack.set_team(1, new Pawn(this.attack, 2, 4, 0));
        this.attack.set_team(2, new Pawn(this.attack, 3, 5, 0));
        this.attack.set_team(3, new Pawn(this.attack, 4, 6, 0));
        this.attack.set_team(4, new Pawn(this.attack, 5, 7, 0));
        this.attack.set_team(5, new Pawn(this.attack, 6, 5, 1));
        this.attack.set_team(6, new Pawn(this.attack, 7, 0, 3));
        this.attack.set_team(7, new Pawn(this.attack, 9, 0, 4));
        this.attack.set_team(8, new Pawn(this.attack, 11, 0, 5));
        this.attack.set_team(9, new Pawn(this.attack, 15, 0, 6));
        this.attack.set_team(10, new Pawn(this.attack, 17, 0, 7));
        this.attack.set_team(11, new Pawn(this.attack, 12, 1, 5));
        this.attack.set_team(12, new Pawn(this.attack, 8, 10, 3));
        this.attack.set_team(13, new Pawn(this.attack, 10, 10, 4));
        this.attack.set_team(14, new Pawn(this.attack, 14, 10, 5));
        this.attack.set_team(15, new Pawn(this.attack, 16, 10, 6));
        this.attack.set_team(16, new Pawn(this.attack, 18, 10, 7));
        this.attack.set_team(17, new Pawn(this.attack, 13, 9, 5));
        this.attack.set_team(18, new Pawn(this.attack, 20, 3, 10));
        this.attack.set_team(19, new Pawn(this.attack, 21, 4, 10));
        this.attack.set_team(20, new Pawn(this.attack, 22, 5, 10));
        this.attack.set_team(21, new Pawn(this.attack, 23, 6, 10));
        this.attack.set_team(22, new Pawn(this.attack, 24, 7, 10));
        this.attack.set_team(23, new Pawn(this.attack, 19, 5, 9));

    }

    void setBoard() {
//סידור כל שחקני קבוצת player1 על הלוח והכנסת המיקומים שלהם לרשימת המיקומים שדרכו בהם שחקנים
        for (int i = 0; i < this.defens.get_team().length; i++) {
            this.board[this.defens.get_team()[i].getstartPx()][this.defens.get_team()[i].getstartPy()] = this.defens.get_team()[i];
            Position p = new Position(defens.get_team()[i].getstartPx(), defens.get_team()[i].getstartPy());
            set_listPosition(p);
        }
        //סידור כל שחקני קבוצת player2 על הלוח והכנסת המיקומים שלהם לרשימת המיקומים שדרכו בהם שחקנים
        for (int i = 0; i < this.attack.get_team().length; i++) {
            this.board[this.attack.get_team()[i].getstartPx()][this.attack.get_team()[i].getstartPy()] = this.attack.get_team()[i];
            Position p = new Position(attack.get_team()[i].getstartPx(), attack.get_team()[i].getstartPy());
            set_listPosition(p);
        }

    }

    @Override
    public boolean move(Position a, Position b) {
        if (this.board[a.getX()][a.getY()].getOwner().isPlayerOne() == turn) {
            if (this.board[b.getX()][b.getY()] == null) {
                if (this.board[a.getX()][a.getY()].getType().equals("♔")) {//אם החייל שזז הוא מלך אז הוא יכול לזוז לכל מקום והוא לא יכול לאכול
                    if (((a.getX() == b.getX()) && move_column(a.getX(), a.getY(), b.getY())) || ((a.getY() == b.getY()) && move_row(a.getY(), a.getX(), b.getX()))) {//בדיקה האם שתי המשבצות באותה שור/תור ואפשר לבצע תזוזה
                        this.board[b.getX()][b.getY()] = this.board[a.getX()][a.getY()];//תזוזה של החייל אל b
                        this.board[a.getX()][a.getY()] = null;//הפיכת  מיקום a לריק
                        getPieceAtPosition(b).addSteps(b);//הוספת b בתור צעד שהחייל עשה
                        set_listPosition(b);//הוספת b בתור משבצת שדרכו עליה
                        turn = !(turn);//החלפת התור
                        if (isGameFinished()) {
                            print(defens, attack);
                            defens.setWins(defens.getWins() + 1);
                        }//בדיקה האם נגמר המשחק
                        return true;//החזרת true כי המהלך היה תקין
                    }
                } //אם החייל הוא לא מלך אז צריך לבדוק שהוא לא זז לפינות
                else if (!(b.getX() == 0 && b.getY() == 0) && !(b.getX() == 0 && b.getY() == 10) && !(b.getX() == 10 && b.getY() == 0) && !(b.getX() == 10 && b.getY() == 10)) {
                    if (((a.getX() == b.getX()) && move_column(a.getX(), a.getY(), b.getY())) || ((a.getY() == b.getY()) && move_row(a.getY(), a.getX(), b.getX()))) {//בדיקה האם הם באותה שורה/טור ואפשר לבצע תזוזה
                        this.board[b.getX()][b.getY()] = this.board[a.getX()][a.getY()];//תזוזה של החייל לפוזישן b
                        this.board[a.getX()][a.getY()] = null;//הפיכת a למקום ריק
                        getPieceAtPosition(b).addSteps(b);//הוספת b לרשימת הצעדים שהחייל עשה
                        ((Pawn) getPieceAtPosition(b)).setKills(kill(b));//קריאה לפונקצייה kill שבודקת האם החייל הרג חיילים אחרים ואם כן מוסיפה את הכמות למספר החיילים שהרג
                        set_listPosition(b);//הוספת b לרשימת המשבצות שדרכו עליהן
                        turn = !(turn);//החלפת תור
                        if (isGameFinished()) {
                            print(attack, defens);
                            attack.setWins(attack.getWins() + 1);
                        }//בדיקה האם נגמר המשחק
                        return true;//החזרת true כי המהלך היה תקין
                    }

                }
            }

        }

        return false;//אם השחקן לא זז אז מחזירים false כי המהלך לא היה תקין
    }

    public void set_listPosition(Position b) {//הפונקצייה מוסיפה את המשבצות שדרכו עליהם במשך לתוך הרשימה
        boolean found = false;
        for (Position position : listPosition) {//בדיקה האם כבר קיית משבצת אם הנתוהים של b ברשימה
            if (b.getX() == position.getX()) {
                if (b.getY() == position.getY()) {
                    position.addP(this.board[b.getX()][b.getY()].getName());
                    found = true;
                }
            }
        }
        if (!found) {//אם b לא ברשימה
            listPosition.add(b);//הוספה של b לרשימה}
            b.addP(this.board[b.getX()][b.getY()].getName());//הוספה של החייל שדרך לתוך הרשימה של החיילים שדרכו על משבצת זו
        }
    }

    public boolean move_row(int y, int x1, int x2) {//בדיקה האם לא נמצא אף חייל בטור זה
        for (int i = Math.min(x1, x2) + 1; i < Math.max(x1, x2); i++) {
            if (this.board[i][y] != null) {
                return false;
            }
        }
        return true;
    }

    public boolean move_column(int x, int y1, int y2) {//בדיקה האם לא נמצא אף חייל בשורה זו
        for (int i = Math.min(y1, y2) + 1; i < Math.max(y1, y2); i++) {
            if (this.board[x][i] != null) {
                return false;
            }
        }
        return true;
    }

    public int kill(Position p) {
        int num = 0;
        //אכילה למטה
        if (p.getY() + 1 < 10) {
            num += kill_2pieces(p, 0, 1);
        } else if (p.getY() + 1 == 10) {
            num += kill_1pices(p, 0, 1);
        }
//אכילה למעלה
        if (p.getY() - 1 > 0) {
            num += kill_2pieces(p, 0, -1);
        } else if (p.getY() - 1 == 0) {
            num += kill_1pices(p, 0, -1);
        }
//אכילה ימינה
        if (p.getX() + 1 < 10) {
            num += kill_2pieces(p, 1, 0);
        } else if (p.getX() + 1 == 10) {
            num += kill_1pices(p, 1, 0);
        }
//אכילה שמאלה
        if (p.getX() - 1 > 0) {
            num += kill_2pieces(p, -1, 0);
        } else if (p.getX() - 1 == 0) {
            num += kill_1pices(p, -1, 0);
        }
//אכילה ליד הפינות שבהן אסור לדרוך פרט למלך
        if ((p.getX() == 2 && p.getY() == 0) || (p.getX() == 2 && p.getY() == 10)) {
            num += kill_1pices(p, -1, 0);
        }
        if ((p.getX() == 8 && p.getY() == 0) || (p.getX() == 8 && p.getY() == 10)) {
            num += kill_1pices(p, 1, 0);
        }
        if ((p.getX() == 0 && p.getY() == 2) || (p.getX() == 10 && p.getY() == 2)) {
            num += kill_1pices(p, 0, -1);
        }
        if ((p.getX() == 0 && p.getY() == 8) || (p.getX() == 10 && p.getY() == 8)) {
            num += kill_1pices(p, 0, 1);
        }
        //num זה מספר האכילות שהשחקן ביצע
        return num;

    }

    //פונקצייה המקבלת משבצת ובודקת האם השחקן שנמצא בה יכול לאכול.היא בודקת את כל האופציות במרכז הלוח של אכילה ע"י שתי שחקנים ואם כן מבצעת אכילה
    public int kill_2pieces(Position p, int i, int j) {
        if (this.board[p.getX() + i][p.getY() + j] != null) {
            if (!this.board[p.getX() + i][p.getY() + j].getType().equals("♔")) {
                if (this.board[p.getX() + i][p.getY() + j].getOwner() != this.board[p.getX()][p.getY()].getOwner()) {
                    if (board[p.getX() + i * 2][p.getY() + j * 2] != null) {
                        if (!board[p.getX() + i * 2][p.getY() + j * 2].getType().equals("♔")) {
                            if (board[p.getX() + i * 2][p.getY() + j * 2].getOwner() == this.board[p.getX()][p.getY()].getOwner()) {
                                this.board[p.getX() + i][p.getY() + j] = null;

                                return 1;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    //פונקצייה הבודקת האם שחקן יכול לאכול שחקן אחר בקצוות ואם כן מבצעת
    public int kill_1pices(Position p, int i, int j) {
        if (this.board[p.getX() + i][p.getY() + j] != null) {
            if (!this.board[p.getX() + i][p.getY() + j].getType().equals("♔")) {
                if (this.board[p.getX() + i][p.getY() + j].getOwner() != this.board[p.getX()][p.getY()].getOwner()) {
                    this.board[p.getX() + i][p.getY() + j] = null;
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public ConcretePiece getPieceAtPosition(Position position) {
        return board[position.getX()][position.getY()];
    }

    @Override
    public ConcretePlayer getFirstPlayer() {
        return defens;
    }

    @Override
    public ConcretePlayer getSecondPlayer() {
        return attack;
    }

    @Override
    public boolean isGameFinished() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < this.defens.get_team().length; i++) {//מציאת המיקום האחרון של המלך
            if (defens.get_team()[i].getNumber() == 7) {
                x = defens.get_team()[i].geSteps().get(defens.get_team()[i].geSteps().size() - 1).getX();
                y = defens.get_team()[i].geSteps().get(defens.get_team()[i].geSteps().size() - 1).getY();
            }
        }
        //אם המלך נמצא באחת הפינות אז המגנים ניצחו
        if (x == 0 && y == 0 || x == 0 && y == 10 || x == 10 && y == 0 || x == 10 && y == 10) {
            // print(player1, player2);
            defens.setWinNow(true);
            attack.setWinNow(false);
            // player1.setWins(player1.getWins() + 1);
            return true;
        } else {//בדיקה האם המלך מוקף מכל הכיוונים בשחקני האוייב .אם כן התוקפים ניצחו
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
            // print(player2, player1);
            attack.setWinNow(true);
            defens.setWinNow(false);

            return true;

        }

    }

    public void print(ConcretePlayer win, ConcretePlayer lose) {
        //מיו מערך הקבוצה המנצחת לפי מספר צעדים
        Arrays.sort(win.get_team(), historicStepsComparator);
        for (int m = 0; m < win.get_team().length; m++) {
            win.get_team()[m].printSteps();
            ;
        }
        //מיון מערך הקבוצה המפסידה לפי מספר צעדים והדפסה
        Arrays.sort(lose.get_team(), historicStepsComparator);
        for (int n = 0; n < lose.get_team().length; n++) {
            lose.get_team()[n].printSteps();
            ;
        }

        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
        //  בניית מערך רק של החיילים בלי המלך ומיון לפי כמות  kills
        Pawn[] merge = new Pawn[defens.get_team().length + attack.get_team().length - 1];
        int j = 0;
        for (int i = 0; i < this.defens.get_team().length; i++) {
            if (this.defens.get_team()[i].getNumber() != 7) {
                merge[j] = (Pawn) this.defens.get_team()[i];
                j++;
            }
        }
        for (int i = 0; i < this.attack.get_team().length; i++) {
            merge[i + this.defens.get_team().length - 1] = (Pawn) this.attack.get_team()[i];
        }
        Arrays.sort(merge, killComparator);
        for (int i = 0; i < merge.length; i++) {
            merge[i].printkills();
        }

        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
        //בנית מערך של שתי הקבוצות כולל המלך ומיון לפי מרחק והדפסה
        ConcretePiece[] merge2 = new ConcretePiece[win.get_team().length + lose.get_team().length];
        System.arraycopy(win.get_team(), 0, merge2, 0, win.get_team().length);
        System.arraycopy(lose.get_team(), 0, merge2, win.get_team().length, lose.get_team().length);
        Arrays.sort(merge2, distanceComparator);
        for (int i = 0; i < merge.length; i++) {
            merge2[i].printDistance();
        }

        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
        //מיון כל המשבצות לפי כמות חיילים שונים שניו עליהם והדפסה
        listPosition.sort(PosComparator);
        for (int i = 0; i < listPosition.size(); i++) {
            if (listPosition.get(i).getList_Pieces().size() > 1) {
                System.out.println("(" + listPosition.get(i).getX() + ", " + listPosition.get(i).getY() + ")" + listPosition.get(i).getList_Pieces().size() + " pieces");
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
        //מחיקת כל הלוח
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                this.board[i][j] = null;
            }
        }
        //מחיקה לכל משבצת שדרכו עליה את הפרטים של מי שדרך עליה
        for (int i = 0; i < listPosition.size(); i++) {
            listPosition.get(i).getList_Pieces().clear();
        }
        //מחיקת מערך המשבצות שדרכו עליהם
        listPosition.clear();
        //מחיקת כל הצעדים שכל החיילים עשו
        for (int i = 0; i < defens.get_team().length; i++) {
            defens.get_team()[i].geSteps().clear();
        }
        for (int i = 0; i < attack.get_team().length; i++) {
            attack.get_team()[i].geSteps().clear();
        }
        //סידור הלוח מחדש
        setBoard();
    }

    @Override
    public void undoLastMove() {
    }


    @Override
    public int getBoardSize() {
        return this.size;
    }

    public Comparator<ConcretePiece> historicStepsComparator = new Comparator<ConcretePiece>() {
        @Override//השוואה בין חיילים לפי כמות הצעדים שצעדו
        public int compare(ConcretePiece o1, ConcretePiece o2) {
            if (o2.geSteps().size() != o1.geSteps().size()) {
                return Integer.compare(o1.geSteps().size(), o2.geSteps().size());
            } //אם המרחק שווה אז השוואה לפי מספר שחקן
            else {
                return Integer.compare(o1.getNumber(), o2.getNumber());
            }


        }
    };
    public Comparator<Pawn> killComparator = new Comparator<Pawn>() {
        @Override//השוואה בין חיילים לפי מספר הריגות
        public int compare(Pawn o1, Pawn o2) {
            if (o2.getKills() != o1.getKills()) {
                return Integer.compare(o2.getKills(), o1.getKills());
            } else if (o2.getNumber() != o1.getNumber()) {//אם מספר ההריגות שווה אז השוואה לפי קבוצה מנצחת
                return Integer.compare(o1.getNumber(), o2.getNumber());
            } else
                return Boolean.compare(o1.getOwner().getWinnow(), o2.getOwner().getWinnow());//בסוף השוואה לפי המספר של החייל

        }
    };
    public Comparator<ConcretePiece> distanceComparator = new Comparator<ConcretePiece>() {
        @Override//השוואה בין חיילים לפי מרחק שצעדו
        public int compare(ConcretePiece o1, ConcretePiece o2) {
            if (o1.distance() != o2.distance()) {
                return Integer.compare(o2.distance(), o1.distance());
            } else if (o1.getNumber() != o2.getNumber()) {//אם המרחק שווה אז השוואה לפי המספר שלהם
                return Integer.compare(o1.getNumber(), o2.getNumber());
            } else
                return Boolean.compare(o1.getOwner().getWinnow(), o2.getOwner().getWinnow());//בסוף השוואה לפי מי ניצח עכשיו
        }
    };
    public Comparator<Position> PosComparator = new Comparator<Position>() {//השוואת פוזישנים
        public int compare(Position o1, Position o2) {
            if (o1.getList_Pieces().size() != o2.getList_Pieces().size()) {//השוואה לפי מספר הדריכות
                return Integer.compare(o2.getList_Pieces().size(), o1.getList_Pieces().size());
            } else if (o1.getX() != o2.getX()) {//אם מספר הדריכות שווה אז השוואה בין נקודת הx
                return Integer.compare(o1.getX(), o2.getX());
            } else return Integer.compare(o1.getY(), o2.getY());//אם גם נקודת הx שווה אז השוואה לפי הy
        }

    };

}
