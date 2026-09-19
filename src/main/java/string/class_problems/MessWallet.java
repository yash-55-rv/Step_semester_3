import java.util.Scanner;

class MessWallet {
    private double balance;

    public MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            System.out.println("Warning: Opening balance cannot be negative. Setting balance to 0.0.");
            this.balance = 0.0;
        } else {
            this.balance = openingBalance;
        }
    }

    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: Amount must be greater than zero.");
        } else {
            this.balance += amount;
            System.out.println("Balance after top-up: " + this.balance);
        }
    }

    public void deduct(double amount) {
        if (amount > this.balance) {
            System.out.println("Deduct rejected: insufficient balance");
        } else {
            this.balance -= amount;
        }
    }

    public double getBalance() {
        return this.balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Read opening balance
        // Input format expected: opening = 500 OR just the number 500
        String openingLine = scanner.nextLine();
        double openingBalance = parseValue(openingLine);
        MessWallet wallet = new MessWallet(openingBalance);

        // 2. Read topUp amount
        // Input format expected: topUp(200) OR just the number 200
        String topUpLine = scanner.nextLine();
        double topUpAmount = parseValue(topUpLine);
        wallet.topUp(topUpAmount);

        // 3. Read deduct amount
        // Input format expected: deduct(1000) OR just the number 1000
        String deductLine = scanner.nextLine();
        double deductAmount = parseValue(deductLine);
        wallet.deduct(deductAmount);

        // 4. Output final balance
        System.out.println("Final balance: " + wallet.getBalance());

        scanner.close();
    }

    // Helper method to extract numbers from various input formats like "opening = 500" or "topUp(200)"
    private static double parseValue(String input) {
        // Strip out alphabets, spaces, brackets, and equals signs, leaving only numbers and decimals
        String cleanInput = input.replaceAll("[a-zA-Z\\s=\\(\\)]", "");
        return Double.parseDouble(cleanInput);
    }
}
