import java.util.Scanner;

// ========================================================
// POLYMORPHIC INTERFACE
// ========================================================
interface PaymentMethod {
    double calculateAdjustedAmount(double amount);
}

// ========================================================
// CONCRETE PAYMENT IMPLEMENTATIONS (Encapsulating Fee Logic)
// ========================================================
class CardPayment implements PaymentMethod {
    @Override
    public double calculateAdjustedAmount(double amount) {
        return amount * 1.02; // 2% processing fee
    }
}

class WalletPayment implements PaymentMethod {
    @Override
    public double calculateAdjustedAmount(double amount) {
        return amount * 1.01; // 1% processing fee
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public double calculateAdjustedAmount(double amount) {
        return amount; // No processing fee
    }
}

// ========================================================
// MAIN SYSTEM PROCESSOR
// ========================================================
public class PaymentSystem {

    // Factory method to obtain the correct strategy without type-checking inside the loop
    private static PaymentMethod getPaymentMethod(String type) {
        switch (type.toUpperCase()) {
            case "CARD":
                return new CardPayment();
            case "WALLET":
                return new WalletPayment();
            case "BANKTRANSFER":
                return new BankTransferPayment();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        
        double grandTotal = 0.0;

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            double amount = scanner.nextDouble();

            // Uniformly handle transactions polymorphically
            PaymentMethod method = getPaymentMethod(type);
            double adjustedAmount = method.calculateAdjustedAmount(amount);

            // Print the individual transaction result
            System.out.printf("%s: %.2f\n", type.toUpperCase(), adjustedAmount);

            // Accumulate grand total
            grandTotal += adjustedAmount;
        }

        // Print final summary
        System.out.printf("Total: %.2f\n", grandTotal);
        scanner.close();
    }
}
