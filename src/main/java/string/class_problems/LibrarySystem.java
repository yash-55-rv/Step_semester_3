import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface LibraryItem {
    String getTitle();
    int getBorrowDurationDays();
}

// ========================================================
// CONCRETE ITEM IMPLEMENTATIONS (Encapsulating Rules)
// ========================================================
class BookItem implements LibraryItem {
    private final String title;

    public BookItem(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public int getBorrowDurationDays() {
        return 14; // Books have a standard borrowing period of 14 days
    }
}

class DVDItem implements LibraryItem {
    private final String title;

    public DVDItem(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public int getBorrowDurationDays() {
        return 7; // DVDs have a standard borrowing period of 7 days
    }
}

class MagazineItem implements LibraryItem {
    private final String title;

    public MagazineItem(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public int getBorrowDurationDays() {
        return 3; // Magazines have a standard borrowing period of 3 days
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class LibrarySystem {

    // Factory method to instantiate the correct item type dynamically
    private static LibraryItem createItem(String type, String title) {
        switch (type.toUpperCase()) {
            case "BOOK":
                return new BookItem(title);
            case "DVD":
                return new DVDItem(title);
            case "MAGAZINE":
                return new MagazineItem(title);
            default:
                throw new IllegalArgumentException("Unknown library item type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline character

        // Base current date defined by the business rules
        LocalDate currentDate = LocalDate.parse("2023-10-26");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;
            
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            // Split line by the first whitespace into: [ItemType, ItemTitle]
            String[] parts = line.split("\\s+", 2);
            if (parts.length < 2) continue;

            String itemType = parts[0];
            // Remove any surrounding wrapping quotes from the title
            String itemTitle = parts[1].replace("\"", "");

            try {
                // Uniformly process items polymorphically
                LibraryItem item = createItem(itemType, itemTitle);
                
                // Dynamic binding automatically picks up the correct duration rule
                LocalDate dueDate = currentDate.plusDays(item.getBorrowDurationDays());
                
                // Print individual transaction output matching requirements
                System.out.printf("%s: %s\n", item.getTitle(), dueDate.format(formatter));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        
        scanner.close();
    }
}
