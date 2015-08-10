package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        
        System.out.println(mergeSort.mergeSort(Arrays.asList(5l, 4l, 3l, 2l, 1l)));
    }
    
    /**
     * Merge sort algo
     * 
     * @param input
     * @return
     */
    public List<Long> mergeSort(List<Long> input) {

        if (input.size() <= 1)
            return input;

        int middle = input.size() / 2;
        List<Long> left = input.subList(0, middle);
        List<Long> right = input.subList(middle, input.size());

        left = mergeSort(left);
        right = mergeSort(right);
        
        List<Long> result = mergeTogether(left, right);
        
        return result;
    }
    
    /**
     * Put the lists together in sorted order
     * 
     * @param left
     * @param right
     * @return
     */
    private List<Long> mergeTogether(List<Long> left, List<Long> right) {
        if (right.isEmpty())
            return left;
        if (left.isEmpty())
            return right;
        
        List<Long> result = new ArrayList<Long>(left.size() + right.size());
        
        int leftIndex = 0;
        int rightIndex = 0;
        
        for(int i = 0; i < left.size() + right.size(); i++) {
            // take from left
            if(rightIndex >= right.size() || 
                (leftIndex < left.size() && left.get(leftIndex) <= right.get(rightIndex))) {
                result.add(i, left.get(leftIndex));
                leftIndex++;
            } else {
                // take from right
                result.add(i, right.get(rightIndex));
                rightIndex++;
            }
        }
        
        return result;
    }
    
}
