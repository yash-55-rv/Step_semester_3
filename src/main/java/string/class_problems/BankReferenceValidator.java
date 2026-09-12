import java.util.Scanner;

public class BankReferenceValidator {

    public static String normalizeReference(String raw) {
        String trimmed = raw.trim();
        
        if (trimmed.length() < 3) {
            return trimmed; // Handle short lengths safely before substring operations
        }
        
        // Uppercase only the first 3 characters and join with the rest
        String bankCode = trimmed.substring(0, 3).toUpperCase();
        String body = trimmed.substring(3);
        
        return bankCode + body;
    }

    public static String validateAndFormat(String reference) {
        // 1. Length validation
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }

        // 2. Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

        // 3. Validate remaining 11 characters are numeric digits
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        // 4. If valid, format presentation details via StringBuilder
        String bankCode = reference.substring(0, 3);
        String day = reference.substring(3, 5);
        String month = reference.substring(5, 7);
        String year = reference.substring(7, 9);
        String sequence = reference.substring(9, 14);

        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(bankCode).append("] ");
        formatted.append("DATE: ").append(day).append("/").append(month).append("/").append(year);
        formatted.append(" | SEQ: ").append(sequence);

        return formatted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter raw transaction reference string: ");
        String rawInput = scanner.nextLine();
        
        // Step 1: Normalize
        String normalized = normalizeReference(rawInput);
        
        // Step 2: Validate and Output formatting results
        String output = validateAndFormat(normalized);
        System.out.println(output);
        
        scanner.close();
    }
}
