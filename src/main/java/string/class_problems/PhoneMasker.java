import java.util.Scanner;

public class PhoneMasker {

    public static String maskPhoneNumber(String phone) {
        // Check if length is exactly 10 characters
        if (phone.length() != 10) {
            return "Invalid phone number";
        }

        // Validate that all characters are numeric digits
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        // Build the masked variant using StringBuilder
        StringBuilder masked = new StringBuilder();
        masked.append("XXXXXX");
        masked.insert(6, "-");
        masked.append(phone.substring(6));

        return masked.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter 10-digit phone number: ");
        String phone = scanner.nextLine();
        
        String result = maskPhoneNumber(phone);
        System.out.println(result);
        
        scanner.close();
    }
}
