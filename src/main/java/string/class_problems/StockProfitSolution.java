import java.util.Scanner;

public class StockProfitSolution {

    // Method to calculate the maximum profit in a single pass
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // Initialize the lowest price seen so far to the first day's price
        int minPrice = prices[0];
        // Running record of the largest profit seen across the array
        int maxProfit = 0;

        // Walk through the array once from left to right
        for (int i = 1; i < prices.length; i++) {
            // Track the lowest price seen so far
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // Calculate profit if sold today and update maxProfit if it's the largest seen
                int currentProfit = prices[i] - minPrice;
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                }
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== L2. Best Time to Buy and Sell Stock ===");
        
        // Take size of the stock prices array
        System.out.print("Enter the number of days: ");
        int days = scanner.nextInt();
        
        // Take daily stock prices
        int[] prices = new int[days];
        System.out.print("Enter " + days + " daily stock prices separated by spaces: ");
        for (int i = 0; i < days; i++) {
            prices[i] = scanner.nextInt();
        }
        
        // Process and display the output maximum profit
        int result = maxProfit(prices);
        System.out.println("Maximum Profit: " + result);
        
        scanner.close();
    }
}
