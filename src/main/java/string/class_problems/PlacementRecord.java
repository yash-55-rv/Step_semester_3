import java.util.Scanner;

// Define the PlacementRecord class to hold related data together
class PlacementRecord {
    // Fields to store student placement details
    private String studentName;
    private String company;
    private double packageLpa;

    // Constructor to initialize all three fields
    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    // Instance method to print a single formatted record
    public void printRecord() {
        System.out.println(this.studentName + " -> " + this.company + " @ " + this.packageLpa + " LPA");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create an array to store three PlacementRecord objects
        PlacementRecord[] records = new PlacementRecord[3];

        // Read 3 student placement details from the user
        for (int i = 0; i < 3; i++) {
            String line = scanner.nextLine();
            // Split the input line by commas and trim any accidental spaces
            String[] details = line.split(",");
            
            String name = details[0].trim();
            String company = details[1].trim();
            double packageLpa = Double.parseDouble(details[2].trim());

            // Instantiate the object and store it in the array
            records[i] = new PlacementRecord(name, company, packageLpa);
        }

        System.out.println(); // Prints a blank line matching the sample output spacing

        // Iterate through the array using a loop and print each record
        for (PlacementRecord record : records) {
            record.printRecord();
        }

        scanner.close();
    }
}
