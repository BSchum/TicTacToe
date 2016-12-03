package connect4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by brice on 01/12/2016.
 */
public class Saisie {
    private Scanner scanner = new Scanner(System.in);
    ArrayList<GameEnvironment> gameEnvironmentObs = new ArrayList<>();
    public int readInt() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        else {

            scanner.next();
            return -1;
        }
    }

    public String read() {
        if (scanner.hasNext()) {
            return scanner.next();
        }
        else {
            scanner.next();
            return "no input";
        }
    }
}
