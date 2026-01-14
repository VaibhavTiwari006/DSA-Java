package Stack_04;

import java.util.*;

public class SlidingWindowMax {
    // inpput 2 9 3 8 1 7 12 6 4
    // output 9 9 8 12 12 12 12 12
    // take 4 elements print the max
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the value of sliding constant ");
        int k = in.nextInt();
        System.out.println("Enter size of the array ");
        int n = in.nextInt();
        int arr[] = new int[n];
        System.out.println("enter the elements into the array ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i <= n - k; i++) {

            int max = arr[i]; 

            for (int j = i; j < i + k; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            System.out.print(max + " ");
        }
        in.close();
    }
}