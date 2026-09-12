import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class WordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            System.out.println("No feedback content found.");
            return;
        }

        // Define fixed list of stop words
        String[] stopWordsArray = {"the", "was", "and", "a", "is", "of", "in"};
        
        // Normalize text: convert to lowercase and strip periods/commas using replace()
        String cleanedText = feedback.toLowerCase().replace(".", "").replace(",", "");
        
        // Split by whitespace
        String[] words = cleanedText.split("\\s+");
        
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            // Skip word if found in the stop-word list
            boolean isStopWord = false;
            for (String stopWord : stopWordsArray) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        // Sort entries by count in descending order
        List<Map.Entry<String, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Print sorted unique words
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a paragraph of feedback text:\n");
        String paragraphInput = scanner.nextLine();
        
        System.out.println("\n--- Frequency Report ---");
        printFilteredWordFrequency(paragraphInput);
        
        scanner.close();
    }
}
