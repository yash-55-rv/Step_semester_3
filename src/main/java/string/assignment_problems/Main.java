import java.util.Scanner;

// Define the BookInventory class to keep all related attributes bundled together
class BookInventory {
    // Fields to store individual book details
    private String title;
    private String author;
    private int copiesAvailable;

    // Constructor to initialize all three fields
    public BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    // Instance method to print a single formatted book record
    public void printEntry() {
        System.out.println(this.title + " by " + this.author + " - " + this.copiesAvailable + " copies available");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create an array to hold four BookInventory objects
        BookInventory[] libraryCatalog = new BookInventory[4];

        // Process and read exactly 4 book metadata logs from the console
        for (int i = 0; i < 4; i++) {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Split the input line by commas and trim any accidental spaces
                String[] details = line.split(",");
                
                String title = details[0].trim();
                String author = details[1].trim();
                int copiesAvailable = Integer.parseInt(details[2].trim());

                // Instantiate the object and safely map it directly into our array slot
                libraryCatalog[i] = new BookInventory(title, author, copiesAvailable);
            }
        }

        // Iterate through the array block inside a loop and print each object's state
        for (BookInventory book : libraryCatalog) {
            if (book != null) {
                book.printEntry();
            }
        }

        scanner.close();
    }
}
