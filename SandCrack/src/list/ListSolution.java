package list;


class Node<T extends Comparable<T>> {
    Node<T> next;
    T data;

    public Node(Node<T> next, T data) {
        this.next = next;
        this.data = data;
    }
    
    public String toString() {
        return data + (next != null ? "," + next : "");
    }
}

class List<T extends Comparable<T>> {
    Node<T> head;
    Node<T> tail;

    public void partition(T n) {
        Node<T> before = null;
        Node<T> after = null;
        
        Node<T> current = head;
        
        while (current != null) {
            Node<T> next = current.next;
            
            if (current.data.compareTo(n) <= 0) {
                current.next = before;
                before = current;
            } else {
                current.next = after;
                after = current;
            }
            current = next;
        }
    }
    
    public void addToFront(T data) {
        if (head == null) {
            head = new Node<T>(null, data);
            tail = head;
        } else {
            Node<T> node = new Node<T>(null, data);
            node.next = head;
            head = node;
        }
        
    }
    
    public void addToEnd(T data) {
        if (head == null) {
            head = new Node<T>(null, data);
            tail = head;
        } else {
            Node<T> node = new Node<T>(null, data);
            tail.next = node;
            tail = node;
        }
    }
    
    public void delete(T data) {
        
        if (head != null && head.data.equals(data)) {
            deleteHead();
        }
        else if (tail != null && tail.data.equals(data)) {
            deleteTail();
        } else {
            Node<T> current = head;
            
            while(current.next != null) {
                if (current.next.data.equals(data)) {
                    current.next = current.next.next;
                    break;
                }
                current = current.next;
            }
        }
        
    }

    private void deleteHead() {
        if(head != null) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node<T> current = head;
                head = head.next;
                current.next = null;
            }
        }
    }
    
    private void deleteTail() {
        if (tail != null) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node<T> current = head;
                while(current.next != tail) {
                    current = current.next;
                }
                
                tail = current;
                tail.next = null;
            }
        }
    }
    
    public String toString() {
        return "[" + (head != null ? head : "") + "]";
    }
}


public class ListSolution {
    public static void main(String[] args) {
        List<Integer> list = new List<>();
        
        list.addToEnd(6);
        list.addToEnd(3);
        list.addToEnd(2);
        
        System.out.println(list);
        
        list.partition(4);
    }
}
