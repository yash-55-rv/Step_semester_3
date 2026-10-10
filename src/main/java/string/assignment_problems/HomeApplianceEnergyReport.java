import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE FOR OPTIONAL CAPABILITIES
// ========================================================
interface SaverModeCapable {
    // Defines the 25% energy reduction multiplier (100% - 25% = 75%)
    double SAVER_MODE_MULTIPLIER = 0.75;
}

// ========================================================
// ABSTRACT BASE CLASS FOR ALL APPLIANCES
// ========================================================
abstract class Appliance {
    private final String name;

    public Appliance(String name) {
        this.name = name.toUpperCase();
    }

    public String getName() {
        return this.name;
    }

    // Abstract method: Each appliance encapsulates its own unique power rating in Watts
    public abstract double getPowerRatingWatts();

    // Shared calculation logic: Base Units (kWh) = power × hours ÷ 1000
    public double calculateBaseUnits(double hours) {
        return (this.getPowerRatingWatts() * hours) / 1000.0;
    }
}

// ========================================================
// CONCRETE APPLIANCE IMPLEMENTATIONS
// ========================================================
class FridgeAppliance extends Appliance {
    public FridgeAppliance() {
        super("FRIDGE");
    }

    @Override
    public double getPowerRatingWatts() {
        return 150.0; // Fridge: 150 W
    }
}

class ACAppliance extends Appliance implements SaverModeCapable {
    public ACAppliance() {
        super("AC");
    }

    @Override
    public double getPowerRatingWatts() {
        return 1500.0; // AC: 1500 W
    }
}

class TVAppliance extends Appliance {
    public TVAppliance() {
        super("TV");
    }

    @Override
    public double getPowerRatingWatts() {
        return 100.0; // TV: 100 W
    }
}

class WasherAppliance extends Appliance implements SaverModeCapable {
    public WasherAppliance() {
        super("WASHER");
    }

    @Override
    public double getPowerRatingWatts() {
        return 500.0; // Washer: 500 W
    }
}

// ========================================================
// MAIN ENERGY REPORT PROCESSOR
// ========================================================
public class HomeApplianceEnergyReport {

    // Factory helper method to map type strings to concrete instances
    private static Appliance createAppliance(String type) {
        switch (type.toUpperCase()) {
            case "FRIDGE":
                return new FridgeAppliance();
            case "AC":
                return new ACAppliance();
            case "TV":
                return new TVAppliance();
            case "WASHER":
                return new WasherAppliance();
            default:
                throw new IllegalArgumentException("Unknown appliance type: " + type);
        }
    }

    public static void main(String args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        double totalCost = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNextLine()) break;

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            // Split line into tokens up to 3 parts maximum: [APPLIANCE, hours, (optional) SAVER]
            String tokens = line.split("\\s+");
            String applianceType = tokens[0];
            double hours = Double.parseDouble(tokens[1]);
            boolean wantsSaver = (tokens.length == 3 && tokens[2].equalsIgnoreCase("SAVER"));

            try {
                Appliance appliance = createAppliance(applianceType);

                // Validation Rule: If saver mode is requested but not supported, reject immediately
                if (wantsSaver && !(appliance instanceof SaverModeCapable)) {
                    System.out.printf("%s: saver mode not supported\n", appliance.getName());
                    continue;
                }

                // Step 1: Calculate core baseline units (kWh)
                double units = appliance.calculateBaseUnits(hours);

                // Step 2: Apply eco reduction modifier if applicable
                if (wantsSaver) {
                    units *= SaverModeCapable.SAVER_MODE_MULTIPLIER;
                }

                // Step 3: Compute financial cost mapping (Cost = units * 8)
                double cost = units * 8.0;

                // Print individual appliance data row formatted to 2 decimal places
                System.out.printf("%s: Units=%.2f Cost=%.2f\n", appliance.getName(), units, cost);
                totalCost += cost;

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // Print final aggregate home utilities cost overview
        System.out.printf("Total Cost: %.2f\n", totalCost);
        scanner.close();
    }
}
