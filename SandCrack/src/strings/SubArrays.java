package strings;

public class SubArrays {

    public static boolean isEqualArrays(String[] one, String[] two) {
        if (one == null || one.length == 0 ||
            two == null || two.length == 0) {
            return false;
        }
        
        int i = 0;
        int j = 0;
        
        String subOne = "";
        String subTwo = "";
        
        while (i <= one.length && j <= two.length) {
            
            if (subOne.length() >= subTwo.length()) {
                if (subOne.contains(subTwo)) {
                    if (j < two.length) {
                        subTwo += two[j];
                    }
                    
                    j++;
                } else
                    return false;
            }
            
            if (subTwo.length() >= subOne.length()) {
                if (subTwo.contains(subOne)) {
                    if (i < one.length) {
                        subOne += one[i];
                    }
                    
                    i++;
                } else {
                    return false;
                }
            }
            
        }
        
        if (i >= one.length && j >= two.length)
            return true;
        
        return false;
    }
    
    public static void main(String[] args) {
        String[] one = new String[] {"this", "is a"};
        String[] two = new String[] {"th", "is", "is", " a"};
        
        System.out.println(isEqualArrays(one, two));
    }
}
