import java.util.Scanner;

// ========================================================
// ABSTRACT BASE CLASS (Open for Extension, Closed for Modification)
// ========================================================
abstract class GardenPlot {
    private final String owner;
    private final String shapeName;

    public GardenPlot(String owner, String shapeName) {
        this.owner = owner;
        this.shapeName = shapeName;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getShapeName() {
        return this.shapeName;
    }

    // Polymorphic method: Each new shape implements its own calculation logic
    public abstract double calculateArea();
}

// ========================================================
// CONCRETE PLOT IMPLEMENTATIONS (Encapsulating Unique Math Formulas)
// ========================================================
class CirclePlot extends GardenPlot {
    private final double radius;

    public CirclePlot(String owner, double radius) {
        super(owner, "CIRCLE");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        // Circle area = π × radius × radius
        return Math.PI * this.radius * this.radius;
    }
}

class RectanglePlot extends GardenPlot {
    private final double length;
    private final double width;

    public RectanglePlot(String owner, double length, double width) {
        super(owner, "RECTANGLE");
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        // Rectangle area = length × width
        return this.length * this.width;
    }
}

class TrianglePlot extends GardenPlot {
    private final double base;
    private final double height;

    public TrianglePlot(String owner, double base, double height) {
        super(owner, "TRIANGLE");
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        // Triangle area = 1/2 × base × height
        return 0.5 * this.base * this.height;
    }
}

// ========================================================
// MAIN REPORT GENERATOR
// ========================================================
public class GardenReportSystem {

    // Factory helper method to handle plot creation
    private static GardenPlot createPlot(String shape, String owner, Scanner scanner) {
        switch (shape.toUpperCase()) {
            case "CIRCLE":
                double radius = scanner.nextDouble();
                return new CirclePlot(owner, radius);
            case "RECTANGLE":
                double length = scanner.nextDouble();
                double width = scanner.nextDouble();
                return new RectanglePlot(owner, length, width);
            case "TRIANGLE":
                double base = scanner.nextDouble();
                double height = scanner.nextDouble();
                return new TrianglePlot(owner, base, height);
            default:
                throw new IllegalArgumentException("Unknown plot shape: " + shape);
        }
    }

    public static void main(String args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }

        int n = scanner.nextInt();
        double totalArea = 0.0;

        for (int i = 0; i < n; i++) {
            if (!scanner.hasNext()) break;

            String shape = scanner.next();
            String owner = scanner.next();

            // Instantiate plot type dynamically
            GardenPlot plot = createPlot(shape, owner, scanner);

            // Polymorphic computation: This line stays identical even if 10 new shapes are added!
            double area = plot.calculateArea();

            // Print output matching requirements
            System.out.printf("%s (%s): %.2f\n", plot.getOwner(), plot.getShapeName(), area);

            totalArea += area;
        }

        // Print aggregate report summaries
        System.out.printf("Total Area: %.2f\n", totalArea);
        scanner.close();
    }
}
