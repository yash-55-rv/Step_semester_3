public class TrafficLight {
    // Requirements: Private color, final fixed ID
    private String currentColor;
    private final String id;

    // Constructor to set fixed ID and initialize color to RED
    public TrafficLight(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Traffic light ID cannot be empty.");
        }
        this.id = id;
        this.currentColor = "RED"; // Requirement: Starts on RED
    }

    // Requirement: Strictly cycle colors in order: RED -> GREEN -> YELLOW -> RED (Hint 1)
    public void next() {
        if (this.currentColor.equals("RED")) {
            this.currentColor = "GREEN";
        } else if (this.currentColor.equals("GREEN")) {
            this.currentColor = "YELLOW";
        } else if (this.currentColor.equals("YELLOW")) {
            this.currentColor = "RED";
        }
    }

    // Requirement: Read-only access to current color
    public String getColor() {
        return this.currentColor;
    }

    // Read-only access to the ID
    public String getId() {
        return this.id;
    }

    // ========================================================
    // THE MAIN METHOD (Tests the expected behavior)
    // ========================================================
    public static void main(String[] args) {
        // Create a new traffic light
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println("Light " + t.getId() + " starts at: " + t.getColor()); // Output: RED

        // Cycle 1: RED -> GREEN
        t.next();
        System.out.println("After 1st next(): " + t.getColor()); // Output: GREEN

        // Cycle 2: GREEN -> YELLOW
        t.next();
        System.out.println("After 2nd next(): " + t.getColor()); // Output: YELLOW

        // Cycle 3: YELLOW -> RED
        t.next();
        System.out.println("After 3rd next(): " + t.getColor()); // Output: RED

        // Cycle 4: RED -> GREEN
        t.next();
        System.out.println("After 4th next(): " + t.getColor()); // Output: GREEN
        
        // Note: No setter methods exist to force the light straight to "YELLOW"
    }
}
