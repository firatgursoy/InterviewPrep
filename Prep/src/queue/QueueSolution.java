package queue;

import java.util.Stack;

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}

class Queue<T> {
    Node<T> first, last;
    
    public void enqueue(T data) {
        if (first == null) {
            last = new Node<T>(data);
            first = last;
        } else {
            last.next = new Node<T>(data);
            last = last.next;
        }
    }
    
    public T dequeue() {
        if (first == null) {
            T item = first.data;
            first = first.next;
            return item; 
        }
        return null;
    }
}

// implement Queue with two stacks
class MyQueue<T> {
    private Stack<T> newStack, oldStack;
    
    public MyQueue() {
        newStack = new Stack<T>();
        oldStack = new Stack<T>();
    }
    
    public int size() {
        return newStack.size() + oldStack.size();
    }
    
    public void add(T item) {
        newStack.push(item);
    }
    
    public T remove() {
        shiftStacks();
        return oldStack.pop();
    }
    
    public T peek() {
        shiftStacks();
        return oldStack.peek();
    }
    
    private void shiftStacks() {
        if (oldStack.isEmpty()) {
            while (!newStack.isEmpty()) {
                oldStack.push(newStack.pop());
            }
        }
    }
}


public class QueueSolution {

}
