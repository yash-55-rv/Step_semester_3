import java.util.Arrays;
import java.util.Scanner;

public class ProductExceptSelfSolution {

    // Method to calculate the product of all elements except self in O(n) time
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // --- Pass 1: Forward Pass (Prefix Products) ---
        // answer[i] will store the product of all elements to the left of index i
        answer[0] = 1; // Nothing to the left of the first element
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // --- Pass 2: Backward Pass (Suffix Products) ---
        // Maintain a running product of everything to the right of index i
        int rightProduct = 1; // Nothing to the right of the last element
        for (int i = n - 1; i >= 0; i--) {
            // Multiply the prefix product already stored in answer[i] by the running suffix product
            answer[i] = answer[i] * rightProduct;
            // Update the running suffix product for the next element to the left
            rightProduct = rightProduct * nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== A1. Product of Array Except Self ===");

        // Take size of the array
        System.out.print("Enter the number of products in the bundle: ");
        int size = scanner.nextInt();

        // Take array elements
        int[] nums = new int[size];
        System.out.print("Enter " + size + " prices separated by spaces: ");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        // Process and display the output
        int[] result = productExceptSelf(nums);
        System.out.println("Output Product Array: " + Arrays.toString(result));

        scanner.close();
    }
}
