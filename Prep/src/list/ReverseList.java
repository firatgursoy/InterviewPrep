package list;

public class ReverseList {

    private class Node {
        public int value;
        public Node next;
    }
    
    public Node reverseList(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        
        Node remaining = reverseList(node.next);
        node.next.next = node;
        node.next = null;
        return remaining;
    }
}
