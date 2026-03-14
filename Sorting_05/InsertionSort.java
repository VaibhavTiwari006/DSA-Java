package Sorting_05;
import java.util.Scanner;
public class InsertionSort{
    public static void main (String args[]){
        Scanner in = new Scanner (System.in);
        System.out.println("Enter number of elements");
        int n = in.nextInt();
        int a[] = new int[n];
        int j, temp;
        System.err.println("Enter the elements in the array");
        for(int i = 0 ; i < n ; i ++){
            a[i] = in.nextInt();
        }
        for(int i = 1 ; i< n; i++){
            temp = a[i];
            j=i;
            while(j > 0 && a[j-1] > temp){
                a[j] = a[j-1];
                j = j-1;
            }
            a[j] = temp;
        }
    
    System.err.println("The sorted array is :");
        for(int i = 0 ; i < n ; i ++){
            System.out.print(a[i]+" ");
        }
        in.close();
}
}