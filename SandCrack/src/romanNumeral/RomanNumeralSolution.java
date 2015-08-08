package romanNumeral;

public class RomanNumeralSolution {

    static int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] letters = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    
    static int convertToArabic(String input) {
        int result = 0;

        if (!input.isEmpty()) {
            int i = 0;

            while (i < input.length()) {
                char c = input.charAt(i);
                int number = letterToNumber(c);
                
                i++;
                
                if (i == input.length()) {
                    result += number;
                } else {
                    int nextNumber = letterToNumber(input.charAt(i));
                    
                    if (nextNumber > number) {
                        result = result + (nextNumber - number);
                        i++;
                    } else {
                        result += number;
                    }
                }
            }
        }

        return result;
    }
    
    static String convertToRomanNumberals(int number) {
        String result = "";
        
        int N = number;
        
        for(int i = 0; i < numbers.length; i++) {
            while(N >= numbers[i]) {
                result += letters[i];
                N -= numbers[i];
            }
        }
        
        return result;
    }

    private static int letterToNumber(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(convertToArabic("XI"));
        
        System.out.println(convertToRomanNumberals(3999));
    }
}
