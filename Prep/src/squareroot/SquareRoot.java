package squareroot;

public class SquareRoot {

    private static final double EPISLON = 0.000000001;

    public double squareRoot(float n) {
        if (n < 0) return Double.NaN;
        
        float low = 0;
        float high = n;
        float mid = (low + high) / 2;
        
        while(Math.abs(mid * mid - n) > EPISLON) {
            if (mid * mid < n) {
                low = mid;
            } else if (mid * mid > n) {
                high = mid;
            }
            
            mid = (low + high) / 2;
        }
        
        return mid;
    }
    
    // return the square root of c, computed using Newton's method
    public double sqrtNewtons(float c) {
        if (c < 0) return Double.NaN;
        
        double t = c;
        while (Math.abs(t - c/t) > EPISLON * t)
            t = (t + c/t) / 2.0;
        
        return t;
    }
    
    public static void main(String[] args) {
        SquareRoot root = new SquareRoot();
        
        System.out.println(root.squareRoot(9f));
        
        System.out.println(root.squareRoot(36f));
        
        System.out.println(root.sqrtNewtons(25f));
    }
}
