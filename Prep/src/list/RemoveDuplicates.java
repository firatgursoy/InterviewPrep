package list;

import java.util.HashSet;
import java.util.Set;


/**
 * @author echow23
 *
 */
public class RemoveDuplicates {

    static class Node<T> {
        T data;
        Node<T> next;
        
        Node() {
            
        }
        
        Node(T data) {
            this.data = data;
        }
        
        public String toString() {
            return data + (next != null ? "," + next : "");
        }
    }
    
    
    /**
     * Remove duplicates using runner
     * time: O(N^2)
     * space: O(1)
     * 
     * @param head
     */
    static <T> void removeDuplicates(Node<T> head) {
        if (head == null) return;
        
        Node<T> current = head;
        
        while (current != null) {
            Node<T> runner = current;
            
            while(runner.next != null) {
                if (current.data.equals(runner.next.data)) {
                    Node<T> ahead = runner.next;
                    runner.next = runner.next.next;
                    ahead.next = null;
                } else {
                    runner = runner.next;
                }
                
            }
            current = current.next;
        }
    }
    
    /**
     * Remove duplicates using storage;
     * O(N) time 
     * O(N) space
     * 
     * @param head
     */
    static <T> void removeDuplicateStorage(Node<T> head) {
        if (head == null) return;

        Set<T> set = new HashSet<>();
        
        Node<T> current = head;
        Node<T> prev = current;
        while (current != null) {
            if (set.contains(current.data)) {
                Node<T> next = current.next;
                prev.next = next;
                current.next = null;
                current = next;
            } else {
                set.add(current.data);
                prev = current;
                current = current.next;
            }
        }
        
    }
    
    public static void main(String[] args) {
        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> twoA = new Node<>(2);
        Node<Integer> three = new Node<>(3);
        Node<Integer> threeA = new Node<>(3);
        Node<Integer> four = new Node<>(4);
        Node<Integer> fourA = new Node<>(4);
        
        one.next = two;
        two.next = twoA;
        twoA.next = three;
        three.next = threeA;
        threeA.next = four;
        four.next = fourA;
        
        System.out.println(one);
        
        removeDuplicateStorage(one);
        
        System.out.println(one);
    }
}
