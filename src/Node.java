/**
 * A node containing a board and its heuristic as well as the predecessor board leading to it. previousBoard will always be null for a root node.
 * Created by 1530B
 */


/*
 * Don't have default permissions on varibles
 * Naming isn't good. If you have a constructor like Node a and b mean nothing. Something like prev would be better. Of course it doesn't matter much here but if things gro then it could get confusing.
 */


public class Node implements Comparable {
    Board board;
    int heuristic;
    Board previousBoard;

    Node(Board a, Board b) {
        previousBoard = a;
        board = new Board(b);
        heuristic = board.currentHeuristic();
    }

    /**
     * Comparator function for Node. Since boards of different orientations can have the same heuristic, calls the board's equality function instead.
     * @param o
     * @return
     */
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
