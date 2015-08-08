package stack;

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}

class Stack<T> {
    private Node<T> top;
    
    public void push(T data) {
        Node<T> t = new Node<T>(data);
        t.next = top;
        top = t;
    }
    
    public T pop() {
        if (top != null) {
            T item = top.data;
            top = top.next;
            return item;
        }
        return null;
    }
    
    public T peek() {
        if (top != null) {
            return top.data;
        }
        
        return null;
    }
}

public class StackSolution {

}
