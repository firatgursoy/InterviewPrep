package max;

public class MaxSolution {

    static int flip(int bit) {
        return 1^bit;
    }
    
    /**
     * if a is positive, then return 1
     * if a is negative, then return 0
     * 
     * @param a
     * @return
     */
    static int sign(int a) {
        return flip( (a >> 31) & 0x1);
    }
    
    /**
     * When A and B have different signs
     * 
     * if a > 0 && b < 0, then k = 1
     * if a < 0 && b > 0, then k = 0
     * therefore k = sign(a)
     * 
     * When A and B have the same sign
     * k = sign(a - b)
     * 
     * @param a
     * @param b
     * @return
     */
    static int max(int a, int b) {
        int c = a - b;
        
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        
        int use_sa = sa ^ sb;
        int use_sc = flip(sa ^ sb);
        
        int k = use_sa * sa + use_sc * sc;
        int q = flip(k);
        
        return a * k + b * q;
    }
    
    public static void main(String[] args) {
        
    }
}
