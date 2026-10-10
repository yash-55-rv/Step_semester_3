import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface Journey {
    double calculateFare();
}

// ========================================================
// CONCRETE JOURNEY IMPLEMENTATIONS (Encapsulating Pricing Rules)
// ========================================================
class BusJourney implements Journey {
    private final double distance;

    public BusJourney(double distance) {
        this.distance = distance;
    }

    @Override
    public double calculateFare() {
        // Base fare $2 + $0.10 per km. Max fare is capped at $10.
        double calculated = 2.0 + (0.10 * distance);
        return Math.min(calculated, 10.0);
    }
}

class TrainJourney implements Journey {
    private final double distance;

    public TrainJourney(double distance) {
        this.distance = distance;
    }

    @Override
    public double calculateFare() {
        // Base fare $3 + $0.15 per km.
        return 3.0 + (0.15 * distance);
    }
}

class MetroJourney implements Journey {
    private final double distance;
    private final double peakHourFactor;

    public MetroJourney(double distance, double peakHourFactor) {
        this.distance = distance;
        this.peakHourFactor = peakHourFactor;
    }

    @Override
    public double calculateFare() {
        // (Base fare $1.50 + $0.20 per km) multiplied by PeakHourFactor
        return (1.50 + (0.20 * distance)) * peakHourFactor;
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class TransitSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        double grandTotalFare = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String type = scanner.next().toUpperCase();
            double distance = scanner.nextDouble();

            Journey journey;

            // Handle parameter variations cleanly during object construction
            switch (type) {
                case "BUS":
                    journey = new BusJourney(distance);
                    break;
                case "TRAIN":
                    journey = new TrainJourney(distance);
                    break;
                case "METRO":
                    double factor = scanner.nextDouble();
                    journey = new MetroJourney(distance, factor);
                    break;
                default:
                    System.out.println("Unknown transport type: " + type);
                    continue;
            }

            // Uniform processing via the polymorphic interface reference
            double calculatedFare = journey.calculateFare();
            
            // Output individual formatting matching requirements
            System.out.printf("%s: %.2f\n", type, calculatedFare);

            grandTotalFare += calculatedFare;
        }

        // Print final grand total
        System.out.printf("Total: %.2f\n", grandTotalFare);
        scanner.close();
    }
}
