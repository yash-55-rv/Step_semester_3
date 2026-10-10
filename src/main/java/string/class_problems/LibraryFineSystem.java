import java.util.Scanner;

// ========================================================
// ABSTRACT BASE CLASS (Defines common structure)
// ========================================================
abstract class LibraryItem {
    private final String title;
    private final int daysLate;

    public LibraryItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    public String getTitle() {
        return this.title;
    }

    public int getDaysLate() {
        return this.daysLate;
    }

    // Abstract method: Forces media types to implement unique fine rules
    public abstract double calculateFine();
}

// ========================================================
// CONCRETE ITEM IMPLEMENTATIONS (Encapsulating Specific Fine Rules)
// ========================================================
class BookItem extends LibraryItem {
    public BookItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        // Books: 2 per day late
        return this.getDaysLate() * 2.0;
    }
}

class DVDItem extends LibraryItem {
    public DVDItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        // DVDs: 5 per day late, up to a maximum of 50
        double fine = this.getDaysLate() * 5.0;
        return Math.min(fine, 50.0);
    }
}

class MagazineItem extends LibraryItem {
    public MagazineItem(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    public double calculateFine() {
        // Magazines: 1 per day late
        return this.getDaysLate() * 1.0;
    }
}

// ========================================================
// MAIN FINE MANAGEMENT PROCESSOR
// ========================================================
public class LibraryFineSystem {

    // Factory helper method to parse tokens and instantiate subtypes cleanly
    private static LibraryItem createLibraryItem(String type, String title, int daysLate) {
        switch (type.toUpperCase()) {
            case "BOOK":
                return new BookItem(title, daysLate);
            case "DVD":
                return new DVDItem(title, daysLate);
            case "MAGAZINE":
                return new MagazineItem(title, daysLate);
            default:
                throw new IllegalArgumentException("Unknown item catalog type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        double totalFines = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String type = scanner.next();
            String title = scanner.next();
            int daysLate = scanner.nextInt();

            // Instantiate concrete item mapping types dynamically
            LibraryItem item = createLibraryItem(type, title, daysLate);

            // Polymorphic processing: The loop logic never changes, regardless of media types
            double fineAmount = item.calculateFine();

            // Output data formatted to requirements
            System.out.printf("%s: %.2f\n", item.getTitle(), fineAmount);

            totalFines += fineAmount;
        }

        // Print aggregated results
        System.out.printf("Total Fines: %.2f\n", totalFines);
        scanner.close();
    }
}
