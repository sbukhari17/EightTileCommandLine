import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.*;

import static java.lang.System.exit;

/**
 * Created by syedb on 10/17/2016.
 */
public class Board {
    public int grid [][];
    HashMap<Integer, Coordinate> mapFromIntegerToCoord = new HashMap<>();

    public Board(int choice) {
        if(choice == 1){
            generateBoard(new Random(System.currentTimeMillis()));
        } else if(choice == 2){
            generateBoard();
        } else {
            Constants.printStream.println("Invalid input given.");
            exit(1);
        }

    }
    public Board(Board b){
        grid= new int[Constants.dimX][Constants.dimY];
        for(int i=0; i< Constants.dimX; i++){
            for(int j=0; j< Constants.dimY; j++){
                grid[i][j]= b.grid[i][j];
                mapFromIntegerToCoord.put(b.grid[i][j],new Coordinate(i,j));
            }
        }
    }


    public boolean equals(Board b){
        for(int i=0; i< Constants.dimX; i++){
            if(!grid[i].equals(b.grid[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * generated a board using a random seeded with time.
     * 1. generate 9 unique, random integers (0-9)
     * 2. export them into an array
     * 3. convert into an integer array
     * 4. set up grid
     * @param rand
     */
    private void generateBoard(Random rand){
        grid = new int[Constants.dimX][Constants.dimY];
        int [] boardVals = new int[Constants.dimX * Constants.dimY];
        for(int i=0; i< (Constants.dimX* Constants.dimY); i++){
            boardVals[i]= i;
        }
        shuffleNumbers(boardVals, rand);
        int arrIndex = 0;
        for(int i=0; i< Constants.dimX; i++){
            for(int j=0; j< Constants.dimY; j++){
                int num = boardVals[arrIndex++];
                mapFromIntegerToCoord.put(num, new Coordinate(i,j));
                grid[i][j] = num;
            }
        }
    }

    /**
     * Generates board based on user input
     * 1. take input from user
     * 2. parse char by char
     * 3. input into grid
     */
    private void generateBoard(){
        Scanner scan= new Scanner(System.in);
        System.out.println("Some boards such as 728045163 are impossible.");
        System.out.println("Others such as 245386107 are possible.");
        System.out.print("Enter a string of 6 digits (including 0) for the board --> ");
        String input= scan.nextLine().trim();
        grid = new int[Constants.dimX][Constants.dimY];
        int strIndex = 0;

        for(int i=0;i < Constants.dimX; i++){
            for(int j=0; j< Constants.dimY; j++){
                int num = Character.getNumericValue(input.charAt(strIndex++));
                mapFromIntegerToCoord.put(num, new Coordinate(i,j));
                grid[i][j] = num;
            }
        }
    }
    private void shuffleNumbers(int [] arr, Random rand){
        int numTimesToSwap = rand.nextInt(50);
        for(int i=0; i< numTimesToSwap; i++){
            int index1= rand.nextInt((Constants.dimX*Constants.dimY) -1);
            int index2 = rand.nextInt((Constants.dimX*Constants.dimY) -1);
            int tmp= arr[index2];
            arr[index2] = arr[index1];
            arr[index1]=tmp;
        }
    }
    public void printBoard(){
        for(int i=0; i< Constants.dimX; i++){
            System.out.print("  ");
            for(int j=0; j< Constants.dimY; j++){
                System.out.print(grid[i][j] == 0 ? "  " : grid[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public boolean isSolved() {
        for (int i = 0; i < Constants.dimX; i++) {
            for (int j = 0; j < Constants.dimY; j++) {
                if (i == Constants.dimX - 1 && j == Constants.dimY - 1) {
                    if(grid[i][j] != 0) {
                      return false;
                  }
                }
                else if (grid[i][j] != (i * Constants.dimX) + j + 1) //every other index should satisfy its spot
                    return false;
            }
        }
        return true;
    }
    public void makeMove(int n){
        if(mapFromIntegerToCoord.containsKey(n)){
            Coordinate coord = mapFromIntegerToCoord.remove(n);
            Coordinate zeroCoord = mapFromIntegerToCoord.remove(0);
            grid[zeroCoord.X][zeroCoord.Y] = grid[coord.X][coord.Y];
            grid[coord.X][coord.Y] = 0;
            mapFromIntegerToCoord.put(0, new Coordinate(coord.X, coord.Y));
            mapFromIntegerToCoord.put(n, new Coordinate(zeroCoord.X, zeroCoord.Y));

        } else{
            //invalid number entered
        }
    }
    public boolean isValidMove(int n){
        Coordinate coord = mapFromIntegerToCoord.get(n);
        if(mapFromIntegerToCoord.containsKey(n)) {
            if (coord.X + 1 <= Constants.dimX - 1) {
                if (grid[coord.X + 1][coord.Y] == 0) {
                    return true;
                }
            }
            if (coord.Y + 1 <= Constants.dimY - 1) {
                if (grid[coord.X][coord.Y + 1] == 0) {
                    return true;
                }
            }
            if (coord.X - 1 >= 0) {
                if (grid[coord.X - 1][coord.Y] == 0) {
                    return true;
                }
            }
            if (coord.Y - 1 >= 0) {
                if (grid[coord.X][coord.Y - 1] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void interactiveLoop(){
        Scanner scan = new Scanner(System.in);
        int boardCounter = 1;
        System.out.println("Initial board is:");
        boolean autoSolve = false;
        while(!isSolved() && !autoSolve) {
            System.out.println(boardCounter++ + ".");
            printBoard();
            System.out.println("Heuristic Value: " + currentHeuristic());
            System.out.print("\nPiece to move: ");
            String move = scan.next();
            char m = move.charAt(0);
            if (m == 's') {
                autoSolve = true;
            } else {
                int numericInput = Character.getNumericValue(m);
                if(numericInput == 0){
                    exit(0);
                }
                if (isValidMove(numericInput)) {
                    makeMove(numericInput);
                } else {
                    System.out.println("Invalid Move entered.");
                }
            }
        }
        if(!isSolved()){
            autoSolve(boardCounter);
        }
        printBoard();
        System.out.println("Done!");
    }

    private void autoSolve(int currCtr){
        System.out.println("Solving puzzle automatically..........................");
        Node v = new Node(this);

        SearchTree tree = new SearchTree(v);
        boolean unsolvable = false;
        while(!v.board.isSolved() && !unsolvable){
            ArrayList<Board> children = v.board.getChildren();
            for(Board b: children){
                tree.addNode(new Node(b));
            }
            Node nextMove = tree.pop();
            if(nextMove == null){
                //unsolvable
                unsolvable = true;
            }else {
                v = nextMove;
                System.out.println(currCtr++ + ". ");
                tree.rootNode.nextNode.get(tree.rootNode.nextNode.size() - 1).board.printBoard();
            }
        }
        tree.rootNode.nextNode.get(tree.rootNode.nextNode.size()).board.printBoard();
    }

    private static Coordinate getIntendedCoordinate(int n){
        int intX = (n-1)/Constants.dimX;
        int intY = (n-1)%Constants.dimY;
        return new Coordinate(intX,intY);
    }

    private int movesFromIntendedSlot(int n){
        Coordinate currCoordinate = mapFromIntegerToCoord.get(n);
        if(n == 0){
            return Math.abs((Constants.dimX -1) - currCoordinate.X) + Math.abs((Constants.dimY -1) - currCoordinate.Y);
        }
        else {
            Coordinate intendedCoordinate = getIntendedCoordinate(n);
            return Math.abs(intendedCoordinate.X - currCoordinate.X) + Math.abs(intendedCoordinate.Y - currCoordinate.Y);
        }
    }

    public int currentHeuristic(){
        int total = 0;
        for(int i=0; i < Constants.dimX; i++){
            for(int j=0; j< Constants.dimY; j++){
                total += movesFromIntendedSlot(grid[i][j]);
            }
        }
        return total;
    }
    public static int calculateHeuristic(int [][] aGrid){
        int total = 0;
        for(int i=0; i < Constants.dimX; i++){
            for(int j=0; j< Constants.dimY; j++){
                total += movesFromIntendedSlot(aGrid[i][j], i, j);
            }
        }
        return total;
    }

    private static int movesFromIntendedSlot(int n, int i, int j){
        if(n == 0){
            return Math.abs((Constants.dimX -1) - i) + Math.abs((Constants.dimY -1) - j);
        }
        else {
            Coordinate intendedCoordinate = getIntendedCoordinate(n);
            return Math.abs(intendedCoordinate.X - i) + Math.abs(intendedCoordinate.Y - j);
        }
    }

    public ArrayList<Board> getChildren(){
        ArrayList<Board> children= new ArrayList<>();
        Coordinate posOfZero = mapFromIntegerToCoord.get(0);

                if (posOfZero.X + 1 <= Constants.dimX - 1) {
                    Board aBoard = new Board(this);
                    aBoard.makeMove(grid[posOfZero.X+1][posOfZero.Y]);
                    children.add(aBoard);
                }
                if (posOfZero.Y + 1 <= Constants.dimY - 1) {
                    Board aBoard = new Board(this);
                    aBoard.makeMove(grid[posOfZero.X][posOfZero.Y+1]);
                    children.add(aBoard);
                }
                if (posOfZero.X - 1 >= 0) {
                    Board aBoard = new Board(this);
                    aBoard.makeMove(grid[posOfZero.X-1][posOfZero.Y]);
                    children.add(aBoard);
                }
                if (posOfZero.Y - 1 >= 0) {
                    Board aBoard = new Board(this);
                    aBoard.makeMove(grid[posOfZero.X][posOfZero.Y-1]);
                    children.add(aBoard);
                }


        return children;
    }
}
class Coordinate{
    int X;
    int Y;

    public Coordinate(int x, int y) {
        X = x;
        Y = y;
    }
}
