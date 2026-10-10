import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE FOR OPTIONAL CAPABILITIES
// ========================================================
interface NightServiceCapable {
    // Defines the 20% surcharge multiplier for night trips
    double NIGHT_SURCHARGE_MULTIPLIER = 1.20;
}

// ========================================================
// ABSTRACT BASE CLASS FOR ALL CABS
// ========================================================
abstract class Cab {
    private final String cabType;

    public Cab(String cabType) {
        this.cabType = cabType.toUpperCase();
    }

    public String getCabType() {
        return this.cabType;
    }

    // Abstract method: Each cab specifies its own per-km rate
    public abstract double getRatePerKm();

    // Template method: Handles base calculation and applies the minimum fare rule in one place
    public double calculateBaseFare(double km) {
        double calculatedFare = km * this.getRatePerKm();
        return Math.max(calculatedFare, 100.0); // Rule: No trip costs less than 100
    }
}

// ========================================================
// CONCRETE CAB IMPLEMENTATIONS
// ========================================================
class MiniCab extends Cab {
    public MiniCab() {
        super("MINI");
    }

    @Override
    public double getRatePerKm() {
        return 10.0; // Mini: 10 per km
    }
    // MiniCab does not implement NightServiceCapable, so night trips will be rejected
}

class SedanCab extends Cab implements NightServiceCapable {
    public SedanCab() {
        super("SEDAN");
    }

    @Override
    public double getRatePerKm() {
        return 14.0; // Sedan: 14 per km
    }
}

class SUVCab extends Cab implements NightServiceCapable {
    public SUVCab() {
        super("SUV");
    }

    @Override
    public double getRatePerKm() {
        return 18.0; // SUV: 18 per km
    }
}

// ========================================================
// MAIN FARE METER PROCESSOR
// ========================================================
public class CityCabFareMeter {

    // Factory helper method to map type strings to concrete instances
    private static Cab createCab(String type) {
        switch (type.toUpperCase()) {
            case "MINI":
                return new MiniCab();
            case "SEDAN":
                return new SedanCab();
            case "SUV":
                return new SUVCab();
            default:
                throw new IllegalArgumentException("Unknown cab type: " + type);
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
            double km = scanner.nextDouble();
            String time = scanner.next().toUpperCase();

            try {
                Cab cab = createCab(type);

                // Validation Rule: Reject night trips if the cab lacks capability
                if (time.equals("NIGHT") && !(cab instanceof NightServiceCapable)) {
                    System.out.printf("%s: night service not available\n", cab.getCabType());
                    continue;
                }

                // Step 1: Compute fare with minimum boundary condition applied
                double fare = cab.calculateBaseFare(km);

                // Step 2: Apply 20% surcharge if it's a valid night trip
                if (time.equals("NIGHT")) {
                    fare *= NightServiceCapable.NIGHT_SURCHARGE_MULTIPLIER;
                }

                // Output formatted result
                System.out.printf("%s: %.2f\n", cab.getCabType(), fare);
                grandTotal += fare;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Print final aggregate earnings
        System.out.printf("Total: %.2f\n", grandTotal);
        scanner.close();
    }
}
