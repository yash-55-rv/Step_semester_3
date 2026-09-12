import java.util.Scanner;

public class UniqueLetterHunt {

    public static char findFirstNonRepeatingChar(String text) {
        // Assuming extended ASCII space (256 characters) to cover signs/letters
        int[] frequencyMap = new int[256];

        // Step 1: Compute frequencies
        for (int i = 0; i < text.length(); i++) {
            frequencyMap[text.charAt(i)]++;
        }

        // Step 2: Early-exit scanning from left to right
        for (int i = 0; i < text.length(); i++) {
            if (frequencyMap[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        // Return a null char sentinel if no non-repeating character exists
        return '\0'; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a word or sentence for the Unique Letter Hunt: ");
        String input = scanner.nextLine();
        
        char result = findFirstNonRepeatingChar(input);
        
        System.out.println("\n--- Result ---");
        if (result != '\0') {
            System.out.println("First Non-Repeating Character: '" + result + "'");
        } else {
            System.out.println("No Non-Repeating Character Found");
        }
        
        scanner.close();
    }
}
