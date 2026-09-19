import java.util.Scanner;

class Employee {
    private String empId;
    private String empName;
    private double salary;
    private boolean isIntern;

    // Three-argument master constructor for permanent employees
    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false; // Default setting for permanent employees
    }

    // Two-argument constructor for interns (chains to the 3-arg constructor)
    public Employee(String empId, String empName) {
        this(empId, empName, 0.0); // Reuses the master constructor setup logic
        this.isIntern = true;       // Overrides/sets the intern flag explicitly
    }

    // Method to display formatted profile information
    public void printProfile() {
        System.out.println(this.empId + " | " + this.empName + " | Rs " + this.salary + " | Intern: " + this.isIntern);
    }
}

public class EmployeeSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Process Permanent Employee Input
        String line1 = scanner.nextLine();
        // Remove structural prefix text "Permanent: " if present
        line1 = line1.replace("Permanent:", "").trim();
        String[] tokens1 = line1.split(",");
        String id1 = tokens1[0].trim();
        String name1 = tokens1[1].trim();
        double salary1 = Double.parseDouble(tokens1[2].trim());
        
        // Invoke the 3-argument constructor
        Employee emp1 = new Employee(id1, name1, salary1);

        // 2. Process Intern Employee Input
        String line2 = scanner.nextLine();
        // Remove structural prefix text "Intern: " if present
        line2 = line2.replace("Intern:", "").trim();
        String[] tokens2 = line2.split(",");
        String id2 = tokens2[0].trim();
        String name2 = tokens2[1].trim();
        
        // Invoke the chained 2-argument constructor
        Employee emp2 = new Employee(id2, name2);

        System.out.println(); // Prints a blank line matching the sample output spacing

        // 3. Print output profiles
        emp1.printProfile();
        emp2.printProfile();

        scanner.close();
    }
}
