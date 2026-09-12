import java.util.Arrays;
import java.util.Scanner;

public class Problem3 {

    static int[] findTopThreeScores(int[] scores) {

        int[] top = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        for (int score : scores) {

            if (score > top[0]) {
                top[2] = top[1];
                top[1] = top[0];
                top[0] = score;
            }
            else if (score > top[1]) {
                top[2] = top[1];
                top[1] = score;
            }
            else if (score > top[2]) {
                top[2] = score;
            }
        }

        return top;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of scores: ");
        int n = sc.nextInt();

        int[] scores = new int[n];

        System.out.println("Enter the scores:");
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        int[] result = findTopThreeScores(scores);

        System.out.println("Top 3 Scores: " + Arrays.toString(result));

        sc.close();
    }
}
