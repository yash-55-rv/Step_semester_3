import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private int credits;
    private int labCredits;

    // Four-argument constructor setting all fields directly
    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    // Three-argument constructor for theory-only courses (chains to the one above)
    public Course(String code, String title, int credits) {
        this(code, title, credits, 0); // Reuses the 4-arg constructor logic
    }

    // Method to calculate total credits
    public int totalCredits() {
        return this.credits + this.labCredits;
    }

    public String getCode() {
        return this.code;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Parse Course 1 (Theory-only)
        String line1 = scanner.nextLine();
        String[] tokens1 = line1.split(",");
        String code1 = tokens1[0].trim();
        String title1 = tokens1[1].trim();
        int credits1 = Integer.parseInt(tokens1[2].trim());
        
        // Construct using the 3-argument constructor
        Course course1 = new Course(code1, title1, credits1);

        // 2. Parse Course 2 (With Lab Component)
        String line2 = scanner.nextLine();
        String[] tokens2 = line2.split(",");
        String code2 = tokens2[0].trim();
        String title2 = tokens2[1].trim();
        int credits2 = Integer.parseInt(tokens2[2].trim());
        
        // Extract lab credits by stripping non-digits from the last segment (e.g., "labCredits 1" -> "1")
        String labStr = tokens2[3].replaceAll("[^0-9]", "");
        int labCredits2 = Integer.parseInt(labStr);
        
        // Construct using the 4-argument constructor
        Course course2 = new Course(code2, title2, credits2, labCredits2);

        System.out.println(); // Blank line for output formatting

        // Print results
        System.out.println(course1.getCode() + " total credits: " + course1.totalCredits());
        System.out.println(course2.getCode() + " total credits: " + course2.totalCredits());

        scanner.close();
    }
}
