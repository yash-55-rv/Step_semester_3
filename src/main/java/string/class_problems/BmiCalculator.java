import java.util.Random;

public class BmiCalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-11s | %-6s | %-12s\n", "Person", "Height (m)", "Weight (kg)", "BMI", "Status");
        System.out.println("---------------------------------------------------------");
        
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);
            System.out.printf("Person %-3d | %-8.2f m | %-9.1f kg | %-6.2f | %-12s\n", (i + 1), heights[i], weights[i], bmi, status);
        }
        System.out.println("---------------------------------------------------------");
    }

    public static void main(String[] args) {
        int teamSize = 10;
        double[] heights = new double[teamSize];
        double[] weights = new double[teamSize];
        Random random = new Random();

        // Populate with random realistic values for a fast live demo
        for (int i = 0; i < teamSize; i++) {
            heights[i] = 1.50 + (1.95 - 1.50) * random.nextDouble(); // Range: 1.50m - 1.95m
            weights[i] = 45.0 + (110.0 - 45.0) * random.nextDouble(); // Range: 45kg - 110kg
        }

        printWellnessReport(heights, weights);
    }
}
