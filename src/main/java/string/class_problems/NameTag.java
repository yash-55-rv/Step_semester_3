public class NameTag {
    // Requirements: Final fields to guarantee immutability
    private final String firstName;
    private final char lastNameInitial;

    // Constructor splits the name immediately upon object creation (Hint 2)
    public NameTag(String fullName) {
        if (fullName == null || !fullName.contains(" ")) {
            throw new IllegalArgumentException("Full name must contain a first and last name separated by a space.");
        }

        // Hint 1: Split the text into parts wherever a space appears
        String[] parts = fullName.split(" ");
        
        this.firstName = parts[0];
        this.lastNameInitial = parts[1].charAt(0); // Store just the first letter of the last name
    }

    // Requirement: Return the built nickname
    public String getNickname() {
        return this.firstName + " " + this.lastNameInitial + ".";
    }

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String[] args) {
        // Create a NameTag object
        NameTag tag = new NameTag("Maria Gomez");
        
        // Verify the expected output
        System.out.println("Nickname: " + tag.getNickname()); // Output: Maria G.

        // Test a second, completely separate object
        NameTag tag2 = new NameTag("Alex Smith");
        System.out.println("Nickname 2: " + tag2.getNickname()); // Output: Alex S.
        
        // Note: Because the fields are final, there are no setters to change the name later!
    }
}
