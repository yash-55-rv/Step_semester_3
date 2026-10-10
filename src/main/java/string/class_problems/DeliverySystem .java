import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface DeliveryRequest {
    double calculateFee();
}

// ========================================================
// CONCRETE DELIVERY IMPLEMENTATIONS (Encapsulating Pricing Rules)
// ========================================================
class StandardDelivery implements DeliveryRequest {
    private final double weight;
    private final double distance;

    public StandardDelivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    @Override
    public double calculateFee() {
        // Base fee $5 + $0.50 per kg + $0.10 per km
        return 5.0 + (0.50 * weight) + (0.10 * distance);
    }
}

class ExpressDelivery implements DeliveryRequest {
    private final double weight;
    private final double distance;

    public ExpressDelivery(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    @Override
    public double calculateFee() {
        // Base fee $15 + $1.00 per kg + $0.20 per km
        return 15.0 + (1.00 * weight) + (0.20 * distance);
    }
}

class InternationalDelivery implements DeliveryRequest {
    private final double weight;
    private final double distance;
    private final double customsFee;

    public InternationalDelivery(double weight, double distance, double customsFee) {
        this.weight = weight;
        this.distance = distance;
        this.customsFee = customsFee;
    }

    @Override
    public double calculateFee() {
        // Base fee $25 + $2.00 per kg + $0.50 per km + CustomsFee
        return 25.0 + (2.00 * weight) + (0.50 * distance) + customsFee;
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class DeliverySystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        
        int n = scanner.nextInt();
        double grandTotalFee = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;
            
            String type = scanner.next().toUpperCase();
            double weight = scanner.nextDouble();
            double distance = scanner.nextDouble();
            
            DeliveryRequest request;

            // Handle parameter variations inside the initialization step
            switch (type) {
                case "STANDARD":
                    request = new StandardDelivery(weight, distance);
                    break;
                case "EXPRESS":
                    request = new ExpressDelivery(weight, distance);
                    break;
                case "INTERNATIONAL":
                    double customsFee = scanner.nextDouble();
                    request = new InternationalDelivery(weight, distance, customsFee);
                    break;
                default:
                    System.out.println("Unknown delivery type: " + type);
                    continue;
            }

            // Uniformly execute calculation through the interface reference
            double calculatedFee = request.calculateFee();
            
            // Output individual formatting matching requirements
            System.out.printf("%s: %.2f\n", type, calculatedFee);
            
            grandTotalFee += calculatedFee;
        }

        // Print final summary totals
        System.out.printf("Total: %.2f\n", grandTotalFee);
        scanner.close();
    }
}
