import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreeSumSolution {

    // Method to find all unique triplets that sum to 0
    public static int[][] threeSum(int[] nums) {
        // 1. Sort the array first to make duplicate skipping and pointer movement deterministic
        Arrays.sort(nums);
        List<int[]> tripletsList = new ArrayList<>();

        // 2. Iterate through the array, fixing the first element of the triplet
        for (int i = 0; i < nums.length - 2; i++) {
            // Optimization: If the fixed element is greater than 0, no later elements can sum to 0
            if (nums[i] > 0) break;

            // Skip duplicate values for the fixed position to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 3. Set up two pointers moving inward from both ends of the remaining subarray
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Match found! Save the combination
                    tripletsList.add(new int[]{nums[i], nums[left], nums[right]});

                    // Advance pointers and immediately skip duplicate values
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    // Sum is too small, move the left pointer rightward to pick up a larger value
                    left++;
                } else {
                    // Sum is too large, move the right pointer leftward to pick up a smaller value
                    right--;
                }
            }
        }

        // Convert the dynamic list of triplets into the required native 2D array output
        return tripletsList.toArray(new int[tripletsList.size()][]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== A3. 3Sum ===");

        // Take size of the array
        System.out.print("Enter the number of transaction amounts: ");
        int size = scanner.nextInt();

        // Take array elements
        int[] nums = new int[size];
        System.out.print("Enter " + size + " transaction integers separated by spaces: ");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        // Process and display the nested unique triplet combinations
        int[][] result = threeSum(nums);
        System.out.print("Output Unique Triplets: ");
        System.out.println(Arrays.deepToString(result));

        scanner.close();
    }
}
