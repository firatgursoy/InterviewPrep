package towersOfHanoi;

import java.util.Stack;

class Tower {
    private Stack<Integer> disks;
    private String index;
    
    public Tower(String index) {
        disks = new Stack<Integer>();
        this.index = index;
    }
    
    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("error placing disk: " +d);
        } else {
            disks.push(d);
        }
    }
    
    public void moveTopTo(Tower t) {
        int top = disks.pop();
        t.add(top);
        
        System.out.println("Move disk " + top + " from " + index + " to " + t.getIndex());
    }
    
    public void moveDisks(int n, Tower destination, Tower buffer) {
        if (n > 0) {
            moveDisks(n - 1, buffer, destination);
            moveTopTo(destination);
            buffer.moveDisks(n - 1, destination, this);
        }
    }
    
    public String getIndex() {
        return index;
    }
    
    public String toString() {
        return this.index;
    }
}

public class Solution {

    public static void main(String[] args) {
        int n = 4;
        Tower[] towers = new Tower[3];
        
        towers[0] = new Tower("A");
        towers[1] = new Tower("B");
        towers[2] = new Tower("C");
        
        for(int i = n - 1; i >= 0; i--) {
            towers[0].add(i);
        }
        
        towers[0].moveDisks(n, towers[2], towers[1]);
    }
}
