package array_complete_cycle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.util.Pair;

public class CompleteCycleSolution {

    public static boolean isCompleteCycle(int[] input) {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        
        if (input == null)
            return false;
        
        if (input.length == 1) 
            return true;
        
        
        Pair<Integer, Integer> nextPair = new Pair<>(0, input[0]);
        set.add(nextPair);
        
        while(set.size() != input.length) {
            nextPair = getNextPair(input, nextPair);

            if (set.contains(nextPair))
                break;
            else
                set.add(nextPair);
        }
        
        return set.size() == input.length;
    }
    
    private static Pair<Integer, Integer> getNextPair(int[] input, Pair<Integer, Integer> pair) {
        int index = pair.getKey();
        int relativeIndex = pair.getValue();
        
        int newIndex = (index + relativeIndex) % input.length;
        return new Pair<Integer, Integer>(newIndex, input[newIndex]);
    }

    public static void main(String[] args) {
        int[] input = {2, 2, 2};
        int[] input2 = {2, 2, 2, 2};
        int[] input3 = {1, 2, 1};
        
        System.out.println(Arrays.toString(input2) + ": " +isCompleteCycle(input2));
        
        System.out.println(Arrays.toString(input) + ": " +isCompleteCycle(input));
        
        System.out.println(Arrays.toString(input3) + ": " + isCompleteCycle(input3));
    }
}
