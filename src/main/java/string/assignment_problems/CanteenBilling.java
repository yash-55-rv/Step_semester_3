import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface Customer {
    double calculateFinalAmount(double amount);
}

// ========================================================
// CONCRETE CUSTOMER IMPLEMENTATIONS (Encapsulating Pricing Rules)
// ========================================================
class StudentCustomer implements Customer {
    @Override
    public double calculateFinalAmount(double amount) {
        return amount * 0.90; // 10% discount
    }
}

class StaffCustomer implements Customer {
    @Override
    public double calculateFinalAmount(double amount) {
        return amount * 0.95; // 5% discount
    }
}

class GuestCustomer implements Customer {
    @Override
    public double calculateFinalAmount(double amount) {
        return amount + 10.0; // Full amount + ₹10 service charge
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class CanteenBilling {

    // Factory method to isolate the object creation step from the main calculation loop
    private static Customer getCustomerInstance(String type) {
        switch (type.toUpperCase()) {
            case "STUDENT":
                return new StudentCustomer();
            case "STAFF":
                return new StaffCustomer();
            case "GUEST":
                return new GuestCustomer();
            default:
                throw new IllegalArgumentException("Unknown customer type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        double grandTotal = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String type = scanner.next();
            double amount = scanner.nextDouble();

            // Uniformly execute calculation through polymorphic dynamic binding
            Customer customer = getCustomerInstance(type);
            double finalAmount = customer.calculateFinalAmount(amount);

            // Output individual formatting matching requirements
            System.out.printf("%s: %.2f\n", type.toUpperCase(), finalAmount);

            grandTotal += finalAmount;
        }

        // Print final grand total
        System.out.printf("Total: %.2f\n", grandTotal);
        scanner.close();
    }
}
