import java.util.Scanner;

// ========================================================
// ABSTRACT BASE CLASS (Prevents direct instantiation)
// ========================================================
abstract class Staff {
    private final String name;

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Abstract method: Forces subclasses to define their specific pay calculation rules
    public abstract double calculatePay();
}

// ========================================================
// CONCRETE STAFF IMPLEMENTATIONS (Encapsulating Unique Pay Logic)
// ========================================================
class FullTimeStaff extends Staff {
    private final double weeklySalary;

    public FullTimeStaff(String name, double weeklySalary) {
        super(name);
        this.weeklySalary = weeklySalary;
    }

    @Override
    public double calculatePay() {
        // Full-time staff are paid their fixed weekly salary
        return this.weeklySalary;
    }
}

class HourlyStaff extends Staff {
    private final double hours;
    private final double rate;

    public HourlyStaff(String name, double hours, double rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculatePay() {
        // Rate for the first 40 hours, and 1.5 × rate for every hour above 40
        if (this.hours <= 40) {
            return this.hours * this.rate;
        } else {
            double regularPay = 40 * this.rate;
            double overtimeHours = this.hours - 40;
            double overtimePay = overtimeHours * (this.rate * 1.5);
            return regularPay + overtimePay;
        }
    }
}

class InternStaff extends Staff {
    private final double stipend;

    public InternStaff(String name, double stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    public double calculatePay() {
        // Interns are paid their fixed stipend
        return this.stipend;
    }
}

// ========================================================
// MAIN PAYROLL PROCESSOR
// ========================================================
public class PayrollSystem {

    // Factory helper method to handle staff processing dynamics
    private static Staff createStaffMember(String type, String name, Scanner scanner) {
        switch (type.toUpperCase()) {
            case "FULLTIME":
                double salary = scanner.nextDouble();
                return new FullTimeStaff(name, salary);
            case "HOURLY":
                double hours = scanner.nextDouble();
                double rate = scanner.nextDouble();
                return new HourlyStaff(name, hours, rate);
            case "INTERN":
                double stipend = scanner.nextDouble();
                return new InternStaff(name, stipend);
            default:
                throw new IllegalArgumentException("Unknown staff contract scheme: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        double totalPayroll = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String type = scanner.next();
            String name = scanner.next();

            // Instantiate dynamic employee contract configurations
            Staff member = createStaffMember(type, name, scanner);

            // Polymorphic computation: Core calculator executes uniformly across contract types
            double earnings = member.calculatePay();

            // Output formatting matching the problem statement requirements
            System.out.printf("%s: %.2f\n", member.getName(), earnings);

            totalPayroll += earnings;
        }

        // Output aggregate final corporate balance summary
        System.out.printf("Total Payroll: %.2f\n", totalPayroll);
        scanner.close();
    }
}
