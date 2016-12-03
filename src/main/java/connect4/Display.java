package connect4;

import java.util.ArrayList;

public class Display implements Observer{


    public void Update(Board b){
        System.out.println("  1 2 3 4 5 6 7");
        for(int i = 0; i<6;i++){
            System.out.print(i+1);
            for(int j = 0;j<7;j++){
                System.out.print("|"+b.vBoard[j][i].symbol);
            }
            System.out.println("|");
        }
    }

	public static void main(String[] args) {
        Player[] players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        GameEnvironment GE = new GameEnvironment(players);
        GE.HowTheGameWorks();
    }
}