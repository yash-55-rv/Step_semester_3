import java.util.Scanner;
import java.util.Arrays;

public class PodiumFinder {

    // Method to find the top three scores in a single pass
    static int[] findTopThreeScores(int[] scores) {
        // Initialize the top three tracking variables to minimum integer values
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        // Single pass layout: left to right
        for (int score : scores) {
            // Case 1: Current score beats the absolute highest
            if (score > first) {
                third = second;   // Third gets old second
                second = first;   // Second gets old first
                first = score;    // First gets new highest
            } 
            // Case 2: Current score is between first and second
            else if (score > second) {
                third = second;   // Third gets old second
                second = score;   // Second gets new value
            } 
            // Case 3: Current score is between second and third
            else if (score > third) {
                third = score;    // Third gets new value
            }
        }

        return new int[]{first, second, third};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of scores (at least 3): ");
        int n = scanner.nextInt();

        // Enforce the problem constraints
        if (n < 3) {
            System.out.println("Invalid size. Array must have at least 3 elements.");
            scanner.close();
            return;
        }

        int[] scores = new int[n];
        System.out.println("Enter the scores:");
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }

        // Compute and print results
        int[] topThree = findTopThreeScores(scores);
        System.out.println(Arrays.toString(topThree));

        scanner.close();
    }
}
