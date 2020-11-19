public interface AVL_interface {


    void
    insert(int key, String info);
    void ReBalance(Node node);

    void successor(int key);
    void predecessor(int key);
    void leftleftRotate(Node node);
    void rightRotate(Node node);
    void doubleRotate(Node node);



}
