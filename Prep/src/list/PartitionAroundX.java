package list;

import list.HasLoopSolution.Node;

public class PartitionAroundX {

    static class Node {
        Integer data;
        Node next;
        
        Node(Integer data) {
            this.data = data;
        }
        
        public String toString() {
            return data + (next != null ? "," + next : "");
        }
    }
    
    static Node partitionAround(int x, Node node) {
        Node beforeX = null;
        Node afterX = null;
        
        while (node != null) {
            Node next = node.next;
            
            if(node.data <= x) {
                node.next = beforeX;
                beforeX = node;
            } else {
                node.next = afterX;
                afterX = node;
            }
            
            node = next;
        }

        if (beforeX == null) {
            return afterX;
        }
        
        Node head = beforeX;
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        current.next = afterX;
        
        return head;
    }
    
    public static void main(String[] args) {
        Node three = new Node(3);
        Node one = new Node(1);
        Node two = new Node(2);
        
        Node seven = new Node(7);
        Node six = new Node(6);
        Node five = new Node(5);

        three.next = one;
        one.next = two;
        two.next = seven;
        seven.next = six;
        six.next = five;
        
        System.out.println(three);
        
        Node result = partitionAround(4,  three);
        
        System.out.println("result: " + result);
    }
}
