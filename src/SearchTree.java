import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by syedb on 10/17/2016.
 */
public class SearchTree {
    public PriorityQueue<Node> boardQueue;
    public HashSet<Node> oldBoards;
    Node rootNode;
    public SearchTree(Node n){
        rootNode = new Node(n.board);

        boardQueue = new PriorityQueue<>();
        oldBoards = new HashSet<>();

        oldBoards.add(rootNode);
    }

    public void addNode(Node n){
        if(!oldBoards.contains(n)) {
            boardQueue.offer(n);
            oldBoards.add(n);
        }
    }
    public Node pop(){
        Node ret = boardQueue.poll();
        if(ret != null)
            rootNode.addPathNode(ret);
        return ret;
    }
    public void Empty(){
        boardQueue.clear();
    }
}
