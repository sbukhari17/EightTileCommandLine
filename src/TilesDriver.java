import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by syedb on 10/17/2016.
 */
public class TilesDriver {
    public static void main(String[] args) {
        try {
            Board board = new Board(getUserInput());
            board.interactiveLoop();
        } catch(InputMismatchException e){ //if anything other than an integer is given
            Constants.outputStream.println("Invalid input given.");
        }
    }

    public static int getUserInput() {
        Scanner scan = new Scanner(Constants.inputStream);
        Constants.outputStream.println("Welcome to the 8-tiles puzzle.");
        Constants.outputStream.println("Place the tiles in ascending numerical order. For each");
        Constants.outputStream.println("move enter the piece to be moved into the blank square,");
        Constants.outputStream.println("or 0 to exit the program.\n\n");
        Constants.outputStream.println("Choose a game option:");
        Constants.outputStream.println("\t1. Start playing");
        Constants.outputStream.println("\t2. Set the starting configuration");
        Constants.outputStream.println("Enter your choice -->");
        return scan.nextInt();
    }
}
