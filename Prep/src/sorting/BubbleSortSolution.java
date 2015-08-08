package sorting;

public class BubbleSortSolution {

    static void swap(int i, int j, int[] input) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    
    static void bubbleSort(int[] input) {
        for(int i = 0; i < input.length; i++) {
            for (int j = 1; j < input.length; j++) {
                if (input[j] < input[j - 1]) {
                    swap(j, j - 1, input);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] input = {3, 4, 2, 1};
        
        bubbleSort(input);
        
        for (int i : input) {
            System.out.println(i);
        }
    }
}
