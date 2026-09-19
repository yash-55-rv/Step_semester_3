import java.util.Arrays;
import java.util.Scanner;

class Candidate implements Comparable<Candidate> {
    private String name;
    private double cgpa;
    private int codingScore;
    private double compositeScore;

    // Constructor & Encapsulation
    public Candidate(String name, double cgpa, int codingScore) {
        this.name = name;
        this.cgpa = cgpa;
        this.codingScore = codingScore;
        // Composite score formula matches example math: (CGPA * 10) + codingScore
        this.compositeScore = (cgpa * 10) + codingScore;
    }

    public String getName() { return name; }
    public double getCgpa() { return cgpa; }
    public int getCodingScore() { return codingScore; }
    public double getCompositeScore() { return compositeScore; }

    // Method Overloading - Quick CGPA check (e.g., direct cutoff at 7.0)
    public static boolean isEligible(double cgpa) {
        return cgpa >= 7.0;
    }

    // Method Overloading - Borderline backup check (e.g., lower CGPA bar of 6.5, but needs solid code score)
    public static boolean isEligible(double cgpa, int codingScore) {
        return cgpa >= 6.5 && codingScore >= 50;
    }

    // Sort Descending by implementing Comparable
    @Override
    public int compareTo(Candidate other) {
        // Multiplied by -1 for descending order ranking
        return Double.compare(other.compositeScore, this.compositeScore);
    }
}

public class PlacementEngine {

    public static String shortlistAndRank(Candidate[] candidates) {
        if (candidates == null || candidates.length == 0) {
            return "No candidates to evaluate.";
        }

        // Temporary tracking array to hold shortlisted candidates
        Candidate[] tempShortlist = new Candidate[candidates.length];
        int count = 0;

        // Process eligibility using overloaded static filters
        for (Candidate c : candidates) {
            if (Candidate.isEligible(c.getCgpa()) || Candidate.isEligible(c.getCgpa(), c.getCodingScore())) {
                tempShortlist[count++] = c;
            }
        }

        if (count == 0) {
            return "No candidates shortlisted.";
        }

        // Trim array to exact matching structure
        Candidate[] shortlisted = Arrays.copyOf(tempShortlist, count);

        // Sorting rule handled natively by Arrays.sort utilizing Candidate's compareTo overrides
        Arrays.sort(shortlisted);

        // Build string output formatting to match requirements perfectly
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shortlisted.length; i++) {
            sb.append((i + 1)).append(". ")
              .append(shortlisted[i].getName())
              .append(" (").append(shortlisted[i].getCompositeScore()).append(")");
            
            if (i < shortlisted.length - 1) {
                sb.append(" | ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // Hardcoded testing array matching example scenarios exactly
        Candidate[] batch = {
            new Candidate("Aisha", 8.2, 40),
            new Candidate("Rohit", 6.8, 65),
            new Candidate("Meena", 6.0, 90),
            new Candidate("Karan", 7.5, 20)
        };

        String rankingResult = shortlistAndRank(batch);
        System.out.println(rankingResult);
    }
}
