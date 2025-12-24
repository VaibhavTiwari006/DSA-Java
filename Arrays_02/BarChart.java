package Arrays_02;
import java.util.*;
public class BarChart {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i;
        System.out.println("Enter the number of elements");
        int n = in.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter the elements");
        for (i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int max = arr[0];
        for (i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        for (int floor = max; floor >= 1; floor--) {
            for (i = 0; i < arr.length; i++) {
                if (arr[i] >= floor) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        in.close();
    }
}