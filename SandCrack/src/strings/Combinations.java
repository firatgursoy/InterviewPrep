package strings;

import java.util.ArrayList;
import java.util.List;



public class Combinations {

    public static void combinations(String s) {
        comb1("", s);
    }
 
    // print all subsets of the remaining elements, with given prefix
    private static void comb1(String prefix, String s) {
        if (s.length() > 0) {
            
            System.out.println(prefix + s.charAt(0));
            
            comb1(prefix + s.charAt(0), s.substring(1));
            
            comb1(prefix, s.substring(1));
        }
    }
    
    private static List<String> combo1(String input) {
        List<String> results = new ArrayList<>();
        combo(results, "", input);
        return results;
    }
    
    private static List<String> combo(List<String> results, String prefix, String s) {
        if (!s.isEmpty()) {
            results.add(prefix + s.charAt(0));
            
            combo(results, prefix + s.charAt(0), s.substring(1));
            combo(results, prefix, s.substring(1));
        }
        
        return results;
    }

    public static void main(String args[]) {
        combinations("wxyz");
        
        System.out.println(combo1("wxyz"));
    }

}
