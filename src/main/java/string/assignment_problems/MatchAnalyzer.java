import java.util.Scanner;

public class MatchAnalyzer {

    // Required private helper method to compute a single match's average
    private static double rowAverage(int[] row) {
        if (row == null || row.length == 0) {
            return 0.0;
        }

        double sum = 0;
        for (int runs : row) {
            sum += runs;
        }
        return sum / row.length;
    }

    // Required classification method that loops through each match row
    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        if (runsPerOver == null || runsPerOver.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < runsPerOver.length; i++) {
            // Call the helper method to get the current match's average
            double average = rowAverage(runsPerOver[i]);

            // Determine match status based on the threshold
            String classification = (average >= threshold) ? "Power Surge" : "Normal";

            // Append formatting to match the example output string structure
            result.append("Match ").append(i).append(": ").append(classification);

            // Add a visual separator between matches, skipping the final element
            if (i < runsPerOver.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total number of matches: ");
        int totalMatches = scanner.nextInt();

        int[][] runsPerOver = new int[totalMatches][];

        // Safely capture jagged array fields of uneven over counts
        for (int i = 0; i < totalMatches; i++) {
            System.out.print("Enter number of overs for Match " + i + ": ");
            int overs = scanner.nextInt();
            runsPerOver[i] = new int[overs];

            System.out.print("Enter runs scored in each over: ");
            for (int j = 0; j < overs; j++) {
                runsPerOver[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the Power Surge threshold: ");
        int threshold = scanner.nextInt();

        // Print final output string matching requested specs
        String classificationResult = classifyMatches(runsPerOver, threshold);
        System.out.println("\n" + classificationResult);

        scanner.close();
    }
}
