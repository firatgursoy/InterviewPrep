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
    
    public Node reverse(Node curr) {
        Node next = null;
        Node prev = null;
        
        while (curr != null) {
            next = curr.next;
            //reverse link
            curr.next = prev;
            
            //update pointers
            prev = curr;
            curr = next;
        }
        
        
        return prev;
    }
}
