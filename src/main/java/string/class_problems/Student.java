import java.util.Scanner;

class Student {
    private String name;
    private int attendance;

    // Static fields shared globally across all instances
    public static final String collegeName = "SRM Institute of Science and Technology";
    private static int studentCount = 0;

    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++; 
    }

    public static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Read the number of student objects to create
        // Input format expected: "2 Student objects created" OR just the number "2"
        String inputLine = scanner.nextLine();
        
        // Extract only the digits from the input string to find the loop count
        String numericOnly = inputLine.replaceAll("[^0-9]", "");
        int targetCount = numericOnly.isEmpty() ? 0 : Integer.parseInt(numericOnly);

        // 2. Loop to simulate taking student records (or creating mock objects based on the count)
        for (int i = 0; i < targetCount; i++) {
            // Instantiating dummy records to simulate data stream entry
            new Student("Student_" + (i + 1), 100);
        }

        // 3. Print the class-level shared statistics
        Student.printCollegeInfo();

        scanner.close();
    }
}
