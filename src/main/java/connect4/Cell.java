package connect4;

import java.text.Normalizer;

/**
 * Created by brice on 25/11/2016.
 */
public class Cell {
    Coord coord;
    Character symbol;

    public Cell(){
        //cellule vide utilisé pour l'Update
        symbol = ' ';

    }
    public Cell(int x, int y, char c) {
        coord = new Coord(x, y);
        symbol = c;
    }

    public void SetSymbol(char s) {
        symbol = s;
    }
}
