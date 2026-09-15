package Arrays_02;
import java.util.Scanner;

public class DuplicateElements {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter lentgh of the array");
        int n = in.nextInt();
        System.out.println("Enter elements into the array");
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int ele = arr[i];
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == ele) {
                    System.out.println(ele);
                    break;

                }
            }
        }
        in.close();
    }
}
