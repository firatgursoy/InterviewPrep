package trees;

class Node {
    Integer value;
    
    Node left;
    Node right;
}

public class IsBinaryTreeSolution {

    public boolean isBinaryTree(Node root) {
        return isBinaryTree(root, null, null);
    }
    
    public boolean isBinaryTree(Node node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        
        if (    min != null && node.value < min
                        ||
                max != null && node.value > max
            ){
            return false;
        }
        
        return isBinaryTree(node.left, min, node.value) && 
               isBinaryTree(node.right, node.value, max);
    }
    
}
