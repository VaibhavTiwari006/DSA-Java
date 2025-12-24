package Arrays_02;
import java.util.Scanner;
// span of array means differnce of largest and smallest value of an array
public class SpanOfArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of elements in the array");
        int n = in.nextInt();
        if (n<=0) {
            System.out.println("Array must have at least one element");
            in.close();
            return;
        }
        int arr[] = new int[n];
        System.out.println("Enter the elements in the array");
        for(int i =0 ; i<n ; i++){ // input the elements
            arr[i]=in.nextInt();
        }
        int max=arr[0],min=arr[0]; 
        for(int i =1; i<n ;i++){
            if(arr[i]>max){
                max = arr[i];
            }
            if(arr[i]<min){
                min = arr[i];
            }
        }
        int span = max-min;
        System.out.println("The span of an array is "+span);
       in.close();
    }
}