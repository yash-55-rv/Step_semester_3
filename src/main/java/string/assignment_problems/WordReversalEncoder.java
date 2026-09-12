import java.util.Scanner;

public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return "";
        }

        // Split sentence into words using a single space
        String[] words = sentence.split(" ");
        StringBuilder finalResult = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            // Build the reverse of each word using a loop
            StringBuilder reversedWord = new StringBuilder();
            for (int j = words[i].length() - 1; j >= 0; j--) {
                reversedWord.append(words[i].charAt(j));
            }
            
            finalResult.append(reversedWord);
            
            // Add a space back if it's not the last word
            if (i < words.length - 1) {
                finalResult.append(" ");
            }
        }

        return finalResult.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a sentence to encode: ");
        String inputSentence = scanner.nextLine();
        
        String output = reverseEachWord(inputSentence);
        System.out.println(output);
        
        scanner.close();
    }
}
