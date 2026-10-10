public class AttendanceSheet {
    // Requirements: Private array for student tracking, fixed capacity
    private final String[] presentStudents;
    private int presentCount;

    // Constructor to set the fixed maximum class size
    public AttendanceSheet(int maxClassSize) {
        if (maxClassSize <= 0) {
            throw new IllegalArgumentException("Class size must be greater than 0.");
        }
        this.presentStudents = new String[maxClassSize];
        this.presentCount = 0;
    }

    // Requirement: Mark a student present, preventing duplicates (Hint 1)
    public void markPresent(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            return; // Ignore empty inputs
        }

        // Hint 1: Check if the student is already recorded before adding
        if (this.isPresent(studentName)) {
            System.out.println(studentName + " is already marked present.");
            return;
        }

        // Safety check to ensure we don't exceed the array limits
        if (this.presentCount >= this.presentStudents.length) {
            System.out.println("Attendance sheet is full! Cannot add: " + studentName);
            return;
        }

        this.presentStudents[this.presentCount] = studentName;
        this.presentCount++;
    }

    // Requirement: Yes/No lookup loop to verify presence (Hint 2)
    public boolean isPresent(String studentName) {
        if (studentName == null) return false;

        // Loop through the private array internally up to the count added so far
        for (int i = 0; i < this.presentCount; i++) {
            if (this.presentStudents[i].equalsIgnoreCase(studentName.trim())) {
                return true;
            }
        }
        return false;
    }

    // Requirement: Read-only count of unique students present
    public int getPresentCount() {
        return this.presentCount;
    }

    // Hint 2: Notice there is NO "public String[] getPresentStudents()" method out of habit!

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String[] args) {
        // Create an attendance sheet for up to 30 students
        AttendanceSheet sheet = new AttendanceSheet(30);

        // Mark students present (including a duplicate attempt)
        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana"); // Output: Ana is already marked present.

        // Verify that the count ignores duplicates
        System.out.println("Total Present Count: " + sheet.getPresentCount()); // Output: 2

        // Verify the yes/no lookups work perfectly
        System.out.println("Is Ben present? " + sheet.isPresent("Ben"));   // Output: true
        System.out.println("Is Chen present? " + sheet.isPresent("Chen")); // Output: false
        
        // Note: The entire string array remains completely locked away inside the object instance.
    }
}
