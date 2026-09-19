import java.util.Arrays;
import java.util.Scanner;

public class ScoreBoosterSolution {

    // Modifies the caller's original array directly in place
    static void curveScores(int[] scores, int bonus) {
        // Iterate through each index and add the flat bonus to the existing value
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PROBLEM 1 • Hackathon Score Curve Booster ===");

        // Take size of the array
        System.out.print("Enter the number of teams: ");
        int size = scanner.nextInt();

        // Take array elements
        int[] scores = new int[size];
        System.out.print("Enter " + size + " baseline scores separated by spaces: ");
        for (int i = 0; i < size; i++) {
            scores[i] = scanner.nextInt();
        }

        // Take the flat bonus amount
        System.out.print("Enter the flat bonus to apply: ");
        int bonus = scanner.nextInt();

        // Invoke the method (modifies the 'scores' reference in place)
        curveScores(scores, bonus);

        // Print the finalized leaderboard using Arrays.toString(...) as required
        System.out.println("Final Leaderboard: " + Arrays.toString(scores));

        scanner.close();
    }
}
