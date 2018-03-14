import structure.AVLTree;

/**
 * Created by GongWenBo on 2018/3/2.
 */
public class Test {

    public static void main(String[] args){
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        System.out.println("---");
    }

}
