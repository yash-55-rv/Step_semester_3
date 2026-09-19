import java.util.Arrays;
import java.util.Scanner;

public class RotateArraySolution {

    // Method to rotate an array to the right by k positions using modulo wraparound
    public static int[] rotateArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int n = nums.length;
        
        // Reduce k to handle values larger than or equal to the array length
        k = k % n; 
        
        // Create a new array of the same size to hold intermediate results
        int[] newArray = new int[n];

        // For every index i, work out its new position after rotation and place nums[i] there
        for (int i = 0; i < n; i++) {
            newArray[(i + k) % n] = nums[i];
        }

        // Copy the values from the new array back into nums
        for (int i = 0; i < n; i++) {
            nums[i] = newArray[i];
        }

        return nums; // Return the rotated array
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== L5. Rotate Array ===");
        
        // Take size of the array
        System.out.print("Enter the size of the array (number of songs): ");
        int size = scanner.nextInt();
        
        // Take array elements
        int[] nums = new int[size];
        if (size > 0) {
            System.out.print("Enter " + size + " integers separated by spaces: ");
            for (int i = 0; i < size; i++) {
                nums[i] = scanner.nextInt();
            }
        }
        
        // Take positions to rotate (k)
        System.out.print("Enter the number of positions to rotate to the right (k): ");
        int k = scanner.nextInt();
        
        // Process and display the output
        int[] result = rotateArray(nums, k);
        System.out.println("Rotated Array: " + Arrays.toString(result));
        
        scanner.close();
    }
}
