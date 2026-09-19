import java.util.Arrays;
import java.util.Scanner;

public class TwoSumSolution {

    // Method to find indices of the two numbers that add up to the target
    public static int[] twoSum(int[] nums, int target) {
        // Using two nested loops to check every pair of different positions
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // If the sum equals the target, return the indices immediately
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        // Fallback return if no pair matches (though the task assumes exactly one valid pair exists)
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== L1. Two Sum ===");
        
        // Take size of the array
        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();
        
        // Take array elements
        int[] nums = new int[size];
        System.out.print("Enter " + size + " integers separated by spaces: ");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }
        
        // Take target sum
        System.out.print("Enter the target budget: ");
        int target = scanner.nextInt();
        
        // Process and display the output
        int[] result = twoSum(nums, target);
        System.out.println("Output Indices: " + Arrays.toString(result));
        
        scanner.close();
    }
}
