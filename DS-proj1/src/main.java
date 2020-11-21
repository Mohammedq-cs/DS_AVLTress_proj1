public class main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        for (int i = 1000; i > 0; i--){
            tree.insert(i, "gg");
        }

        tree.insert(100, "d");
        System.out.println(tree.root);
        System.out.println(tree.Depth());

    }



}
