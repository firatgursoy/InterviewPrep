package suffixTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    char c;
    List<Integer> indexes = new ArrayList<>(10);
    Map<Character, Node> children = new HashMap<>();
    
    public void insertString(String s, int index) {
        indexes.add(index);
        
        if (s != null && !s.isEmpty()) {
            Node child = null;
            c = s.charAt(0);
            
            if (children.containsKey(c)) {
                child = children.get(c);
            } else {
                child = new Node();
                children.put(c, child);
                
            }
            String remainder = s.substring(1);
            child.insertString(remainder, index);
        }
    }
    
    public List<Integer> search(String s) {
        if (s == null || s.isEmpty()) {
            return indexes;
        } else {
            char first = s.charAt(0);
            if (children.containsKey(first)) {
                String remainder = s.substring(1);
                return children.get(first).search(remainder);
            }
        }
        
        return null;
    }
}

public class SuffixTree {
    Node root = new Node();

    public SuffixTree(String s) {
        if (s != null && !s.isEmpty()) {
            for (int i = 0; i < s.length(); i++) {
                String sufffix = s.substring(i);
                
                root.insertString(sufffix, i);
            }
        }
    }
    
    public List<Integer> search(String s) {
        return root.search(s);
    }
    
    public static void main(String[] args) {
        String test = "bibs";
        SuffixTree tree = new SuffixTree(test);
        
        System.out.println(tree.search("bs"));
    }
}
