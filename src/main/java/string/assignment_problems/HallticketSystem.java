import java.util.Scanner;

class HallTicket {
    String studentName;
    int seatNumber;

    // Constructor to initialize fields
    public HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}

public class HallticketSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Take initial inputs for Priya's ticket
        System.out.print("Enter initial seat number for Priya: ");
        int initialSeat = scanner.nextInt();
        HallTicket priya = new HallTicket("Priya", initialSeat);
        
        // 2. Assign a second variable to point at that same object
        HallTicket copy = priya;
        
        // 3. Take user input to change the seatNumber through the second variable
        System.out.print("Enter new seat number to update via copy: ");
        int newSeat = scanner.nextInt();
        copy.seatNumber = newSeat;
        
        // 4. Print the field's value as seen through the first variable
        System.out.println("\nPriya's seatNumber (via first variable): " + priya.seatNumber);
        
        // Print whether the two variables are == to each other
        System.out.println("copy == priya: " + (copy == priya));
        
        // 5. Take inputs to create a third, separate HallTicket object
        System.out.print("\nEnter student name for separate ticket: ");
        String sepName = scanner.next();
        System.out.print("Enter seat number for separate ticket: ");
        int sepSeat = scanner.nextInt();
        
        HallTicket separate = new HallTicket(sepName, sepSeat);
        
        // Print whether it is == to the first
        System.out.println("separate == priya: " + (separate == priya));
        
        scanner.close();
    }
}
