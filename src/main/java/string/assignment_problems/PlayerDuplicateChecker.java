import java.util.Scanner;

public class PlayerDuplicateChecker {

    // Method satisfying the exact problem requirements
    static String findDuplicatePick(String[] playerNames) {
        if (playerNames == null || playerNames.length == 0) {
            return "No Duplicates Found";
        }

        // Outer loop selects the current player name to inspect
        for (int i = 0; i < playerNames.length; i++) {
            // Inner loop compares it only against the names that come AFTER it (following the hint)
            for (int j = i + 1; j < playerNames.length; j++) {
                // Perform case-sensitive exact string matching
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i];
                }
            }
        }

        // If no pairs matched after scanning the entire array
        return "No Duplicates Found";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of players in the lineup (up to 11): ");
        int totalPlayers = scanner.nextInt();
        scanner.nextLine(); // Crucial: Consumes the newline trailing the integer

        // Initialize array based on user input size
        String[] lineup = new String[totalPlayers];

        System.out.println("Enter the player names:");
        for (int i = 0; i < totalPlayers; i++) {
            System.out.print("Player " + (i + 1) + ": ");
            lineup[i] = scanner.nextLine(); // nextLine allows spaces in names (e.g., "Hardik Pandya")
        }

        // Execute search and print exact output structure matching requirements
        String result = findDuplicatePick(lineup);
        System.out.println("\n" + result);

        scanner.close();
    }
}
