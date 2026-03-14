package Sorting_05;
import java.util.Scanner;
public class SelectionSort {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of elements");
        int n = in.nextInt();
        int arr[] = new int[n];
        int min, temp = 0;
        System.err.println("Enter the elements in the array");
        for(int i = 0 ; i < n ; i ++){
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        System.err.println("The sorted array is :");
        for(int i = 0 ; i < n ; i ++){
            System.out.print(arr[i]+" ");
        }
        in.close();
    }
}