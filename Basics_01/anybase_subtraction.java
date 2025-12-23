package Basics_01;
import java.util.Scanner;

public class anybase_subtraction {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the greater number");
        int n1 = in.nextInt();

        System.out.println("Enter the smaller number");
        int n2 = in.nextInt();

        System.out.println("Enter the base ");
        int b = in.nextInt();

        int borrow = 0;
        int result = 0;
        int p = 1;

        while (n1 > 0 || n2 > 0) {
            int dig1 = n1 % 10;
            int dig2 = n2 % 10;

            n1 = n1 / 10;
            n2 = n2 / 10;

            dig1 = dig1 + borrow;

            int digSub;
            if (dig1 >= dig2) {
                digSub = dig1 - dig2;
                borrow = 0;
            } else {
                digSub = dig1 + b - dig2;
                borrow = -1;
            }

            result = result + digSub * p;
            p = p * 10;
        }

        System.out.println("Subtraction result = " + result);
        in.close();
    }
}
