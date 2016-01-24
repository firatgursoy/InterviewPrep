package list;


public class KthToLastNodeSolution {

    static class Node<T> {
        T data;
        Node<T> next;
        
        Node(T data) {
            this.data = data;
        }
        
        public String toString() {
            return data + (next != null ? ", " + next : "");
        }
    }
    
    static <T> Node<T> kthToLast(int k, Node<T> head) {
        if (k <= 0) return null;
        
        Node<T> slow = head;
        Node<T> fast = head;
        
        while (k > 1) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
            k--;
        }
        if (fast == null) {
            return null;
        }
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
    
    public static void main(String[] args) {
        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> three = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        
        one.next = two;
        two.next = three;
        three.next = four;
        
        System.out.println(one);
        
        Node<Integer> result = kthToLast(2, one);
        
        System.out.println(result.data);
    }
}
