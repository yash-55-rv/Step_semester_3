import java.util.Scanner;
import java.util.Arrays;
public class Problem1 {
    public void curveScores(int[] arr, int bonus){
        for(int i = 0; i < arr.length; i++){
            arr[i] += bonus;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.print("Enter the bonus to be added to each score: ");
        int bonus = scanner.nextInt();
        Problem1 problem = new Problem1();
        problem.curveScores(arr, bonus);
        System.out.println(Arrays.toString(arr));
    }
}
