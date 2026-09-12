import java.util.Scanner;

public class TypingAccuracyChecker {

    public static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char origChar = ' ', typedChar = ' ';

        // Use the smaller length to avoid StringIndexOutOfBoundsException if text lengths differ
        int compareLength = Math.min(original.length(), typed.length());

        for (int i = 0; i < compareLength; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; 
                origChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        double accuracy = ((double) matched / total) * 100;

        System.out.print("Matched: " + matched + "/" + total + " | Accuracy: " + String.format("%.2f", accuracy) + "% | ");
        if (firstMismatchPos == -1 && original.length() == typed.length()) {
            System.out.println("No Mismatches");
        } else if (firstMismatchPos != -1) {
            System.out.println("First Mismatch at position " + firstMismatchPos + " ('" + origChar + "' vs '" + typedChar + "')");
        } else {
            System.out.println("First Mismatch due to missing or extra characters at position " + (compareLength + 1));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter original passage: ");
        String original = scanner.nextLine();
        
        System.out.print("Enter typed passage: ");
        String typed = scanner.nextLine();
        
        checkTypingAccuracy(original, typed);
        scanner.close();
    }
}
