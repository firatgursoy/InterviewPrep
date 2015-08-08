package sandbox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import com.google.common.primitives.Ints;

public class MergeSort {

    private int inversions = 0;
    
    public int getInversions() {
        return inversions;
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        
        List<Long> list = new ArrayList<>(100000);
        
        BufferedReader br;
        String curline;
        try {
            br = new BufferedReader(new FileReader("test.txt"));
            while ((curline = br.readLine()) != null) {
                list.add(Long.parseLong(curline));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        int[] ints = Ints.toArray(list);
        
        long count = mergeSort.invCount(ints);
        
        System.out.println("inversion count: " + count);
    }
    
    long invCount(int[] arr) {
        if (arr.length < 2)
            return 0;

        int m = (arr.length + 1) / 2;
        int left[] = Arrays.copyOfRange(arr, 0, m);
        int right[] = Arrays.copyOfRange(arr, m, arr.length);

        return invCount(left) + invCount(right) + merge(arr, left, right);
    }
    
    public List<Long> mergeSort(List<Long> input) {

        if (input.size() <= 1)
            return input;

        int middle = input.size() / 2;
        List<Long> left = input.subList(0, middle);
        List<Long> right = input.subList(middle, input.size());

        left = mergeSort(left);
        right = mergeSort(right);
        
        List<Long> result = mergeSort(left, right);
        
        return result;
    }
    
    private List<Long> mergeSort(List<Long> left, List<Long> right) {
        if (right.isEmpty())
            return left;
        if (left.isEmpty())
            return right;
        
        List<Long> result = new ArrayList<Long>(left.size() + right.size());
        
        ListIterator<Long> first = left.listIterator();
        ListIterator<Long> second = right.listIterator();
        
        long firstNum = first.next();
        long secondNum = second.next();
        
        while (true) {
            if (firstNum < secondNum) {
                
                result.add(firstNum);
                
                if (first.hasNext()) {
                    firstNum = first.next();
                }
                else {
                    result.add(secondNum);
                    //add rest of right lift
                    while (second.hasNext())
                        result.add(second.next());
                    
                    break;
                }
            }
            else {
                result.add(secondNum);
                inversions =  inversions + (left.size() - (first.nextIndex() - 1));
                
                if (second.hasNext()) {
                    secondNum = second.next();
                } else  {
                    result.add(firstNum);
                    while (first.hasNext())
                        result.add(first.next());
                        
                    break;
                }
            }
        }
        
        return result;
    }

    long merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i+j] = right[j];
                j++;
            } else if (j == right.length) {
                arr[i+j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                arr[i+j] = left[i];
                i++;                
            } else {
                arr[i+j] = right[j];
                count += left.length-i;
                j++;
            }
        }
        return count;
    }
}
