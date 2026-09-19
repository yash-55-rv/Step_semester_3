import java.util.HashMap;
import java.util.Scanner;

public class SubarraySumSolution {

    // Method to calculate total subarrays that sum to k using a Prefix Sum Map
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        
        // HashMap to store: Key = Prefix Sum, Value = Frequency of this prefix sum
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        
        // Base case: An empty prefix before the array starts has a sum of 0, seen exactly once.
        // This is critical for matching subarrays that start exactly at index 0.
        prefixSumMap.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];

            // If (currentSum - k) exists as an earlier prefix sum, it means the elements 
            // between that earlier point and now add up exactly to k.
            int complement = currentSum - k;
            if (prefixSumMap.containsKey(complement)) {
                count += prefixSumMap.get(complement);
            }

            // Record the current running prefix sum into the frequency map
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== A4. Subarray Sum Equals K ===");

        // Take size of the array
        System.out.print("Enter the number of daily log entries: ");
        int size = scanner.nextInt();

        // Take array elements
        int[] nums = new int[size];
        System.out.print("Enter " + size + " log integers separated by spaces: ");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        // Take target net change k
        System.out.print("Enter the target net change value (k): ");
        int k = scanner.nextInt();

        // Process and display the total counts
        int result = subarraySum(nums, k);
        System.out.println("Total Contiguous Subarrays Found: " + result);

        scanner.close();
    }
}
