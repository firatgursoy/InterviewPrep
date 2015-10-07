package trees;

public class CommonAncestorSolution {

    private class Node {
        int mData;
        Node parent;
        Node mLeftNode;
        Node mRightNode;
    };

    private Node newNode(int data, Node leftNode, Node rightNode) {
        Node node = new Node();
        node.mData = data;
        node.mLeftNode = leftNode;
        node.mRightNode = rightNode;

        if (leftNode != null) {
            leftNode.parent = node;
        }

        if (rightNode != null) {
            rightNode.parent = node;
        }

        return node;
    }

    /*
     * example: root, 2, 3 --> 1
     */

    public Node commonAncestor(Node root, Node first, Node second) {

        if (root == null) {
            return null;
        }

        if (root.mLeftNode == first || root.mRightNode == second || 
            root.mLeftNode == second || root.mRightNode == first) {
            return root;
        } else {
            Node left = commonAncestor(root.mLeftNode, first, second);
            Node right = commonAncestor(root.mRightNode, first, second);

            if (left != null && right != null) {
                return root;
            } else if (left != null && right == null) {
                return left;
            } else {
                return right;
            }

        }
    }

    public Node constuctTree() {

        Node rootNode =
                        newNode(1,
                                        newNode(2, newNode(4, newNode(8, null, null), newNode(9, null, null)),
                                                        newNode(5, newNode(10, null, null), newNode(11, null, null))),
                                        newNode(3, newNode(6, null, null),
                                                        newNode(7, newNode(12, null, null), newNode(13, null, null))));

        return rootNode;
    }
}
