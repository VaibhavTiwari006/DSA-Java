package Arrays_02;
import java.util.*;
/*
Enter number of elements in the array
3
Enter the elements in first array
9
9
9
Enter number of elements in the second array
1
Enter the elements in second array
1
The sum of these two arrays is
1
0
0
0
 */
public class SumOfTwoArrays {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
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

        int[] sum = new int[n1 > n2 ? n1 : n2];
        int carry = 0;
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int k = sum.length - 1;
        System.out.println("The sum of these two arrays is");
        while(k>=0){
            int digit =carry;
            if(i>=0){
                digit +=arr1[i];
            }
            if(j >=0){
                digit +=arr2[j];
            }
            carry = digit/10;
            digit = digit%10;

            sum[k] = digit;

            i--;
            j--;
            k--;
        }
        if(carry!=0){
            System.out.println(carry);
        }
        for(int val: sum){
            System.out.println(val);
        }
        in.close();
    }
}