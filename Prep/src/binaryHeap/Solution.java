package binaryHeap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * A priority queue is an abstract data type which is like a regular queue or stack data structure,
 * but where additionally each element has a "priority" associated with it. 
 * In a priority queue, an element with high priority is served before an element with low priority.
 * 
 * @author echow23
 *
 * @param <T>
 */
interface PriorityQueue<T> {
    
    /**
     * Return the number of elements.
     */
    int size();
    
    /**
     * Insert a value.
     */
    void insert(T value);
    
    /**
     * Return the first value in the heap without removing it
     */
    T peek();
    
    /**
     * Remove and return the first value from the heap
     */
    T remove();

    /**
     * Return is empty or not.
     */
    boolean isEmpty();
}

class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    private static final int DEFAULT_SIZE = 20;
    private T[] heap;
    private int size;
    private Comparator<T> comparator;
    
    @SuppressWarnings("unchecked")
    public BinaryHeap() {
        heap = (T[]) new Comparable[DEFAULT_SIZE];
        size = 0;
    }
    
    @SuppressWarnings("unchecked")
    public BinaryHeap(Comparator<T> comparator) {
        this();
        this.comparator = comparator;
    }
    
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void insert(T value) {
        if (this.size >= heap.length - 1) {
            this.expand();
        }
        
        this.size++;
        int index = this.size;
        heap[index] = value;
        
        if (comparator != null) {
            percolateUpUsingComparator();
        } else {
            percolateUp();
        }
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("heap is empty");
        }
        
        return heap[1];
    }

    @Override
    public T remove() {
        T root = peek();
        
        heap[1] = heap[size()];
        heap[size()] = null;
        size--;
        
        if (comparator != null) {
            percolateDownUsingComparator();
        } else {
            percolateDown();
        }
        
        return root;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public String toString() {
        return Arrays.toString(this.heap);
    }
    
    //////////////////////////////////////////////
    /// PRIVATE METHODS
    //////////////////////////////////////////////
    
    /**
     * Compare node's value with parent's value. If they are in wrong order, swap them
     */
    private void percolateUp() {
        int index = size();
        
        while(hasParent(index) && 
            getParent(index).compareTo(heap[index]) > 0) {
            
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }
    
    /**
     * Compare node's value with parent's value. If they are in wrong order, swap them
     */
    private void percolateUpUsingComparator() {
        int index = size();
        
        while(hasParent(index) && 
            this.comparator.compare(getParent(index), heap[index]) > 0) {
            
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }
    
    /**
     * Compare node's value with parent's value. If they are in wrong order, swap them
     */
    private void percolateDown() {
        int index = 1;
        
        while(hasLeftChild(index)) {
            int left = getLeftChild(index);
            
            if (hasRightChild(index) 
                && heap[getLeftChild(index)].compareTo(heap[getRightChild(index)]) > 0) {
                left = getRightChild(index);
            }
            
            if (heap[index].compareTo(heap[left]) > 0) {
                swap(index, left);
            } else {
                break;
            }
            
            index = left;
        }
    }
    
    /**
     * Compare node's value with parent's value. If they are in wrong order, swap them
     */
    private void percolateDownUsingComparator() {
        int index = 1;
        
        while(hasLeftChild(index)) {
            int left = getLeftChild(index);
            
            if (hasRightChild(index) 
                && 
                comparator.compare(heap[getLeftChild(index)], heap[getRightChild(index)]) > 0) {
                left = getRightChild(index);
            }
            
            if (comparator.compare(heap[index], heap[left]) > 0) {
                swap(index, left);
            } else {
                break;
            }
            
            index = left;
        }
    }
    
    /**
     * Expand heap
     */
    @SuppressWarnings("unchecked")
    private void expand() {
        int newSize = size() * 2;
        T[] newHeap = (T[]) new Comparable[newSize];
        for (int i = 0; i < this.heap.length; i++) {
            newHeap[i] = heap[i];
        }
        
        heap = newHeap;
    }
    
    /**
     * Does parent exist
     * 
     * @param index
     * @return
     */
    private boolean hasParent(int index) {
        return index > 1;
    }
    
    /**
     * Does left child exist
     * 
     * @param index
     * @return
     */
    private boolean hasLeftChild(int index) {
        return getLeftChild(index) <= size();
    }
    
    /**
     * Does right child exist
     * 
     * @param index
     * @return
     */
    private boolean hasRightChild(int index) {
        return getRightChild(index) <= size();
    }

    /**
     * Get left child
     * 
     * @param index
     * @return
     */
    private int getLeftChild(int index) {
        return index * 2;
    }
    
    /**
     * Get right child
     * 
     * @param index
     * @return
     */
    private int getRightChild(int index) {
        return index * 2 + 1;
    }
    
    /**
     * Get parent
     * 
     * @param index
     * @return
     */
    private T getParent(int index) {
        return heap[getParentIndex(index)];
    }
    
    /**
     * Get parent index
     * 
     * @param index
     * @return
     */
    private int getParentIndex(int index) {
        return index / 2;
    }
    
    /**
     * @param first
     * @param second
     */
    private void swap(int first, int second) {
        T temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }
}

public class Solution {
    public static void main(String[] args) {
        BinaryHeap<String> minheap = new BinaryHeap<String>();
        BinaryHeap<String> maxHeap = new BinaryHeap<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        }); 
        
        List<String> numbers = new ArrayList<String>();
        
        // control D to stop taking numbers
        Scanner scanner = new Scanner(System.in);
        String heapType = scanner.nextLine();
        while(scanner.hasNext()) {
            numbers.add(scanner.nextLine());
        }
        
        scanner.close();
        
        for (String x : numbers) {
            minheap.insert(x);
            maxHeap.insert(x);
        }
        
        if (heapType.equals("max-heap")) {
            while(!maxHeap.isEmpty()) {
                System.out.println(maxHeap.remove());
            }
        } else {
            while(!minheap.isEmpty()) {
                System.out.println(minheap.remove());
            }
        }
    }
}
