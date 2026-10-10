import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE FOR OPTIONAL BEHAVIOR
// ========================================================
interface Insurable {
    double calculateInsurance();
}

// ========================================================
// ABSTRACT BASE CLASS FOR ALL PARCELS
// ========================================================
abstract class Parcel {
    private final String typeName;
    private final double weight;
    private final double declaredValue;

    public Parcel(String typeName, double weight, double declaredValue) {
        this.typeName = typeName.toUpperCase();
        this.weight = weight;
        this.declaredValue = declaredValue;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getDeclaredValue() {
        return this.declaredValue;
    }

    // Abstract method: Every parcel must specify its core shipping charge calculation
    public abstract double calculateCharge();
}

// ========================================================
// CONCRETE PARCEL IMPLEMENTATIONS
// ========================================================
class StandardParcel extends Parcel {
    public StandardParcel(double weight, double declaredValue) {
        super("STANDARD", weight, declaredValue);
    }

    @Override
    public double calculateCharge() {
        // Standard: 40 plus 10 per kg
        return 40.0 + (10.0 * this.getWeight());
    }
    // Standard parcels do not implement Insurable, ensuring their insurance defaults to 0.00
}

class ExpressParcel extends Parcel implements Insurable {
    public ExpressParcel(double weight, double declaredValue) {
        super("EXPRESS", weight, declaredValue);
    }

    @Override
    public double calculateCharge() {
        // Express: 80 plus 15 per kg
        return 80.0 + (15.0 * this.getWeight());
    }

    @Override
    public double calculateInsurance() {
        // Insurance for express is 2% of the declared value
        return this.getDeclaredValue() * 0.02;
    }
}

class FragileParcel extends Parcel implements Insurable {
    public FragileParcel(double weight, double declaredValue) {
        super("FRAGILE", weight, declaredValue);
    }

    @Override
    public double calculateCharge() {
        // Fragile: the standard charge formula plus a handling fee of 50
        double standardBaseCharge = 40.0 + (10.0 * this.getWeight());
        return standardBaseCharge + 50.0;
    }

    @Override
    public double calculateInsurance() {
        // Insurance for fragile is 2% of the declared value
        return this.getDeclaredValue() * 0.02;
    }
}

// ========================================================
// MAIN COURIER SYSTEM PROCESSOR
// ========================================================
public class ParcelShippingDesk {

    // Factory method to map type codes to concrete parcel instances
    private static Parcel createParcel(String type, double weight, double declaredValue) {
        switch (type.toUpperCase()) {
            case "STANDARD":
                return new StandardParcel(weight, declaredValue);
            case "EXPRESS":
                return new ExpressParcel(weight, declaredValue);
            case "FRAGILE":
                return new FragileParcel(weight, declaredValue);
            default:
                throw new IllegalArgumentException("Unknown parcel type: " + type);
        }
    }

    public static void main(String args) {
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
            double weight = scanner.nextDouble();
            double declaredValue = scanner.nextDouble();

            // Instantiate dynamic subtype polymorphically
            Parcel parcel = createParcel(type, weight, declaredValue);

            double charge = parcel.calculateCharge();
            double insurance = 0.0;

            // Safe type check strategy using the interface capability contract (Hint)
            if (parcel instanceof Insurable) {
                insurance = ((Insurable) parcel).calculateInsurance();
            }

            double total = charge + insurance;

            // Print output formatted precisely matching structural criteria
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f\n", 
                    parcel.getTypeName(), charge, insurance, total);

            grandTotal += total;
        }

        // Print final aggregate tracking output summaries
        System.out.printf("Grand Total: %.2f\n", grandTotal);
        scanner.close();
    }
}
