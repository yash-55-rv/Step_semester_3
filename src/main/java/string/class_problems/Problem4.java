import java.util.Scanner;

public class Problem4 {

    // Calculates the average score of a single row
    private static double rowAverage(int[] row) {
        int sum = 0;
        for (int score : row) {
            sum += score;
        }
        return (double) sum / row.length;
    }

    // Classifies each row based on the given threshold
    static String classifyRows(int[][] seatingScores, int threshold) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < seatingScores.length; i++) {
            double average = rowAverage(seatingScores[i]);
            
            if (average < threshold) {
                result.append("Row ").append(i).append(": Quiet Zone");
            } else {
                result.append("Row ").append(i).append(": Buzzing Zone");
            }
            
            // Add a separator between rows, but not after the last one
            if (i < seatingScores.length - 1) {
                result.append(" | ");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Get the matrix dimensions
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns per row: ");
        int cols = scanner.nextInt();

        // 2. Populate the 2D array from user input
        int[][] seatingScores = new int[rows][cols];
        System.out.println("Enter the scores row by row:");
        for (int i = 0; i < rows; i++) {
            System.out.print("  Scores for Row " + i + " (" + cols + " values): ");
            for (int j = 0; j < cols; j++) {
                seatingScores[i][j] = scanner.nextInt();
            }
        }

        // 3. Get the threshold value
        System.out.print("Enter the classification threshold: ");
        int threshold = scanner.nextInt();

        // 4. Process and print results
        System.out.println("\n--- Classification Output ---");
        System.out.println(classifyRows(seatingScores, threshold));

        scanner.close();
    }
}
