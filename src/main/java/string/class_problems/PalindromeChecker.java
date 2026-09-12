import java.util.Scanner;

public class PalindromeChecker {

    // 1. Iterative Comparison Approach
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 2. Recursion Approach
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // 3. Array Reversal Approach
    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        return new String(original).equals(new String(reversed));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Take dynamic string input from the user
        System.out.print("Enter a word or short phrase to check: ");
        String input = scanner.nextLine();
        
        // Process evaluations
        String resIterative = isPalindromeIterative(input) ? "Palindrome" : "Not Palindrome";
        String resRecursive = isPalindromeRecursive(input) ? "Palindrome" : "Not Palindrome";
        String resReversal = isPalindromeArrayReversal(input) ? "Palindrome" : "Not Palindrome";

        // Display outcomes
        System.out.println("\nResults for: \"" + input + "\"");
        System.out.println("Iterative: " + resIterative + " | Recursive: " + resRecursive + " | Array Reversal: " + resReversal);
        
        scanner.close();
    }
}
