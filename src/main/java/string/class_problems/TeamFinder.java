import java.util.Scanner;

public class TeamFinder {

    public static String findDuplicateTeam(String[] teamNames) {
        if (teamNames == null || teamNames.length == 0) {
            return "No Duplicates Found";
        }

        // Outer loop selects the current team name
        for (int i = 0; i < teamNames.length; i++) {
            // Inner loop compares it only against the names that come AFTER it
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i];
                }
            }
        }
        return "No Duplicates Found";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the number of teams
        System.out.print("Enter the number of registered teams: ");
        int totalTeams = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character

        // Initialize the array based on user input size
        String[] teams = new String[totalTeams];

        // Collect all team names
        System.out.println("Enter the team names one by one:");
        for (int i = 0; i < totalTeams; i++) {
            System.out.print("Team " + (i + 1) + ": ");
            teams[i] = scanner.nextLine();
        }

        // Find and print the result
        String result = findDuplicateTeam(teams);
        System.out.println("\nResult -> " + result);

        scanner.close();
    }
}
