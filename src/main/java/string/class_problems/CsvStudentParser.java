import java.util.Scanner;

public class CsvStudentParser {

    public static void parseStudentRecord(String csvLine) {
        // Split text by comma
        String[] fields = csvLine.split(",");

        // Validate that exactly 3 fields are present
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        // Print formatted records
        String name = fields[0].trim();
        String rollNo = fields[1].trim();
        String dept = fields[2].trim();

        System.out.println("Name: " + name + " | Roll No: " + rollNo + " | Dept: " + dept);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter CSV line (Name,RollNumber,Department): ");
        String csvLine = scanner.nextLine();
        
        parseStudentRecord(csvLine);
        scanner.close();
    }
}
