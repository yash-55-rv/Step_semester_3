import java.util.Arrays;

public class Player implements Comparable<Player> {
    // Encapsulation: private attributes with controlled access
    private String name;
    private int matchesPlayed;
    private double battingAverage; // Acts as fantasy points metric for ranking
    private boolean injured;

    // Threshold constants for draft logic
    private static final int ESTABLISHED_MATCHES_THRESHOLD = 10;
    private static final int NEW_PLAYER_MATCHES_THRESHOLD = 5;

    // Constructor
    public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    // Overloaded static method 1: Matches-played-only rule for established players
    public static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= ESTABLISHED_MATCHES_THRESHOLD;
    }

    // Overloaded static method 2: Combined matches-and-fitness rule for newer players
    public static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= NEW_PLAYER_MATCHES_THRESHOLD && !injured;
    }

    // Implementing Comparable to sort by battingAverage descending
    @Override
    public int compareTo(Player other) {
        // For descending order, compare 'other' to 'this'
        return Double.compare(other.battingAverage, this.battingAverage);
    }

    // Engine method to filter, sort, and format the output
    public static String draftAndRank(Player[] players) {
        if (players == null || players.length == 0) {
            return "";
        }

        // Phase 1: Filter draftable players into a temporary array
        Player[] tempDraftable = new Player[players.length];
        int count = 0;

        for (Player p : players) {
            // Rule A: Qualifies on sheer experience alone
            // Rule B: Qualifies on combined modest experience AND fitness
            if (isDraftable(p.matchesPlayed) || isDraftable(p.matchesPlayed, p.injured)) {
                tempDraftable[count++] = p;
            }
        }

        // Phase 2: Copy to a perfectly sized array and sort via Standard Library
        Player[] draftable = Arrays.copyOf(tempDraftable, count);
        Arrays.sort(draftable); // Automatically invokes compareTo(Player other)

        // Phase 3: Format output string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < draftable.length; i++) {
            result.append(i + 1).append(". ").append(draftable[i].name);
            if (i < draftable.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }
}
