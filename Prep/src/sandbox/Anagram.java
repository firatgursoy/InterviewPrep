package sandbox;

import java.util.HashMap;
import java.util.Map;

public class Anagram {

    public boolean isAnagram(String input, String candidate) {
        // add preconditions
        if (candidate == null || candidate.isEmpty() || input.length() != candidate.length())
            return false;
        
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : input.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c,  1);
            }
            else {
                Integer integer = map.get(c);
                map.put(c, ++integer);
            }
        }
        
        for (char c : candidate.toCharArray()) {
            if (map.containsKey(c)) {
                Integer integer = map.get(c);
                map.put(c, --integer);
            } else {
                return false;
            }
        }
        
        return !map.entrySet().stream().filter(x -> x.getValue() != 0).findAny().isPresent();
    }
    
    public static void main(String[] args) {
        Anagram gram = new Anagram();
        
        System.out.println(gram.isAnagram("abba", "baba"));
    }
}
