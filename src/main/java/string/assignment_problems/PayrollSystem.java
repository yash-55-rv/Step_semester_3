import java.util.Scanner;

// Define the PayrollAccount class with proper data encapsulation
class PayrollAccount {
    // Private fields protect data from direct external tampering
    private double basicSalary;
    private double bonus;

    // Constructor to initialize basic salary safely
    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: Basic salary cannot be negative. Setting to 0.0.");
            this.basicSalary = 0.0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0.0; // Initially, bonus is set to zero
    }

    // Public method to safely add bonus
    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Credit rejected: Amount must be greater than zero.");
        } else {
            this.bonus += amount;
            System.out.println("Bonus credited: Rs " + this.bonus);
        }
    }

    // Public method to deduct tax safely
    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Deduction rejected: Invalid tax percentage.");
        } else {
            this.basicSalary -= (this.basicSalary * (percent / 100.0));
            System.out.println("Tax deducted: " + (int)percent + "%");
        }
    }

    // Read-only calculation for net salary
    public double getNetSalary() {
        return this.basicSalary + this.bonus;
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Parse and initialize base salary
        String salaryLine = scanner.nextLine();
        double initialSalary = parseValue(salaryLine);
        PayrollAccount account = new PayrollAccount(initialSalary);

        // 2. Parse and credit bonus
        String bonusLine = scanner.nextLine();
        double bonusAmount = parseValue(bonusLine);
        account.creditBonus(bonusAmount);

        // 3. Parse and apply tax deduction
        String taxLine = scanner.nextLine();
        double taxPercent = parseValue(taxLine);
        account.deductTax(taxPercent);

        // 4. Output final calculated net salary
        System.out.println("Net salary: Rs " + account.getNetSalary());

        scanner.close();
    }

    // Helper method to isolate numeric inputs from textual prefixes
    private static double parseValue(String input) {
        String cleanInput = input.replaceAll("[a-zA-Z\\s=\\(\\)]", "");
        return Double.parseDouble(cleanInput);
    }
}
