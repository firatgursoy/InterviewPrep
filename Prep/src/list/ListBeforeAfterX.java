package list;

public class ListBeforeAfterX {

    private class Node
    {
        int data;
        Node next;
    };

    public static Node pivot(Node node, int num) {
        Node before = null;
        Node after = null;
        
        while(node != null) {
            Node next = node.next;
            
            if (node.data < num) {
                node.next = before;
                before = node;
            } else {
                node.next = after;
                after = node;
            }

            node = next;
        }
        
        if (before == null) {
            return after;
        }
        
        Node head = before;
        while(before.next != null) {
            before = before.next;
        }
        before.next = after;
        
        return head;
    }
    
    public static void main(String[] args) {
        
    }
}
