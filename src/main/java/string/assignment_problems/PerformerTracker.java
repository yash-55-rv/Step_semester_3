import java.util.Scanner;

public class PerformerTracker {

    // Method satisfying the exact problem requirements
    static String findMinMaxSpread(int[] scores) {
        if (scores == null || scores.length == 0) {
            return "No scores provided";
        }

        // Initialize min and max with the first element of the array
        int min = scores[0];
        int max = scores[0];

        // Single pass through the array to find both min and max
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
            if (scores[i] > max) {
                max = scores[i];
            }
        }

        // Compute the spread (max - min)
        int spread = max - min;

        // Format the output to match the requirement exactly
        return "Min: " + min + " | Max: " + max + " | Spread: " + spread;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of scores (at least 2): ");
        int n = scanner.nextInt();

        if (n < 2) {
            System.out.println("Invalid size. Array must have at least 2 elements.");
            scanner.close();
            return;
        }

        int[] scores = new int[n];
        System.out.println("Enter the scores:");
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }

        // Execute function and print output matching the example
        String result = findMinMaxSpread(scores);
        System.out.println("\n" + result);

        scanner.close();
    }
}
