import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface Room {
    double calculateBill();
}

// ========================================================
// CONCRETE ROOM IMPLEMENTATIONS (Encapsulating Pricing Rules)
// ========================================================
class SingleRoom implements Room {
    private final int units;

    public SingleRoom(int units) {
        this.units = units;
    }

    @Override
    public double calculateBill() {
        // ₹8 per unit
        return this.units * 8.0;
    }
}

class SharedRoom implements Room {
    private final int units;
    private final int occupants; // Isolated structural parameter

    public SharedRoom(int units, int occupants) {
        this.units = units;
        this.occupants = occupants;
    }

    @Override
    public double calculateBill() {
        // ₹6 per unit, divided equally by the number of occupants
        return (this.units * 6.0) / this.occupants;
    }
}

class ACRoom implements Room {
    private final int units;

    public ACRoom(int units) {
        this.units = units;
    }

    @Override
    public double calculateBill() {
        // ₹10 per unit, plus a fixed charge of ₹200
        return (this.units * 10.0) + 200.0;
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class HostelBillingSystem {

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

            String type = scanner.next().toUpperCase();
            int units = scanner.nextInt();

            Room room;

            // Handle individual operational parameter configurations during instantiation
            switch (type) {
                case "SINGLE":
                    room = new SingleRoom(units);
                    break;
                case "SHARED":
                    int occupants = scanner.nextInt();
                    room = new SharedRoom(units, occupants); // Capture and bundle the extra param
                    break;
                case "AC":
                    room = new ACRoom(units);
                    break;
                default:
                    System.out.println("Unknown room type: " + type);
                    continue;
            }

            // Uniformly execute calculation through the polymorphic interface reference
            double billAmount = room.calculateBill();
            
            // Output individual formatting matching requirements
            System.out.printf("%s: %.2f\n", type, billAmount);

            grandTotal += billAmount;
        }

        // Print final grand total
        System.out.printf("Total: %.2f\n", grandTotal);
        scanner.close();
    }
}
