package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
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
    
}
