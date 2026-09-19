import java.util.Scanner;

public class SeatingOptimizer {

    // Required private helper method to compute a single row's average
    private static double rowAverage(int[] row) {
        if (row == null || row.length == 0) {
            return 0.0;
        }
        
        double sum = 0;
        for (int score : row) {
            sum += score;
        }
        return sum / row.length;
    }

    // Required classification method that loops through each row
    public static String classifyRows(int[][] seatingScores, int threshold) {
        if (seatingScores == null || seatingScores.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < seatingScores.length; i++) {
            // Call the helper method to get the current row's average
            double average = rowAverage(seatingScores[i]);

            // Determine zone status based on the threshold
            String zone = (average < threshold) ? "Quiet Zone" : "Buzzing Zone";

            // Append formatting to match the example output
            result.append("Row ").append(i).append(": ").append(zone);
            
            // Add a visual separator between rows, but skip the very last one
            if (i < seatingScores.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of seating rows: ");
        int totalRows = scanner.nextInt();

        int[][] seatingScores = new int[totalRows][];

        // Read jagged array row lengths and elements dynamically
        for (int i = 0; i < totalRows; i++) {
            System.out.print("Enter number of seats in Row " + i + ": ");
            int seats = scanner.nextInt();
            seatingScores[i] = new int[seats];

            System.out.print("Enter scores for Row " + i + ": ");
            for (int j = 0; j < seats; j++) {
                seatingScores[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter classification threshold score: ");
        int threshold = scanner.nextInt();

        // Print output
        String classificationResult = classifyRows(seatingScores, threshold);
        System.out.println("\n" + classificationResult);

        scanner.close();
    }
}
