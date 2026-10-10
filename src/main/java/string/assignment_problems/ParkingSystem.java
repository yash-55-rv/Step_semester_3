import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface Vehicle {
    double calculateCharge(int hours);
}

// ========================================================
// CONCRETE VEHICLE IMPLEMENTATIONS (Encapsulating Charging Rules)
// ========================================================
class BikeVehicle implements Vehicle {
    @Override
    public double calculateCharge(int hours) {
        // ₹10 per hour
        return hours * 10.0;
    }
}

class CarVehicle implements Vehicle {
    @Override
    public double calculateCharge(int hours) {
        // ₹30 for the first hour, plus ₹20 for each additional hour
        if (hours <= 1) {
            return 30.0;
        }
        return 30.0 + ((hours - 1) * 20.0);
    }
}

class TruckVehicle implements Vehicle {
    @Override
    public double calculateCharge(int hours) {
        // ₹50 per hour, with a minimum charge of ₹100
        double calculated = hours * 50.0;
        return Math.max(calculated, 100.0);
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class ParkingSystem {

    // Factory method to isolate the object creation step from the main calculation loop
    private static Vehicle getVehicleInstance(String type) {
        switch (type.toUpperCase()) {
            case "BIKE":
                return new BikeVehicle();
            case "CAR":
                return new CarVehicle();
            case "TRUCK":
                return new TruckVehicle();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
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
            int hours = scanner.nextInt();

            // Uniformly execute calculation through polymorphic dynamic binding
            Vehicle vehicle = getVehicleInstance(type);
            double charge = vehicle.calculateCharge(hours);

            // Output individual formatting matching requirements
            System.out.printf("%s: %.2f\n", type.toUpperCase(), charge);

            grandTotal += charge;
        }

        // Print final grand total
        System.out.printf("Total: %.2f\n", grandTotal);
        scanner.close();
    }
}
