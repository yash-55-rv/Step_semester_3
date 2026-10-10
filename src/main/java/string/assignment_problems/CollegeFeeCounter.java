import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE FOR TRIPS / OPTIONAL CAPABILITIES
// ========================================================
interface TransportUser {
    // REQUIREMENT: Keep the transport fee written in one place only
    double TRANSPORT_FEE = 12000.0;
}

// ========================================================
// ABSTRACT BASE CLASS FOR ALL STUDENTS
// ========================================================
abstract class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Abstract method: Forces each student type to declare its custom fee rules
    public abstract double calculateBaseFee();

    // Template method: Uniformly evaluates base costs and layers on transport fees if applicable
    public final double calculateTotalFee() {
        double fee = this.calculateBaseFee();
        if (this instanceof TransportUser) {
            fee += TransportUser.TRANSPORT_FEE;
        }
        return fee;
    }
}

// ========================================================
// CONCRETE STUDENT IMPLEMENTATIONS
// ========================================================
class DayScholarStudent extends Student implements TransportUser {
    public DayScholarStudent(String name) {
        super(name);
    }

    @Override
    public double calculateBaseFee() {
        return 40000.0; // Day scholar tuition: 40,000
    }
}

class HostellerStudent extends Student {
    public HostellerStudent(String name) {
        super(name);
    }

    @Override
    public double calculateBaseFee() {
        return 40000.0 + 60000.0; // Tuition 40,000 + Hostel fee 60,000
    }
}

class ScholarshipStudent extends Student implements TransportUser {
    public ScholarshipStudent(String name) {
        super(name);
    }

    @Override
    public double calculateBaseFee() {
        return 20000.0; // Half tuition fee: 20,000
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class CollegeFeeCounter {

    // Factory helper method to map type strings to concrete instances
    private static Student createStudent(String type, String name) {
        switch (type.toUpperCase()) {
            case "DAY_SCHOLAR":
                return new DayScholarStudent(name);
            case "HOSTELLER":
                return new HostellerStudent(name);
            case "SCHOLAR":
                return new ScholarshipStudent(name);
            default:
                throw new IllegalArgumentException("Unknown student type context: " + type);
        }
    }

    public static void main(String args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        double totalCollected = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String type = scanner.next();
            String name = scanner.next();

            // Instantiate concrete types dynamically at runtime
            Student student = createStudent(type, name);

            // Polymorphic processing: Evaluates without type checking strings inside the loop
            double fee = student.calculateTotalFee();

            // Print formatted results
            System.out.printf("%s: %.2f\n", student.getName(), fee);

            totalCollected += fee;
        }

        // Print final aggregate collection summary
        System.out.printf("Total Collected: %.2f\n", totalCollected);
        scanner.close();
    }
}
