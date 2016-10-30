import java.util.ArrayList;


/**
 * Created by syedb on 10/17/2016.
 */
public class Node implements Comparable{
    Board board;
    int heuristic;
    ArrayList<Node> nextNode;

    Node(Board b){
        board = new Board(b);
        heuristic = board.currentHeuristic();
    }

    public void addPathNode(Node n){
        if(nextNode == null)
            nextNode = new ArrayList<>();
        nextNode.add(n);
    }

    @Override
    public int compareTo(Object o) {
        Node n = (Node)o;
        if(this.board.equals(n.board))
            return 0;
        else if(this.heuristic < n.heuristic){
            return -1;
        }
        else
            return 1;
    }
}
