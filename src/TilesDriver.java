/**
 * Driver class that gets user input, creates a board, and runs that board's interactive loop.
 * Created by 1530B
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class TilesDriver {
    public static void main(String[] args) {
        try {
            Board board = new Board(getUserInput());
            board.interactiveLoop();
        } catch(InputMismatchException e){ //if anything other than an integer is given
            Constants.outputStream.println("Invalid input given.");
        }
    }

    /**
     * Get user input for a starting configuration (0/1)
     * @return int
     */
    public static int getUserInput() {
        Scanner scan = new Scanner(Constants.inputStream);
        Constants.outputStream.println("Author: 1530B");
        Constants.outputStream.println("Class: CS 342, Fall 2016");
        Constants.outputStream.println("Program: #3, 8 Tiles.\n");
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
