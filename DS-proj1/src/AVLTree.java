import java.util.Arrays;

public class AVLTree{
    protected Node root;

    private final Node ExternalNode = new Node(-1 ,Integer.MIN_VALUE);

    public AVLTree() {
        this.root = null;
    }

    public AVLTree(int key, String info) {

        this.root = new Node(key, info);
    }

    public void insert(int key, String info) {
        Node inserted_node;
        if (this.root == null){
            this.root = new Node(key,info, ExternalNode, null, ExternalNode);
            inserted_node = this.root;
        }
        else{
            inserted_node = insert_Recursive(this.root, key, info);
            ReBalance(inserted_node);
        }

    }

    private Node insert_Recursive(Node root, int key, String info) {//assumption that we don't insert same key
        Node leaf = Tree_Position(root, key);
        if(leaf.key == key){
            return leaf;
        }
        Node to_insert = new Node(key,info, ExternalNode, leaf, ExternalNode);
        if(leaf.key < key){
            leaf.right = to_insert;
        }else {
            leaf.left = to_insert;
        }
        return to_insert;
    }


    public void ReBalance(Node node) {
        if(node.parent != null){
        int [] rank_difference_for_node = rankDifference(node);
        int [] rank_difference_for_parent_node = rankDifference(node.parent);
        //{1, 1} me and my parent
        if (((Arrays.equals(rank_difference_for_node, new int[]{1, 1}) ||
                (Arrays.equals(rank_difference_for_node, new int[]{2, 1})) ||
                (Arrays.equals(rank_difference_for_node, new int[]{1, 2})))
                && (Arrays.equals(rank_difference_for_parent_node, new int[]{1, 0})||
                (Arrays.equals(rank_difference_for_parent_node, new int[]{0, 1})) ))){
                    Node.Promote(node.parent);
                    ReBalance(node.parent);
            }
            if (Arrays.equals(rank_difference_for_node, new int[]{2, 1}) // single-rotation case left
                    && Arrays.equals(rank_difference_for_parent_node, new int[]{2, 0})){
                leftRotate(node);
            }
            if (Arrays.equals(rank_difference_for_node, new int[]{1, 2}) // single rotation case right
                    && Arrays.equals(rank_difference_for_parent_node, new int[]{0, 2})){
                rightRotate(node);
            }

            if (Arrays.equals(rank_difference_for_node, new int[]{2, 1}) // double rotation case right
                    && Arrays.equals(rank_difference_for_parent_node, new int[]{0, 2})){
                leftrightRotate(node);
            }
            if (Arrays.equals(rank_difference_for_node, new int[]{1, 2}) // double rotation case right
                    && Arrays.equals(rank_difference_for_parent_node, new int[]{2, 0})){
                rightleftRotate(node);
            }

        }




    }




    public void successor(int key) {

    }


    public void predecessor(int key) {

    }


    public void rightRotate(Node node) {
        Node left_node = node.left;
        Node right_node = node.right;
        Node parent_node = node.parent;

        ParentNodeSetter(node);

        node.right = node.parent;
        node.parent = parent_node.parent;
        parent_node.parent = node;
        parent_node.left = right_node;

        Node.Demote(parent_node);

    }


    public void leftRotate(Node node) {
        Node left_node = node.left;
        Node right_node = node.right;
        Node parent_node = node.parent;
        ParentNodeSetter(node);

        node.left = node.parent;
        node.parent = parent_node.parent;
        parent_node.parent = node;
        parent_node.right = left_node;

        Node.Demote(parent_node);
    }

    public void leftrightRotate(Node node){
        Node nodee = node.right;
        leftRotate(nodee);
        rightRotate(nodee);
        Node.Promote(nodee);
    }

    public void rightleftRotate(Node node){
        Node nodee = node.left;
        rightRotate(nodee);
        leftRotate(nodee);
        Node.Promote(nodee);
    }


    private void ParentNodeSetter(Node node){
        Node parent_node = node.parent;
        if(parent_node.parent == null){
            this.root = node;
        }
        if(parent_node.parent != null){
            if(parent_node.parent.right == parent_node){
                parent_node.parent.right = node;
            }
            else {
                parent_node.parent.left = node;
            }
        }
    }
    public void doubleRotate(Node node) {

    }

    // other help methods
    public static int[] rankDifference(Node node){
        if (node != null) return new int[] {node.rank - node.left.rank, node.rank - node.right.rank};
        return new int[] {};
    }

    private Node Tree_Position(Node node, int k){
        Node y = null;
        while (node != ExternalNode){
            y = node;
            if(node.key == k){
                return node;
            }
            if (k < node.key){
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return y;
    }

    public int Depth(){
        return maxDepth(root);
    }

    public int maxDepth(Node root) {
        if(root == ExternalNode)
            return -1;
        int left_Depth = maxDepth(root.left);
        int right_Depth = maxDepth(root.right);
        int bigger = Math.max(left_Depth, right_Depth);
        return bigger+1;
    }
}
