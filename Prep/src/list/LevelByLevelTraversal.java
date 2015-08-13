package list;

import java.util.ArrayList;
import java.util.List;

public class LevelByLevelTraversal {
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
        
        if (root.mLeftNode == first || root.mRightNode == second ||
            root.mLeftNode == second || root.mRightNode == first) {
            return root;
        } else {
            Node left = commonAncestor(root.mLeftNode, first, second);
            Node right = commonAncestor(root.mRightNode, first, second);
            
            if (left != null && right != null) {
                return root;
            } else if(left != null && right == null) {
                return left;
            } else {
                return right;
            }
            
        }
        
    }
    
    public void printLevelOrder(List<List<Integer>> lists, Node rootNode, int level)
    {
        if (rootNode != null) {
            
            List<Integer> list;
            
            if (lists.size() == level) {
                list = new ArrayList<Integer>();
                lists.add(list);
            } else {
                list = lists.get(level);
            }
            list.add(rootNode.mData);
            
            printLevelOrder(lists, rootNode.mLeftNode, level + 1);
            printLevelOrder(lists, rootNode.mRightNode, level + 1);
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
      LevelByLevelTraversal traversalObj = new LevelByLevelTraversal();
      Node root = traversalObj.constuctTree();
      
      List<List<Integer>> lists = new ArrayList<>();
      traversalObj.printLevelOrder(lists, root, 0);
      
      int depth = 0;
      
      for (List<Integer> list : lists) {
          System.out.println("depth: " + depth);
          for (Integer integer : list) {
              System.out.println(integer);
          }
          depth++;
      }
    }
}
