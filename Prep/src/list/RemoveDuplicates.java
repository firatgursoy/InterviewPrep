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
        for( ;current != null; current = current.next) {
            if (set.contains(current.data)) {
                prev.next = current.next;
                current.next = null;
            } else {
                set.add(current.data);
                prev = current;
            }
        }
        
    }
    
    public static void main(String[] args) {
        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> twoA = new Node<>(2);
        Node<Integer> three = new Node<>(3);
        
        one.next = two;
        two.next = twoA;
        twoA.next = three;
        
        System.out.println(one);
        
        removeDuplicates(one);
        
        System.out.println(one);
    }
}
