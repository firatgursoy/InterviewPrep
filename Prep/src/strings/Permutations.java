package strings;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public List<String> getPerms(String input) {
        List<String> results = new ArrayList<>();
        
        if (input == null) {
            return results;
        }
        else if (input.isEmpty()) {
            results.add("");
            return results;
        }
        
        char first = input.charAt(0);
        String remainder = input.substring(1);
        List<String> words = getPerms(remainder);
        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                results.add(insertChartAt(word, first, i));
            }
        }
        
        return results;
    }

    private String insertChartAt(String word, char c, int i) {
        String first = word.substring(0, i);
        String remainder = word.substring(i);
        return first + c + remainder;
    }
    
    public static void main(String[] args) {
        Permutations perm = new Permutations();
        
        List<String> results = perm.getPerms("abc");
        
        System.out.println(results);
    }
}
