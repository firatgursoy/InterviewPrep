package strings;

public class Mancher {

    String longestPalindrome(String input) {
        input = '#' + String.join("#", input.split("")) + '#';
        int centerIndex = 0;
        int longest = 1;
        int i;
        
        for (i = 1; i < input.length(); i++) {
            int l = 1;
            while (i - l >= 0 && i + l < input.length() && input.charAt(i - l) == input.charAt(i + l)) {
                l++;
            }
            if (l > longest) {
                centerIndex = i;
                longest = l;
            }
        }
        
        return input.substring(centerIndex - longest + 1, centerIndex + longest).replace("#", "");
    }
    
    public static void main(String[] args) {
        Mancher m = new Mancher();
        System.out.println(m.longestPalindrome("xx1234567887654321yy"));
    }
}
