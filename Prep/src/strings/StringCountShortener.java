package strings;

public class StringCountShortener {
    static String shorten(String input) {
        if (input == null || input.isEmpty() || input.length() == 1) {
            return input;
        }
        
        StringBuffer result = new StringBuffer();
        
        char first = input.charAt(0);
        
        int count = 1;
        for(int i = 1; i < input.length(); i++) {
            
            char c = input.charAt(i); 
            if (first == c) {
                count++;
            } else {
                if (count == 1) {
                    result.append(first);
                }
                else if (count > 1) {
                    result.append(count).append(first);
                } 
                first = c;
                count = 1;
            }
            
        }
        
        if (count == 1) {
            result.append(first);
        } else if (count > 1) {
            result.append(count).append(first);
        }
        
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(shorten("abc"));
        System.out.println(shorten("aaabc"));
    }
}
