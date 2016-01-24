package list;

public class HasLoopSolution {

    static class Node<T> {
        T data;
        Node<T> next;
    }
    
    static <T> boolean hasLoop(Node<T> head) {
        Node<T> slow = head;
        Node<T> fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true;
            }
        }
        
        if (fast == null || fast.next == null) {
            return false;
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        Node<Integer> one = new Node<>();
        Node<Integer> two = new Node<>();
        Node<Integer> three = new Node<>();
        
        one.next = two;
        two.next = three;
        
        //TODO: toggle here to change test
        //three.next = null;
        three.next = two;
        
        System.out.println("Has Loop? " + hasLoop(one));
    }
}
