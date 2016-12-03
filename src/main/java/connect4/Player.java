package connect4;

import java.util.List;
import java.util.Stack;

/**
 * Created by brice on 24/11/2016.
 */
public class Player {
    private static int playerNumber = 0;
    public boolean isWinner;
    private char pieceForm;
    public char getPieceForm() {
        return pieceForm;
    }

    public void setPieceForm(char pieceForm) {
        this.pieceForm = pieceForm;
    }


    public Player(){
        playerNumber++;
        if(playerNumber == 1){
            pieceForm = 'X';
        }
        else{
            pieceForm = 'O';
        }
    }
    public boolean Play(Board board, int x){
        return true;
    }
}
