import java.util.Arrays;

public class FantasyMultiplier {

    // Modifies the caller's array directly and returns nothing (void)
    static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        // Enforce boundary check to prevent crashes (though constraints say indices are always valid)
        if (playerScores == null || captainIndex < 0 || viceCaptainIndex < 0 || 
            captainIndex >= playerScores.length || viceCaptainIndex >= playerScores.length) {
            return;
        }

        // Apply 2x multiplier to Captain's index
        playerScores[captainIndex] *= 2.0;

        // Apply 1.5x multiplier to Vice-Captain's index
        playerScores[viceCaptainIndex] *= 1.5;
    }

    public static void main(String[] args) {
        // Test array matching the example input
        double[] scores = {40, 55, 30, 62};
        
        System.out.println("Before: " + Arrays.toString(scores));
        
        // Execute multiplier method
        applyMultipliers(scores, 1, 3);
        
        // Output matches the required example format
        System.out.println("After:  " + Arrays.toString(scores)); 
    }
}
