import java.util.LinkedList;

public class Node {
    protected Node left;
    protected Node parent;
    protected Node right;
    protected int rank; // also we can use Height
    protected int key;
    protected String info;

    public Node(int key) {
        this.key = key;
        this.right = null;
        this.left = null;
        this.rank = 0;
    }

    public Node(int rank, int key) {
        this.rank = rank;
        this.key = key;
    }

    public Node(int key, String info) {
        this.key = key;
        this.info = info;
        this.parent = this.right = this.left = null;
        this.rank = 0;

    }

    public Node(Node parent, String info, int key) {
        this.parent = parent;
        this.rank = 0;
        this.key = key;
        this.left = null;
        this.right = null;
        this.info = info;
    }

    public Node(int key, String info, Node left, Node parent, Node right) {
        this.left = left;
        this.parent = parent;
        this.right = right;
        this.rank = 0;
        this.key = key;
        this.info = info;
    }


    public static void Promote(Node node) {
        if (node != null) {
            node.rank += 1;
        }
    }

    public static void Demote(Node node) {
        if (node != null) {
            node.rank -= 1;
        }
    }

    @Override
    public String toString() {
        if (this.left != null && this.right != null &&  this.parent != null) {
            return "Node{" +
                    "left=" + left.key +
                    ", parent=" + parent.key +
                    ", right=" + right.key +
                    ", rank=" + rank +
                    ", key=" + key +
                    ", info='" + info + '\'' +
                    '}';
        }
        else
            {
                if (this.left != null && this.right != null) {
                    return "Node{" +
                            "left=" + left.key +
                            ", right=" + right.key +
                            ", rank=" + rank +
                            ", key=" + key +
                            ", info='" + info + '\'' +
                            '}';
                }
            return "Node{" +
                    ", rank=" + rank +
                    ", key=" + key +
                    ", info='" + info + '\'' +
                    '}';
        }
    }
}