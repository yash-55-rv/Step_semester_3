import java.util.Scanner;

public class CustomerIdentityVerification {

    public static String reverseCustomerName(String customerName) {
        char[] originalChars = customerName.toCharArray();
        StringBuilder reversed = new StringBuilder();
        
        // String traversal from end to beginning
        for (int i = originalChars.length - 1; i >= 0; i--) {
            reversed.append(originalChars[i]);
        }
        
        return reversed.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter customer name to verify: ");
        String testName = scanner.nextLine();
        
        String reversedName = reverseCustomerName(testName);
        
        System.out.println("\n--- Verification Report ---");
        System.out.println("Original Name: " + testName);
        System.out.println("Reversed Name: " + reversedName);
        
        scanner.close();
    }
}
