package trees;

class TreeNode<T> {
    T data;
    TreeNode<T> left;
    TreeNode<T> right;
    
    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

public class BinaryTree<T extends Comparable<T>> {
    private TreeNode<T> rootNode;
    
    public void insert(T item) {
        if (item == null) {
            return;
        }
        
        if (this.rootNode == null) {
            this.rootNode = new TreeNode<T>(item, null, null);
        } else {
            insert(this.rootNode, item);
        }
    }
    
    private void insert(TreeNode<T> node, T item) {
        if (node == null || item == null) {
            return;
        }
        
        if (item.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new TreeNode<T>(item, null, null);
            } else {
                insert(node.left, item);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode<T>(item, null, null);
            } else {
                insert(node.right, item);
            }
        }
    }
    
}
