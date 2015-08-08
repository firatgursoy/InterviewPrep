package runningMedian;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class RunningMedian {

    private Queue<Integer> lowerHalf = new PriorityQueue<>(10, Collections.reverseOrder());
    private Queue<Integer> upperHalf = new PriorityQueue<>(10);
    
    public  RunningMedian() {
        lowerHalf.add(Integer.MIN_VALUE);
        
        upperHalf.add(Integer.MAX_VALUE);
    }
    
    public double getMedian(int input) {
        
        if (input >= upperHalf.peek()) {
            upperHalf.add(input);
        } else {
            lowerHalf.add(input);
        }
        
        if (lowerHalf.size() - upperHalf.size() == 2) {
            upperHalf.add(lowerHalf.poll());
        } else if (upperHalf.size() - lowerHalf.size() == 2) {
            lowerHalf.add(upperHalf.poll());
        }
        
        if (lowerHalf.size() == upperHalf.size()) {
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        } else if (lowerHalf.size() > upperHalf.size()) {
            return lowerHalf.peek();
        } else {
            return upperHalf.peek();
        }
    }
    
    public static void main(String[] args) {
        RunningMedian median = new RunningMedian();
        
        System.out.println(median.getMedian(1));
        System.out.println(median.getMedian(2));
        System.out.println(median.getMedian(3));
    }
}
