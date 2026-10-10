import java.util.Scanner;

// ========================================================
// ABSTRACT BASE CLASS (Defines common structure and units)
// ========================================================
abstract class ElectricityConnection {
    private final String typeName;
    private final int units;

    public ElectricityConnection(String typeName, int units) {
        this.typeName = typeName.toUpperCase();
        this.units = units;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public int getUnits() {
        return this.units;
    }

    // Abstract method: Forces each connection type to declare its own custom pricing rules
    public abstract double calculateBill();
}

// ========================================================
// CONCRETE IMPLEMENTATIONS (Encapsulating Specific Billing Rules)
// ========================================================
class HomeConnection extends ElectricityConnection {
    public HomeConnection(int units) {
        super("HOME", units);
    }

    @Override
    public double calculateBill() {
        // Home: 5 per unit for the first 100 units and 7 per unit after that
        int unitsUsed = this.getUnits();
        if (unitsUsed <= 100) {
            return unitsUsed * 5.0;
        } else {
            return (100 * 5.0) + ((unitsUsed - 100) * 7.0);
        }
    }
}

class ShopConnection extends ElectricityConnection {
    public ShopConnection(int units) {
        super("SHOP", units);
    }

    @Override
    public double calculateBill() {
        // Shop: 8 per unit plus a fixed charge of 100
        return (this.getUnits() * 8.0) + 100.0;
    }
}

class FactoryConnection extends ElectricityConnection {
    public FactoryConnection(int units) {
        super("FACTORY", units);
    }

    @Override
    public double calculateBill() {
        // Factory: 6 per unit, with a minimum bill of 1000
        double calculated = this.getUnits() * 6.0;
        return Math.max(calculated, 1000.0);
    }
}

// ========================================================
// MAIN BILLING PROCESSOR
// ========================================================
public class ElectricityBillingSystem {

    // Factory helper method to safely map tokens to explicit connection instances
    private static ElectricityConnection createConnection(String type, int units) {
        switch (type.toUpperCase()) {
            case "HOME":
                return new HomeConnection(units);
            case "SHOP":
                return new ShopConnection(units);
            case "FACTORY":
                return new FactoryConnection(units);
            default:
                throw new IllegalArgumentException("Unknown electricity connection type: " + type);
        }
    }

    public static void main(String args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        double totalBilled = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String type = scanner.next();
            int units = scanner.nextInt();

            // Instantiate concrete types dynamically at runtime
            ElectricityConnection connection = createConnection(type, units);

            // Polymorphic Processing: Core logic runs uniformly across connection profiles
            double billAmount = connection.calculateBill();

            // Output format matched explicitly to problem statements
            System.out.printf("%s: %.2f\n", connection.getTypeName(), billAmount);

            totalBilled += billAmount;
        }

        // Print final aggregate sum
        System.out.printf("Total: %.2f\n", totalBilled);
        scanner.close();
    }
}
