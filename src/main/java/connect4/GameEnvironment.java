package connect4;

import java.util.ArrayList;
import java.util.List;

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

        return true;
    }

    private boolean VerifyLine(){
        return true;
    }

    private boolean VerifyCollumn(){
        return true;
    }

    private void VerifyNeighbours(Cell cellule, Direction dir) {

    }
    private Cell GetNeighboursInDirection(Cell c, Direction dir){
        switch (dir){
            case NORTH:
                return board.GetCase(c.coord.x,c.coord.y-1);

            case NORTHEAST:
                return board.GetCase(c.coord.x + 1,c.coord.y -1);

            case EAST:
                return board.GetCase(c.coord.x +1,c.coord.y);

            case SOUTHEAST:
                return board.GetCase(c.coord.x+1,c.coord.y+1);

            case SOUTH:
                return board.GetCase(c.coord.x,c.coord.y+1);

            case SOUTHWEST:
                return board.GetCase(c.coord.x-1,c.coord.y+1);

            case WEST:
                return board.GetCase(c.coord.x-1,c.coord.y);

            case NORTHWEST:
                return board.GetCase(c.coord.x-1,c.coord.y -1);

            default://should never happen
                return new Cell();
        }

    }
    public void HowTheGameWorks(){
        board = new Board();
        AddDisplayObs(new Display());
        listPlayer[0].Play(board,1);
        listPlayer[1].Play(board,1);
        NotifyDisplay();

    }
}
