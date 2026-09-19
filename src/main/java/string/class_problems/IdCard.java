import java.util.Scanner;

class IdCard {
    String name;
    int booksIssued;

    // Constructor to initialize fields
    public IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create the original IdCard object for Ravi
        IdCard ravi = new IdCard("Ravi", 0);
        
        // Point a second variable to the exact same memory location
        IdCard duplicate = ravi;
        
        // Mutate the object state using the second reference pointer
        duplicate.booksIssued = 3;
        
        // Create an entirely separate object instance with identical data
        IdCard separate = new IdCard("Ravi", 3);

        // Verification Output Statements
        System.out.println("Ravi's booksIssued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicate == ravi));
        System.out.println("separate == ravi: " + (separate == ravi));
    }
}
