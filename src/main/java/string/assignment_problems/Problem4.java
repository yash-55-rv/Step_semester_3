import java.util.Scanner;

public class WarehouseBalancer {

    public static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;
        
        int highestQty = Integer.MIN_VALUE;
        String highestSection = "";
        int highestItemNum = -1;

        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            if (sectionA[i] > highestQty) {
                highestQty = sectionA[i];
                highestSection = "Section A";
                highestItemNum = i + 1;
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
            if (sectionB[i] > highestQty) {
                highestQty = sectionB[i];
                highestSection = "Section B";
                highestItemNum = i + 1;
            }
        }

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB + " | Status: " + status + 
                           " | Highest Quantity: " + highestQty + " (" + highestSection + ", Item " + highestItemNum + ")");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of item categories: ");
        int size = scanner.nextInt();
        
        int[] sectionA = new int[size];
        int[] sectionB = new int[size];
        
        System.out.println("Enter quantities for Section A:");
        for (int i = 0; i < size; i++) {
            sectionA[i] = scanner.nextInt();
        }
        
        System.out.println("Enter quantities for Section B:");
        for (int i = 0; i < size; i++) {
            sectionB[i] = scanner.nextInt();
        }
        
        analyzeInventory(sectionA, sectionB);
        scanner.close();
    }
}
