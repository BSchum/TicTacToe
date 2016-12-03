package connect4;

/**
 * Created by brice on 25/11/2016.
 */
public enum Direction {
    NORTH(4),NORTHEAST(5),EAST(6),SOUTHEAST(7),SOUTH(0),SOUTHWEST(1),WEST(2),NORTHWEST(3);
    private int opposite;

    Direction(int opposite){
        this.opposite = opposite;
    }

    Direction opposite(){
        return Direction.values()[this.opposite];
    }
}
