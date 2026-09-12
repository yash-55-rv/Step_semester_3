import java.util.Scanner;

public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {
        int vowels = 0;
        int consonants = 0;
        
        // Convert to lowercase for case-insensitive comparison
        String lowerText = text.toLowerCase();

        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);

            // Skip spaces
            if (ch == ' ') {
                continue;
            }

            // Check if it's a vowel or consonant
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowels++;
            } else if (ch >= 'a' && ch <= 'z') {
                consonants++;
            }
        }

        System.out.println("Vowels: " + vowels + " | Consonants: " + consonants);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter book title: ");
        String input = scanner.nextLine();
        
        countVowelsAndConsonants(input);
        scanner.close();
    }
}
