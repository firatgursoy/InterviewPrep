package sorting;

import java.util.Arrays;

public class MergeSortArrays {

    public int[] mergeSort(int[] input) {
        if (input == null || input.length <= 1) {
            return input;
        }
        
        int[] left = leftHalf(input);
        int[] right = rightHalf(input);
        
        left = mergeSort(left);
        right = mergeSort(right);
        
        return mergeTogether(left, right);
    }
    
    // Returns the first half of the given array.
    private int[] leftHalf(int[] array) {
        int size1 = array.length / 2;
        int[] left = new int[size1];
        for (int i = 0; i < size1; i++) {
            left[i] = array[i];
        }
        return left;
    }
    
    // Returns the second half of the given array.
    private int[] rightHalf(int[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        
        int[] right = new int[size2];
        for (int i = 0; i < size2; i++) {
            right[i] = array[i + size1];
        }
        
        return right;
    }
    
    
    // Merges the given left and right arrays into the given 
    // result array.  Second, working version.
    // pre : result is empty; left/right are sorted
    // post: result contains result of merging sorted lists;
    private int[] mergeTogether(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        
        int leftIndex = 0;   // index into left array
        int rightIndex = 0;   // index into right array
        
        for (int i = 0; i < result.length; i++) {
            if (rightIndex >= right.length || 
                (leftIndex < left.length && left[leftIndex] <= right[rightIndex])) {
                result[i] = left[leftIndex];    // take from left
                leftIndex++;
            } else {
                result[i] = right[rightIndex];   // take from right
                rightIndex++;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        MergeSortArrays sort = new MergeSortArrays();
        
        int[] input = {5, 4, 3, 2, 1};
        
        input = sort.mergeSort(input);
        
        System.out.println(Arrays.toString(input));
    }
}
