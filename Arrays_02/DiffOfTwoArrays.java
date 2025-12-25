package Arrays_02;
import java.util.Scanner;
public class DiffOfTwoArrays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of elements in the array");
        int n1 = in.nextInt();
        int arr1[] = new int[n1];
         System.out.println("Enter the elements in first array");
        for (int i = 0; i < n1; i++) { 
            arr1[i] = in.nextInt();
        }
        System.out.println("Enter number of elements in the second array");
        int n2 = in.nextInt();
         int arr2[] = new int[n2];
         System.out.println("Enter the elements in second array");
        for (int i = 0; i < n2; i++) { 
            arr2[i] = in.nextInt();
        }
        int[]diff = new int[n2];
       //int carry = 0;
        int i = arr2.length - 1;
        int j = arr1.length - 1;
        int k = diff.length - 1;
        int borrow =0;
        while (k>=0) {         
            int d = arr2[i] - borrow;
            int a1 = (j>=0 ? arr1[j] : 0);
            if(d<a1){
                d+=10;
                borrow = 1;
            }
            else{
                borrow =0;
            }
            diff[k] = d - a1;
            i--;
            j--;
            k--;
        } 
        int idx = 0;
        while(idx < diff.length){
            if (diff[idx] == 0) idx++;
            else break;
        }
        while(idx < diff.length){
            System.out.print(diff[idx]+" ");
            idx++;
        } 
        in.close(); 
        }
}