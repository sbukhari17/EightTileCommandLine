import java.util.Scanner;

/**
 * Created by syedb on 10/17/2016.
 */
public class TilesDriver {
    public static void main(String[] args) {

        Board board = new Board(getUserInput());
        //board.printBoard();
        //System.out.println(board.isSolved());
        //board.makeMove(6);
        //board.printBoard();
        //board.isSolved();
        board.interactiveLoop();
    }
    public static int getUserInput(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the 8-tiles puzzle.");
        System.out.println("Place the tiles in ascending numerical order. For each");
        System.out.println("move enter the piece to be moved into the blank square,");
        System.out.println("or 0 to exit the program.\n\n");
        System.out.println("Choose a game option:");
        System.out.println("\t1. Start playing");
        System.out.println("\t2. Set the starting configuration");
        System.out.println("Enter your choice -->");
        return scan.nextInt();

    }
}
