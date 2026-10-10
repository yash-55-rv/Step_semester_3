public class PasswordChecker {
    // Requirements: Private and immutable password storage
    private final String password;

    // Constructor accepts the password once and stores it permanently
    public PasswordChecker(String password) {
        if (password == null) {
            this.password = ""; // Safeguard against null input
        } else {
            this.password = password;
        }
    }

    // Requirement: Compute and return strength without exposing the raw password
    public String getStrength() {
        int length = this.password.length(); // Hint 1

        // Applying the requested length-based rules
        if (length < 6) {
            return "Weak";
        } else if (length <= 9) {
            return "Medium";
        } else {
            return "Strong";
        }
    }

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String args) {
        // Test 1: 4-character password -> "Weak"
        PasswordChecker pc1 = new PasswordChecker("abcd");
        System.out.println("Password 'abcd' strength: " + pc1.getStrength()); 

        // Test 2: 8-character password -> "Medium"
        PasswordChecker pc2 = new PasswordChecker("abcdefgh");
        System.out.println("Password 'abcdefgh' strength: " + pc2.getStrength()); 

        // Test 3: 12-character password -> "Strong"
        PasswordChecker pc3 = new PasswordChecker("abcdefghijkl");
        System.out.println("Password 'abcdefghijkl' strength: " + pc3.getStrength()); 
        
        // Note: There is absolutely no method (like pc1.getPassword()) to retrieve the raw string!
    }
}
