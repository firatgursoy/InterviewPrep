package stack;

public class MinStack<T extends Comparable<T>> extends java.util.Stack<T> {
    private java.util.Stack<T> minStack = new java.util.Stack<>();
    
    public T push(T data) {
        if (data == null) return null;
        
        if (min() == null || data.compareTo(min()) < 0) {
            minStack.push(data);
        }
        super.push(data);
        
        return data;
    }
    
    public T pop() {
        if (minStack.peek().equals(this.peek())) {
            minStack.pop();
        }
        return this.pop();
    }
    
    public T min() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        return null;
    }
    
    public static void main(String[] args) {
        MinStack<Integer> min = new MinStack<>();
        min.push(10);
        min.push(9);
        min.push(-11);
        min.push(-10);
        
        System.out.println(min.min());
    }
}
