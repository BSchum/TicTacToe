package connect4;

import java.util.ArrayList;

/**
 * Created by brice on 24/11/2016.
 */
public class GameEnvironment {
    private Player[] listPlayer = new Player[2];
    private Board board;
    private ArrayList<Cell> neighboursArray = new ArrayList<>();
    private ArrayList<Observer> displayObs = new ArrayList<>();

    private void AddDisplayObs(Display display){
        displayObs.add(display);
    }
    private void NotifyDisplay(){
        for (Observer a : displayObs) {
            a.Update(board);
        }
    }
    public GameEnvironment(Player[] listPlayer) {
        board = new Board();
        this.listPlayer = listPlayer;
    }

    boolean VerifyVictoryCondition(){
        boolean won = VerifyIfPlayerWin();
        if(!won){
            won = VerifyIfFull();
        }
        return won;
    }

    private boolean VerifyIfPlayerWin() {
        for(int i = 0;i < 5;i++) {
            for (int j = 0; j < 6; j++) {
                for (Direction dir : Direction.values()) {
                    if (board.GetCase(i, j).symbol != ' ') {
                        VerifyNeighbours(board.GetCase(i, j), dir);
                        VerifyNeighbours(board.GetCase(i, j), dir.opposite());
                        if(neighboursArray.size() >=3){
                            return true;
                        }

                    }
                    neighboursArray.clear();
                }
            }
        }
        return false;
    }

    private boolean VerifyIfFull() {
        int numberOfPiece = 0;
        for (int i =0;i<7;i++){
            for (int j =0;j<6;j++){
                if(board.GetCase(i,j).symbol != ' '){
                    numberOfPiece++;
                }
            }
        }
        System.out.println(numberOfPiece);
        if(numberOfPiece == (6*7)){
            return true;
        }
        else{
            return false;
        }
    }
    private void VerifyNeighbours(Cell cellule, Direction dir) {
        if(GetNeighboursInDirection(cellule,dir).symbol == cellule.symbol) {
            neighboursArray.add(GetNeighboursInDirection(cellule, dir));
            VerifyNeighbours(GetNeighboursInDirection(cellule, dir), dir);
        }
    }
    private Cell GetNeighboursInDirection(Cell c, Direction dir){
        switch (dir){
            case NORTH:
                if(c.coord.y>0)
                    return board.GetCase(c.coord.x,c.coord.y-1);
                else
                    return new Cell();

            case NORTHEAST:
                if(c.coord.y>0 && c.coord.x < 6)
                    return board.GetCase(c.coord.x + 1,c.coord.y -1);
                else
                {
                    return new Cell();
                }

            case EAST:
                if(c.coord.x<6)
                    return board.GetCase(c.coord.x +1,c.coord.y);
                else
                    return new Cell();
            case SOUTHEAST:
                if(c.coord.y<5 && c.coord.x < 6)
                    return board.GetCase(c.coord.x+1,c.coord.y+1);
                else
                    return new Cell();

            case SOUTH:
                if(c.coord.y<5)
                    return board.GetCase(c.coord.x,c.coord.y+1);
                else
                    return new Cell();
            case SOUTHWEST:
                if(c.coord.y<5 && c.coord.x >0)
                    return board.GetCase(c.coord.x-1,c.coord.y+1);
                else
                    return new Cell();
            case WEST:
                if(c.coord.x > 0)
                    return board.GetCase(c.coord.x-1,c.coord.y);
                else
                    return new Cell();
            case NORTHWEST:
                if(c.coord.x > 0 && c.coord.y >0)
                    return board.GetCase(c.coord.x-1,c.coord.y -1);
                else
                    return new Cell();
            default://should never happen
                return new Cell();
        }

    }
    public void ChooseYourPlay(int player){
        int x = 0;
        do{
            Saisie saisie = new Saisie();
            System.out.println("Choisissez un x : Player "+(player+1));
            x = saisie.readInt() - 1;
            if (board.IsInBoard(x) && board.ColumnIsFull(x)){
                listPlayer[player].Play(board, x);
                break;//On quitte instantanément la boucle pour par revérifier si la colonne est remplis apres avoir jouer
            }
        }while(!board.IsInBoard(x) || !board.ColumnIsFull(x));
    }
    public void HowTheGameWorks(){
        board = new Board();
        AddDisplayObs(new Display());
        do{
            NotifyDisplay();
            ChooseYourPlay(0);
            NotifyDisplay();
            if(VerifyVictoryCondition()){
                continue;
            }
            ChooseYourPlay(1);
        }while(!VerifyVictoryCondition());
        if(neighboursArray.size() == 0){
            System.out.println("Match");
        }
        else if(neighboursArray.get(0).symbol == 'X'){
            System.out.println("Player 1 won");
        }
        else{
            System.out.println("Player 2 won");
        }
    }
}
