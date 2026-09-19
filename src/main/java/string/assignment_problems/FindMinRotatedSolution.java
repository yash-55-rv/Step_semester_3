import java.util.Scanner;

public class FindMinRotatedSolution {

    // Method to find the minimum element in O(log n) time using Binary Search
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // Perform modified binary search
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If the middle element is greater than the rightmost element,
            // the inflection point (and the minimum element) must be in the right half.
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // If the middle element is less than or equal to the rightmost element,
            // the minimum element is either at mid or to its left.
            else {
                right = mid;
            }
        }

        // When left == right, it points directly to the minimum element
        return nums[left];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== A5. Find Minimum in Rotated Sorted Array ===");

        // Take size of the array
        System.out.print("Enter the number of elements in the roster: ");
        int size = scanner.nextInt();

        // Take array elements
        int[] nums = new int[size];
        System.out.print("Enter " + size + " unique, rotated sorted integers separated by spaces: ");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        // Process and display the minimum element
        int result = findMin(nums);
        System.out.println("Earliest/Minimum Element: " + result);

        scanner.close();
    }
}
