import java.util.Scanner;

public class LibraryIsbnValidator {

    public static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        
        if (trimmed.length() < 3) {
            return trimmed;
        }
        
        // Uppercase only the first 3 characters and keep the rest intact
        String publisherCode = trimmed.substring(0, 3).toUpperCase();
        String remainingBody = trimmed.substring(3);
        
        return publisherCode + remainingBody;
    }

    public static String validateAndFormat(String code) {
        // 1. Length validation
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        // 2. Validate first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // 3. Validate remaining 10 characters are numeric digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: non-digit body";
            }
        }

        // 4. Construct formatted presentation layout
        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder formatted = new StringBuilder();
        formatted.append("[").append(pubCode).append("] ");
        formatted.append("YEAR: ").append(year);
        formatted.append(" | CATALOG: ").append(catalog);

        return formatted.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter raw code string: ");
        String rawInput = scanner.nextLine();
        
        // Multi-stage verification pipeline
        String normalized = normalizeCode(rawInput);
        String evaluationResult = validateAndFormat(normalized);
        
        System.out.println(evaluationResult);
        
        scanner.close();
    }
}
