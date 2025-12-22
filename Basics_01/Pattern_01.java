package Basics_01;
import java.util.Scanner;
public class Pattern_01 {
    /*        *
             **
            ***
           ****
          *****
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of rows");
        int n = in.nextInt();
        int space = n - 1, star = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= space; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= star; j++) {
                System.out.print("*");
            }
            space --; star++;
            System.out.println();
        }
        in.close();
    }
}