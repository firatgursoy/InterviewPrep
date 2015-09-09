package trees;

import java.util.ArrayList;
import java.util.List;

public class LevelOrderTraversal {
    
    private class Node
    {
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
    
    public static void printLevelOrder(List<List<Integer>> lists, Node rootNode, int level)
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
    
    public static void main(String[] args) {
        LevelOrderTraversal solution = new LevelOrderTraversal();
        
        Node root = solution.constuctTree();
        
        List<List<Integer>> lists = new ArrayList<>();
        
        printLevelOrder(lists, root, 0);
    }
}
