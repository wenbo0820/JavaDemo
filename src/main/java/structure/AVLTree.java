package structure;


/**
 * Created by GongWenBo on 2018/3/6.
 */
public class AVLTree<T extends Comparable<T>> {

    private AVLNode<T> root;

    public void insert(T key) {
        root = insert(root, key);
    }

    private AVLNode<T> insert(AVLNode<T> node, T key) {
        if (node == null) {
            node = new AVLNode<T>(key, 1, null, null);
        } else {
            int cmp = node.key.compareTo(key);
            if (cmp < 0) {
                node.right = insert(node.right, key);
                if (height(node.right) - height(node.left) == 2) {
                    if (key.compareTo(node.right.key) > 0) {
                        node = leftRotate(node);
                    } else if (key.compareTo(node.right.key) < 0) {
                        node = RLRotate(node);
                    }
                }
            } else if (cmp > 0) {
                node.left = insert(node.left, key);
                if (height(node.left) - height(node.right) == 2) {
                    if (key.compareTo(node.left.key) > 0) {
                        node = LRRotate(node);
                    } else if (key.compareTo(node.left.key) < 0) {
                        node = rightRotate(node);
                    }
                }
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }




    public void remove(T key){

    }

    private AVLNode<T> remove(AVLNode<T> tree,T key){
        if (tree == null || key == null){
            return null;
        }
        int cmp = tree.key.compareTo(key);
        if (cmp == 0){
            //TODO
        }
        return null;
    }


    private AVLNode<T> search(AVLNode<T> root, T key) {
        if (root == null) {
            return null;
        }
        int i = root.key.compareTo(key);
        if (i == 0) {
            return root;
        } else if (i < 0) {
            return search(root.right,key);
        } else {
            return search(root.left, key);
        }
    }


    /**
     * 右旋  ,针对 LL型
     *
     * @param k1
     * @return
     */
    private AVLNode rightRotate(AVLNode k1) {
        AVLNode k2 = k1.left;
        k1.left = k2.right;
        k2.right = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        return k2;
    }

    /**
     * 左旋 ， 针对 RR型
     *
     * @param k1
     * @return
     */
    private AVLNode leftRotate(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        return k2;
    }

    /**
     * L-R 旋转 ，先进行一次 左旋，然后进行一次右旋，针对  左右 类型
     *
     * @param k1
     * @return
     */
    private AVLNode LRRotate(AVLNode k1) {
        k1.left = leftRotate(k1.left);
        return rightRotate(k1);
    }

    /**
     * R-L 旋转 ，先进行右旋，再进行左旋 ，针对  右左 类型
     *
     * @param k1
     * @return
     */
    private AVLNode RLRotate(AVLNode k1) {
        k1.right = rightRotate(k1.right);
        return leftRotate(k1);
    }


    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    static class AVLNode<T extends Comparable<T>> {
        T key;
        int height;
        AVLNode<T> left;
        AVLNode<T> right;

        public AVLNode(T key, int height, AVLNode<T> left, AVLNode<T> right) {
            this.key = key;
            this.height = height;
            this.left = left;
            this.right = right;
        }
    }
}
