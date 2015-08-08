package sandbox;

import java.util.ArrayList;
import java.util.List;

public class QuickSortSolution {

    public static List<Integer> quickSort(List<Integer> input) {
        //pick pivot
        //find ones equal
        //find ones greater
        //find ones less
        
        if (input.size() <= 1) {
            return input;
        }
        
        List<Integer> less = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        
        int pivot = input.get(0);
        
        for(int i: input) {
            if (i == pivot) {
                equal.add(i);
            } else if (i < pivot) {
                less.add(i);
            } else {
                greater.add(i);
            }
        }
        
        less = quickSort(less);
        greater = quickSort(greater);
        
        List<Integer> merge = mergeLists(less, equal);
        return mergeLists(merge, greater);
    }
    
    private static List<Integer> mergeLists(List<Integer> less, List<Integer> equal) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) {
        
    }
}
