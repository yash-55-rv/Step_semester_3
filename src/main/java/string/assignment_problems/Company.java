import java.util.Scanner;

class Employee {
    // Instance fields
    String empName;
    double salary;

    // Static fields
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    // Constructor
    public Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++; 
    }

    // Static method
    static void printCompanyInfo() {
        System.out.println("\n" + companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class Company {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalToCreate = 3;

        System.out.println("--- Enter Details for " + totalToCreate + " Employees ---");

        for (int i = 1; i <= totalToCreate; i++) {
            System.out.println("\nEmployee #" + i + ":");
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // Clear the buffer line break

            // Create object (this automatically increments employeeCount)
            new Employee(name, salary);
        }

        // Call the static method directly through the Class Name
        Employee.printCompanyInfo();

        scanner.close();
    }
}
