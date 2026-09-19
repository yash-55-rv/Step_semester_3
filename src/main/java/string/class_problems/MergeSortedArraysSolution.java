import java.util.Arrays;
import java.util.Scanner;

public class MergeSortedArraysSolution {

    // Method to merge two already sorted arrays using the two-pointer technique
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        // Create a new empty result array with a size equal to the sum of both arrays
        int[] result = new int[arr1.length + arr2.length];
        
        int i = 0; // Index pointer for arr1
        int j = 0; // Index pointer for arr2
        int k = 0; // Index pointer for the result array

        // Repeatedly compare current elements until one array is fully copied over
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k] = arr1[i];
                i++;
            } else {
                result[k] = arr2[j];
                j++;
            }
            k++;
        }

        // Copy all of the remaining elements from arr1 (if any)
        while (i < arr1.length) {
            result[k] = arr1[i];
            i++;
            k++;
        }

        // Copy all of the remaining elements from arr2 (if any)
        while (j < arr2.length) {
            result[k] = arr2[j];
            j++;
            k++;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== L4. Merge Two Sorted Arrays ===");
        
        // Take size and elements of the first sorted array
        System.out.print("Enter the size of the first sorted array (arr1): ");
        int size1 = scanner.nextInt();
        int[] arr1 = new int[size1];
        if (size1 > 0) {
            System.out.print("Enter " + size1 + " sorted integers separated by spaces: ");
            for (int i = 0; i < size1; i++) {
                arr1[i] = scanner.nextInt();
            }
        }
        
        // Take size and elements of the second sorted array
        System.out.print("Enter the size of the second sorted array (arr2): ");
        int size2 = scanner.nextInt();
        int[] arr2 = new int[size2];
        if (size2 > 0) {
            System.out.print("Enter " + size2 + " sorted integers separated by spaces: ");
            for (int i = 0; i < size2; i++) {
                arr2[i] = scanner.nextInt();
            }
        }
        
        // Process the arrays and display the combined sorted result
        int[] combinedResult = mergeSortedArrays(arr1, arr2);
        System.out.println("Merged Array: " + Arrays.toString(combinedResult));
        
        scanner.close();
    }
}
