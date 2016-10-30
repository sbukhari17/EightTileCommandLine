import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by syedb on 10/17/2016.
 */
public class SearchTree {
    public PriorityQueue<Node> boardQueue;
    //public HashSet<String> oldBoards;
    public HashMap<String, Node> oldBoards;
    Board bestBoardFound;
    int bestBoardHeuristic;
    Node rootNode;

    public SearchTree(Node n) {
        rootNode = new Node(null, n.board);
        bestBoardFound = new Board(n.board);
        bestBoardHeuristic = n.heuristic;
        boardQueue = new PriorityQueue<>();
        //oldBoards = new HashSet<>();
        oldBoards = new HashMap<>();

        oldBoards.put(convertBoardToStringSequence(n.board), n);
    }

    public void addNode(Node n) {
        String strSeq = convertBoardToStringSequence(n.board);
        if (!oldBoards.containsKey(strSeq)) {
            if (n.heuristic < bestBoardHeuristic) {
                bestBoardFound = n.board;
                bestBoardHeuristic = n.heuristic;
            }
            boardQueue.offer(n);
            oldBoards.put(strSeq, n);
        }
    }

    public Node pop() {
        Node ret = boardQueue.poll();
        return ret;
    }

    public String convertBoardToStringSequence(Board b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Constants.dimX; i++) {
            for (int j = 0; j < Constants.dimY; j++) {
                sb.append(b.grid[i][j]);
            }
        }
        return sb.toString();
    }

    public ArrayList<Board> createPath() {
        ArrayList<Board> path = new ArrayList<>();
        pathHelper(oldBoards.get(convertBoardToStringSequence(bestBoardFound)), path);
        Collections.reverse(path);
        return path;
    }

    public void pathHelper(Node n, ArrayList<Board> path) {
        if (n.previousBoard == null) {
            return;
        }
        path.add(n.board);
        pathHelper(oldBoards.get(convertBoardToStringSequence(n.previousBoard)), path);
    }
}
