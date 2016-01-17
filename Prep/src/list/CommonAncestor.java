package list;


public class CommonAncestor {
    private class Node
    {
        int mData;
        Node parent;
        Node mLeftNode;
        Node mRightNode;
    };

    /*
     * example: root, 2, 3 --> 1
     * 
     */
    
    public Node commonAncestor(Node root, Node first, Node second) {
        
        if (root == null) {
            return null;
        }
        
        /*if (root.mLeftNode == first || root.mRightNode == second ||
            root.mLeftNode == second || root.mRightNode == first) {
            return root;
        } */
        if (root == first || root == second) {
            return root;
        }
        else {
            Node left = commonAncestor(root.mLeftNode, first, second);
            Node right = commonAncestor(root.mRightNode, first, second);
            
            if (left != null && right != null) {
                return root;
            } 
            
            return (left != null) ? left : right;
        }
        
    }

    private Node newNode(int data, Node leftNode, Node rightNode)
    {
      Node node = new Node();
      node.mData = data;
      node.mLeftNode = leftNode;
      node.mRightNode = rightNode;
      
      if(leftNode != null){
        leftNode.parent = node;
      }
      
      if(rightNode != null){
        rightNode.parent = node;
      }

      return node;
    }

    public Node constuctTree(){

       Node rootNode = newNode(1,
                                newNode(2,
                                    newNode(4,
                                            newNode(8,null,null),
                                            newNode(9,null,null)),
                                    newNode(5,
                                            newNode(10,null,null),
                                            newNode(11,null,null))),
                                newNode(3,
                                    newNode(6, null, null),
                                    newNode(7,
                                            newNode(12,null,null),
                                            newNode(13,null,null))
                                        ));

       return rootNode;
    }

    public static void main(String[] args) {
      System.out.print("Level Order traversal of binary tree is \n");
      CommonAncestor traversalObj = new CommonAncestor();
      Node root = traversalObj.constuctTree();
      
      int trialOne = traversalObj.commonAncestor(root, root.mLeftNode, root.mRightNode).mData;
      int trialTwo = traversalObj.commonAncestor(root, root.mLeftNode.mLeftNode, root.mRightNode).mData;
      int trialThree = traversalObj.commonAncestor(root, root.mRightNode.mLeftNode, root.mRightNode.mRightNode.mLeftNode).mData;
      
      System.out.println("common ancestor for 2 and 3: " + trialOne);
      System.out.println("Trial 4-3: " + trialTwo);
      System.out.println("Trial 6-12: " + trialThree);
     
    }
}
