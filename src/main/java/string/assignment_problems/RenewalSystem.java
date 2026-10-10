import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface StreamingPlan {
    int getValidityDays();
}

// ========================================================
// CONCRETE PLAN IMPLEMENTATIONS (Encapsulating Plan Validity)
// ========================================================
class BasicPlan implements StreamingPlan {
    @Override
    public int getValidityDays() {
        return 30; // Basic plan is valid for 30 days
    }
}

class StandardPlan implements StreamingPlan {
    @Override
    public int getValidityDays() {
        return 90; // Standard plan is valid for 90 days
    }
}

class PremiumPlan implements StreamingPlan {
    @Override
    public int getValidityDays() {
        return 365; // Premium plan is valid for 365 days
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class RenewalSystem {

    // Factory method to isolate object construction from processing loop
    private static StreamingPlan getPlanInstance(String type) {
        switch (type.toUpperCase()) {
            case "BASIC":
                return new BasicPlan();
            case "STANDARD":
                return new StandardPlan();
            case "PREMIUM":
                return new PremiumPlan();
            default:
                throw new IllegalArgumentException("Unknown subscription plan type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        
        // Define date format patterns matching specifications
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String type = scanner.next();
            String name = scanner.next();
            String startDateStr = scanner.next();

            // Parse text date string to an immutable LocalDate object
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);

            // Fetch dynamic type context through the factory mapping
            StreamingPlan plan = getPlanInstance(type);

            // Polymorphically extract validity rules and perform date arithmetic
            LocalDate renewalDate = startDate.plusDays(plan.getValidityDays());

            // Output formatted results matching criteria
            System.out.printf("%s: %s\n", name, renewalDate.format(formatter));
        }

        scanner.close();
    }
}
