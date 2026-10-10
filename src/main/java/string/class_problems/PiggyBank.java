public class PiggyBank {
    // Requirements: Private savings amount, final fixed ID
    private double savings;
    private final String id; // Hint 2: The final keyword locks this in place

    // Constructor to set fixed ID and initialize savings to 0
    public PiggyBank(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Piggy Bank ID cannot be empty.");
        }
        this.id = id;
        this.savings = 0.0; // Requirement: Starts at 0 savings
    }

    // Requirement: Add exactly that amount to savings
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount! Must be greater than 0.");
            return;
        }
        this.savings += amount;
    }

    // Requirement: Reject withdrawals larger than current savings
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount! Must be greater than 0.");
            return;
        }
        
        // Safety check to reject overdrafts
        if (amount > this.savings) {
            System.out.println("Transaction Rejected! Insufficient funds. Savings stay at: " + this.savings);
            return;
        }
        
        this.savings -= amount;
    }

    // Requirement: Read-only check for current savings (no setter)
    public double getSavings() {
        return this.savings;
    }

    // Read-only check for the bank ID
    public String getId() {
        return this.id;
    }

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String[] args) {
        // Create a new piggy bank
        PiggyBank pb = new PiggyBank("PB-1");
        System.out.println("Piggy Bank " + pb.getId() + " initialized with: $" + pb.getSavings());

        // Deposit 100 -> balance should be 100
        pb.deposit(100);
        System.out.println("After depositing 100: $" + pb.getSavings()); // Output: 100.0

        // Withdraw 30 -> balance should be 70
        pb.withdraw(30);
        System.out.println("After withdrawing 30: $" + pb.getSavings()); // Output: 70.0

        // Withdraw 500 -> should be rejected, balance stays 70
        pb.withdraw(500);
        System.out.println("Final balance check: $" + pb.getSavings());   // Output: 70.0
        
        // Note: Hint 1 applies here! By keeping savings private, no one can execute "pb.savings = -9999;"
    }
}
