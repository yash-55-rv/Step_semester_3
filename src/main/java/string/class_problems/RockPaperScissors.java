import java.util.Random;

public class RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        int N = 5;
        String[] options = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        
        // Predefined list of student/player moves for the live demo
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        
        String[] computerMoves = new String[N];
        String[] results = new String[N];
        
        int wins = 0, losses = 0, draws = 0;

        System.out.println("--- Playing Match ---");
        for (int i = 0; i < N; i++) {
            // Generate computer's move randomly
            computerMoves[i] = options[random.nextInt(3)];
            
            // Determine result
            results[i] = playRound(playerMoves[i], computerMoves[i]);
            System.out.println("Round " + (i + 1) + " — Player: " + playerMoves[i] + ", Computer: " + computerMoves[i] + " -> " + results[i]);
            
            // Track statistics
            if (results[i].equals("Player Wins")) wins++;
            else if (results[i].equals("Computer Wins")) losses++;
            else draws++;
        }

        // Print Summary Table
        System.out.println("\nFinal Summary (after " + N + " rounds)");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-8s | %-12s | %-13s | %-12s\n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < N; i++) {
            System.out.printf("Round %-3d | %-12s | %-13s | %-12s\n", (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }
        System.out.println("---------------------------------------------------------");
        
        double winPercentage = ((double) wins / N) * 100;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%\n", wins, losses, draws, winPercentage);
    }
}
