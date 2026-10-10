import java.util.Scanner;

// ========================================================
// ABSTRACT BASE CLASS (Handles shared fee and blocks direct instantiation)
// ========================================================
abstract class MovieTicket {
    // REQUIREMENT: The convenience fee is written in ONE place only.
    private static final double CONVENIENCE_FEE = 20.0;
    
    private final String seatType;
    private final int count;

    public MovieTicket(String seatType, int count) {
        this.seatType = seatType.toUpperCase();
        this.count = count;
    }

    public String getSeatType() {
        return this.seatType;
    }

    public int getCount() {
        return this.count;
    }

    // Abstract method: Forces subclasses to specify their base price per seat
    public abstract double getPricePerSeat();

    // Template method: Uniformly applies the pricing calculation formula across all types
    public double calculateTotalAmount() {
        return (this.getPricePerSeat() + CONVENIENCE_FEE) * this.count;
    }
}

// ========================================================
// CONCRETE SEAT IMPLEMENTATIONS (Encapsulating Specific Base Prices)
// ========================================================
class RegularTicket extends MovieTicket {
    public RegularTicket(int count) {
        super("REGULAR", count);
    }

    @Override
    public double getPricePerSeat() {
        return 150.0; // Regular seat: 150 per ticket
    }
} lounge

class PremiumTicket extends MovieTicket {
    public PremiumTicket(int count) {
        super("PREMIUM", count);
    }

    @Override
    public double getPricePerSeat() {
        return 250.0; // Premium seat: 250 per ticket
    }
}

class ReclinerTicket extends MovieTicket {
    public ReclinerTicket(int count) {
        super("RECLINER", count);
    }

    @Override
    public double getPricePerSeat() {
        return 400.0; // Recliner seat: 400 per ticket
    }
}

// ========================================================
// MAIN CINEMA BILLING PROCESSOR
// ========================================================
public class MovieTicketCounter {

    // Factory helper method to safely map strings to concrete object instances
    private static MovieTicket createTicket(String seatType, int count) {
        switch (seatType.toUpperCase()) {
            case "REGULAR":
                return new RegularTicket(count);
            case "PREMIUM":
                return new PremiumTicket(count);
            case "RECLINER":
                return new ReclinerTicket(count);
            default:
                throw new IllegalArgumentException("Unknown seat type: " + seatType);
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

            String seatType = scanner.next();
            int count = scanner.nextInt();

            // Polymorphically build the appropriate ticket class instance
            MovieTicket ticket = createTicket(seatType, count);

            // Execute calculation uniformly without explicit conditional checks in the loop
            double bookingAmount = ticket.calculateTotalAmount();

            // Print the individual booking result formatted to 2 decimal places
            System.out.printf("%s: %.2f\n", ticket.getSeatType(), bookingAmount);

            grandTotal += bookingAmount;
        }

        // Output aggregate final collection summary
        System.out.printf("Total: %.2f\n", grandTotal);
        scanner.close();
    }
}
