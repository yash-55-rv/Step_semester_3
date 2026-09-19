import java.util.Scanner;

public class MaxSubArraySolution {

    // Method to find the maximum subarray sum using Kadane's algorithm
    public static int maxSubArray(int[] nums) {
        // Initialize current running sum and global maximum sum with the first element
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Traverse the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // "Extend vs. Restart" decision: 
            // Is it better to add the current element to the existing run, or start a new run fresh?
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // Keep a running record of the largest sum seen so far
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== A2. Maximum Subarray (Kadane's Algorithm) ===");

        // Take size of the array
        System.out.print("Enter the number of trading days: ");
        int size = scanner.nextInt();

        // Take array elements
        int[] nums = new int[size];
        System.out.print("Enter " + size + " profit/loss figures separated by spaces: ");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        // Process and display the output maximum subarray sum
        int result = maxSubArray(nums);
        System.out.println("Maximum Contiguous Subarray Sum: " + result);

        scanner.close();
    }
}
