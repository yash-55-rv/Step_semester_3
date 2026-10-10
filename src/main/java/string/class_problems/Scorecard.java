public class Scorecard {
    // Requirements: Private array for tracking results, fixed capacity
    private final boolean[] results;
    private int answersRecorded;

    // Constructor fixes the total number of questions
    public Scorecard(int totalQuestions) {
        if (totalQuestions <= 0) {
            throw new IllegalArgumentException("Total questions must be greater than 0.");
        }
        this.results = new boolean[totalQuestions];
        this.answersRecorded = 0; // Hint 2: Tracks the index for the next answer
    }

    // Requirement: Record the next answer's result one at a time
    public void recordAnswer(boolean isCorrect) {
        // Safety check: Reject answers if we exceed the fixed question count
        if (this.answersRecorded >= this.results.length) {
            System.out.println("All questions have already been answered! Cannot record: " + isCorrect);
            return;
        }

        this.results[this.answersRecorded] = isCorrect;
        this.answersRecorded++;
    }

    // Requirement: Compute and expose ONLY the total score (count of true values)
    public int getScore() {
        int correctCount = 0;
        for (int i = 0; i < this.answersRecorded; i++) {
            if (this.results[i]) {
                correctCount++;
            }
        }
        return correctCount;
    }

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String[] args) {
        // Create a scorecard for a 4-question quiz
        Scorecard sc = new Scorecard(4);

        // Record answers: correct, correct, wrong, correct
        sc.recordAnswer(true);
        sc.recordAnswer(true);
        sc.recordAnswer(false);
        sc.recordAnswer(true);

        // Verify the score matches the expected behavior
        System.out.println("Total Score: " + sc.getScore()); // Output: 3

        // Verify rejection by trying to add a 5th answer to a 4-question quiz
        sc.recordAnswer(false); // Output: All questions have already been answered!
        
        // Note: Hint 1 applies here! The results array stays hidden inside the object.
    }
}
