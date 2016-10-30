import java.util.ArrayList;


/**
 * Created by syedb on 10/17/2016.
 */
public class Node implements Comparable {
    Board board;
    int heuristic;
    Board previousBoard;
    ArrayList<Node> nextNode;

    Node(Board a, Board b) {
        previousBoard = a;
        board = new Board(b);
        heuristic = board.currentHeuristic();
    }

    @Override
    public int compareTo(Object o) {
        Node n = (Node) o;
        if (this.board.equals(n.board))
            return 0;
        else if (this.heuristic < n.heuristic) {
            return -1;
        } else
            return 1;
    }
}
