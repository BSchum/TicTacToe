package connect4;

import java.util.ArrayList;

/**
 * Created by brice on 24/11/2016.
 */
public class GameEnvironment {
    Player[] listPlayer = new Player[2];
    Board board;
    ArrayList<Cell> neighboursArray = new ArrayList<>();
    ArrayList<Observer> displayObs = new ArrayList<>();

    public void AddDisplayObs(Display display){
        displayObs.add(display);
    }
    public void NotifyDisplay(){
        for (Observer a : displayObs) {
            a.Update(board);
        }
    }
    public GameEnvironment(Player[] listPlayer) {
        board = new Board();
        this.listPlayer = listPlayer;
    }

    boolean VerifyVictoryCondition(){
        for(int i = 0;i < 5;i++) {
            for (int j = 0; j < 6; j++) {
                for (Direction dir : Direction.values()) {
                    if (board.GetCase(i, j).symbol != ' ') {
                        VerifyNeighbours(board.GetCase(i, j), dir);
                        VerifyNeighbours(board.GetCase(i, j), dir.opposite());
                        //System.out.println(neighboursArray);
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

    private boolean VerifyLine(){
        return true;
    }

    private boolean VerifyCollumn(){
        return true;
    }

    private void VerifyNeighbours(Cell cellule, Direction dir) {
        if(GetNeighboursInDirection(cellule,dir).symbol == cellule.symbol) {
            neighboursArray.add(GetNeighboursInDirection(cellule, dir));
            VerifyNeighbours(GetNeighboursInDirection(cellule, dir),dir);
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
    public void HowTheGameWorks(){
        board = new Board();
        AddDisplayObs(new Display());
        do{
            NotifyDisplay();
            Saisie saisie = new Saisie();
            int x =0;
            do{
                System.out.println("Choisissez un x : Player 1");
                x = saisie.readInt() - 1;
                if (board.IsInBoard(x) && board.ColumnIsFull(x)){
                    listPlayer[0].Play(board, x);
                    break;//On quitte instantanément la boucle pour par revérifier si la colonne est remplis apres avoir jouer
                }
            }while(!board.IsInBoard(x) || !board.ColumnIsFull(x));

            NotifyDisplay();
            if(VerifyVictoryCondition()){
                continue;
            }
            do{
                System.out.println("Choisissez un x : Player 2");
                x = saisie.readInt() - 1;
                if (board.IsInBoard(x) && board.ColumnIsFull(x)){
                    listPlayer[1].Play(board, x);
                    break;
                }
            }while(!board.IsInBoard(x) || !board.ColumnIsFull(x));
        }while(!VerifyVictoryCondition());
        if(neighboursArray.get(0).symbol == 'X'){
            System.out.println("Player one won");
        }
        else{
            System.out.println("Player two won");
        }
    }
}
