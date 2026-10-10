import java.util.Scanner;

// ========================================================
// ABSTRACT BASE CLASS (Encapsulating Common Attributes)
// ========================================================
abstract class Employee {
    private final String name;
    private final double monthlySalary;

    public Employee(String name, double monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getName() {
        return this.name;
    }

    public double getMonthlySalary() {
        return this.monthlySalary;
    }

    // Polymorphic method to be customized by specific employee types
    public abstract double calculateBonus();
}

// ========================================================
// CONCRETE EMPLOYEE IMPLEMENTATIONS (Encapsulating Bonus Rules)
// ========================================================
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        // Full-time employees get 10% of their monthly salary
        return this.getMonthlySalary() * 0.10;
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        // Part-time employees get 5% of their monthly salary
        return this.getMonthlySalary() * 0.05;
    }
}

class InternEmployee extends Employee {
    public InternEmployee(String name, double monthlySalary) {
        super(name, monthlySalary);
    }

    @Override
    public double calculateBonus() {
        // Interns get a fixed bonus of ₹2,000, whatever their salary
        return 2000.0;
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class PayrollSystem {

    // Factory method to isolate object construction from processing loop
    private static Employee createEmployee(String type, String name, double salary) {
        switch (type.toUpperCase()) {
            case "FULLTIME":
                return new FullTimeEmployee(name, salary);
            case "PARTTIME":
                return new PartTimeEmployee(name, salary);
            case "INTERN":
                return new InternEmployee(name, salary);
            default:
                throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }

    public static void main(String args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        double grandTotalBonus = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String type = scanner.next();
            String name = scanner.next();
            double salary = scanner.nextDouble();

            // Instantiate dynamic subtype and reference it through the base type
            Employee employee = createEmployee(type, name, salary);

            // Uniformly execute calculation through polymorphic dynamic binding
            double bonus = employee.calculateBonus();

            // Output individual formatting matching requirements
            System.out.printf("%s: %.2f\n", employee.getName(), bonus);

            grandTotalBonus += bonus;
        }

        // Print final grand summary total
        System.out.printf("Total Bonus: %.2f\n", grandTotalBonus);
        scanner.close();
    }
}
