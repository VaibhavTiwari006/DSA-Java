package Sorting_05;
import java.util.Scanner;
public class BubbleSort {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter number of elements");
        int n = in.nextInt();

        int a[] = new int[n];
        int temp;

        System.out.println("Enter the elements in the array");
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        System.out.println("The sorted array is:");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }

        in.close();
    }
}
