package sandbox;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Anagram {

    public boolean isAnagram(String input, String candidate) {
        // add preconditions
        if (candidate == null || candidate.isEmpty() || input.length() != candidate.length())
            return false;
        
        /*
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : input.toLowerCase().toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c,  1);
            }
            else {
                Integer integer = map.get(c);
                map.put(c, ++integer);
            }
        }
        */
        
        //Functional Java 8 way
        Map<Character, Long> map = input.chars().mapToObj(x -> (char)x)
                                                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        for (char c : candidate.toLowerCase().toCharArray()) {
            if (map.containsKey(c)) {
                Long integer = map.get(c);
                map.put(c, --integer);
            } else {
                return false;
            }
        }
        
        return !map.entrySet().stream().anyMatch(x -> x.getValue() > 0);
    }
    
    public static void main(String[] args) {
        Anagram gram = new Anagram();
        
        System.out.println(gram.isAnagram("abbbba", "bababb"));
    }
}
