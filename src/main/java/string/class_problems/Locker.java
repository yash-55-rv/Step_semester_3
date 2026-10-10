public class Locker {
    // Requirements: Private combination code, final locker number
    private String combinationCode;
    private final int lockerNumber;

    // Constructor to set fixed locker number and initial code
    public Locker(int lockerNumber, String initialCode) {
        if (initialCode == null || initialCode.isEmpty()) {
            throw new IllegalArgumentException("Initial combination code cannot be empty.");
        }
        this.lockerNumber = lockerNumber;
        this.combinationCode = initialCode;
    }

    // Requirement: Change code only if current code is entered correctly first
    public void changeCode(String currentCode, String newCode) {
        if (newCode == null || newCode.isEmpty()) {
            System.out.println("Rejected: New combination code cannot be empty.");
            return;
        }

        // Hint 2: Check the old code first before modifying the internal state
        if (!this.combinationCode.equals(currentCode)) {
            System.out.println("Rejected: The current code you entered is incorrect. Code stays unchanged.");
            return;
        }

        // If the check passes, update the code
        this.combinationCode = newCode;
        System.out.println("Success: Locker " + this.lockerNumber + " combination code changed.");
    }

    // Read-only access to the locker number
    public int getLockerNumber() {
        return this.lockerNumber;
    }

    // Hint 1: Notice there is NO "public String getCombinationCode()" method out of habit!

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String[] args) {
        // Create locker 101 with initial code "1234"
        Locker l = new Locker(101, "1234");
        System.out.println("Locker number " + l.getLockerNumber() + " created.");

        // 1. Attempt to change code with the CORRECT current code -> succeeds
        l.changeCode("1234", "5678");

        // 2. Attempt to change code with an INCORRECT current code -> rejected
        l.changeCode("0000", "9999");
        
        // Note: Outside classes can verify the locker number but can never extract the secret code string!
    }
}
