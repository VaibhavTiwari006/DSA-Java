package Arrays_02;
import java.util.Scanner;
public class FindElement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean found = false;
        System.out.println("Enter number of elements in the array");
        int n = in.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter the elements in the array");
        for (int i = 0; i < n; i++) { 
            arr[i] = in.nextInt();
        }
        System.out.println("Enter the element to find ");
        int find = in.nextInt();
        for (int i = 0; i < n; i++) {
            if (arr[i] == find) {
                System.out.println("Element is present at index " + i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Element not found");
        }
        in.close();
    }
}