import java.util.Scanner;

public class ContainsDuplicateSolution {

    // Method to check if any roll number/value appears twice using nested loops
    public static boolean containsDuplicate(int[] nums) {
        // Compare every element at position i against every element at a later position j
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // Early exit as soon as a duplicate match is found
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        // Return false if no identical pairs exist after a comprehensive check
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== L3. Contains Duplicate ===");
        
        // Take size of the array
        System.out.print("Enter the number of entries in the list: ");
        int size = scanner.nextInt();
        
        // Take array elements (e.g., exam roll numbers)
        int[] nums = new int[size];
        System.out.print("Enter " + size + " entries separated by spaces: ");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }
        
        // Process and display the validation result
        boolean hasDuplicate = containsDuplicate(nums);
        System.out.println("Contains Duplicate: " + hasDuplicate);
        
        scanner.close();
    }
}
