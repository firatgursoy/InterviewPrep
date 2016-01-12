package staircase;


public class Solution {
    
    public static void main(String[] args){
        StairCase(6);
    }

    private static void StairCase(int n) {

        if (n >= 1 && n <= 100) {
            int level = 1;
            for(int i = n; i > 0; i--) {
                for (int space = 0; space < i - 1; space++) {
                    System.out.print(' ');
                }
                for (int pound = 0; pound < level; pound++) {
                    System.out.print('#');
                }
                System.out.print('\n');
                level++;
            }
        }
    } 
    
}
