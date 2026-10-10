import java.util.Scanner;

// ========================================================
// ABSTRACT BASE CLASS (Handles the shared booking fee)
// ========================================================
abstract class TravelBooking {
    // REQUIREMENT: The fee is written in ONE place only. Change this single line to update it.
    private static final double BOOKING_FEE = 50.0;
    
    private final String mode;
    private final double distanceKm;

    public TravelBooking(String mode, double distanceKm) {
        this.mode = mode.toUpperCase();
        this.distanceKm = distanceKm;
    }

    public String getMode() {
        return this.mode;
    }

    public double getDistanceKm() {
        return this.distanceKm;
    }

    // Abstract method: Forces each mode to implement its own base fare logic
    public abstract double calculateBaseFare();

    // Template method: Combines the unique base fare with the single shared booking fee
    public double calculateTotalFare() {
        return this.calculateBaseFare() + BOOKING_FEE;
    }
}

// ========================================================
// CONCRETE BOOKING IMPLEMENTATIONS (Encapsulating Base Fares)
// ========================================================
class BusBooking extends TravelBooking {
    public BusBooking(double distanceKm) {
        super("BUS", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        // Bus fare: 2 per km
        return this.getDistanceKm() * 2.0;
    }
}

class TrainBooking extends TravelBooking {
    public TrainBooking(double distanceKm) {
        super("TRAIN", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        // Train fare: 1.5 per km
        return this.getDistanceKm() * 1.5;
    }
}

class FlightBooking extends TravelBooking {
    public FlightBooking(double distanceKm) {
        super("FLIGHT", distanceKm);
    }

    @Override
    public double calculateBaseFare() {
        // Flight fare: 2500 plus 4 per km
        return 2500.0 + (this.getDistanceKm() * 4.0);
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class TravelBookingSystem {

    // Factory helper method to safely map tokens to explicit booking modes
    private static TravelBooking createBooking(String mode, double distanceKm) {
        switch (mode.toUpperCase()) {
            case "BUS":
                return new BusBooking(distanceKm);
            case "TRAIN":
                return new TrainBooking(distanceKm);
            case "FLIGHT":
                return new FlightBooking(distanceKm);
            default:
                throw new IllegalArgumentException("Unknown travel mode type: " + mode);
        }
    }

    public static void main(String args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String mode = scanner.next();
            double distanceKm = scanner.nextDouble();

            // Instantiate dynamic concrete objects at runtime
            TravelBooking booking = createBooking(mode, distanceKm);

            // Polymorphic Processing: Executes uniformly without type checking blocks
            double totalFare = booking.calculateTotalFare();

            // Output format matched explicitly to problem statements
            System.out.printf("%s: %.2f\n", booking.getMode(), totalFare);
        }

        scanner.close();
    }
}
