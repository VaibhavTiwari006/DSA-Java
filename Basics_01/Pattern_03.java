package Basics_01;
import java.util.Scanner;
public class Pattern_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            // Print spaces
            for (int j = n; j > i; j--) {
                System.out.print("  ");
            }
            
            // Print decreasing numbers
            for (int j = i; j >= 1; j--) {
                System.out.print(j + " ");
            }
            
            // Print increasing numbers
            for (int j = 2; j <= i; j++) {
                System.out.print(j + " ");
            }
            
            System.out.println();
        }
        sc.close();
    }
}