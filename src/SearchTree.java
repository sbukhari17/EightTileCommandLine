import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by syedb on 10/17/2016.
 */
public class SearchTree {
    public PriorityQueue<Node> boardQueue;
    public HashSet<String> oldBoards;
    Node rootNode;
    public SearchTree(Node n){
        rootNode = new Node(n.board);

        boardQueue = new PriorityQueue<>();
        oldBoards = new HashSet<>();

        oldBoards.add(convertBoardToStringSequence(n));
    }

    public void addNode(Node n){
        String strSeq = convertBoardToStringSequence(n);
        if(!oldBoards.contains(strSeq)) {
            boardQueue.offer(n);
            oldBoards.add(strSeq);
        }
    }
    public Node pop(){
        Node ret = boardQueue.poll();
        if(ret != null)
            rootNode.addPathNode(ret);
        return ret;
    }
    public String convertBoardToStringSequence(Node n){
        Board b = n.board;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< Constants.dimX; i++){
            for(int j =0 ; j< Constants.dimY; j++){
                sb.append(b.grid[i][j]);
            }
        }
        return sb.toString();
    }
    public void Empty(){
        boardQueue.clear();
    }
}
